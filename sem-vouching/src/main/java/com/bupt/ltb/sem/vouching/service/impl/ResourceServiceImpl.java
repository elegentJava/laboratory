package com.bupt.ltb.sem.vouching.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.type.ErrorCode;
import com.bupt.ltb.common.util.StringUtils;
import com.bupt.ltb.sem.vouching.frame.PageSize;
import com.bupt.ltb.sem.vouching.mapper.GlossaryMapper;
import com.bupt.ltb.sem.vouching.mapper.SenCategoryMapper;
import com.bupt.ltb.sem.vouching.mapper.SentenceMapper;
import com.bupt.ltb.sem.vouching.pojo.Glossary;
import com.bupt.ltb.sem.vouching.pojo.SenCategory;
import com.bupt.ltb.sem.vouching.pojo.Sentence;
import com.bupt.ltb.sem.vouching.service.ResourceService;
import com.bupt.ltb.sem.vouching.type.GlossarySourceType;
import com.bupt.ltb.sem.vouching.type.LevelType;
import com.bupt.ltb.sem.vouching.type.SentenceType;
import com.bupt.ltb.sem.vouching.util.PageJsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 资源管理业务层实现
 * 
 * @author Hogan
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	@Resource
	private GlossaryMapper glossaryMapper;
	@Resource
	private SenCategoryMapper senCategoryMapper;
	@Resource
	private SentenceMapper sentenceMapper;

	@Override
	public LJSONObject loadGlossarySource(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		List<GlossarySource> glossarySources = new ArrayList<GlossarySource>();
		for (GlossarySourceType source : GlossarySourceType.values()) {
			glossarySources.add(new GlossarySource(source.getId(), source.getDescription()));
		}
		detail.put("glossarySources", glossarySources);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject queryGlossary(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		String word = jParams.getString("word");
		Integer[] sources = jParams.getObject("sources", Integer[].class);
		Integer pageNum = jParams.getInteger("pageNum");
		PageHelper.startPage(pageNum, PageSize.GLOSSARY_RESULT);
		List<Glossary> glossaries = null;
		PageInfo<Glossary> pageInfo = null;
		if (sources != null) {
			if (StringUtils.isNullOrBlank(word) && sources.length == 0) {// 未输入单词和来源
				glossaries = glossaryMapper.findAllGlossaries();
			} else if (!StringUtils.isNullOrBlank(word) && sources.length == 0) {// 只输入单词，未选择来源
				glossaries = glossaryMapper.findGlossariesByWord(word);
			} else if (StringUtils.isNullOrBlank(word) && sources.length > 0) {// 只选择来源，未输入单词
				glossaries = glossaryMapper.findGlossariesBySource(sources);
			} else {// 输入了单词，也选择了来源
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("word", word);
				map.put("array", sources);
				glossaries = glossaryMapper.findGlossariesBySourceAndWord(map);
			}
			pageInfo = new PageInfo<Glossary>(glossaries);
			detail.put("glossaries", glossaries);
			result.setDetail(detail);
			result.setPager(PageJsonUtil.generatePageJson(pageInfo));
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public LJSONObject loadSentenceCategoryTree(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		List<SenCategory> categories = senCategoryMapper.findAllCategories();
		detail.put("categories", categories);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject loadSentenceLevelAndType(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		List<SenLevel> levels = new ArrayList<SenLevel>();
		List<SenType> types = new ArrayList<SenType>();
		for (LevelType level : LevelType.values()) {
			levels.add(new SenLevel(level.getId(), level.getDescription()));
		}
		for (SentenceType type : SentenceType.values()) {
			types.add(new SenType(type.getId(), type.getDescription()));
		}
		detail.put("levels", levels);
		detail.put("types", types);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject querySentence(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		int pageNum = jParams.getIntValue("pageNum");
		Integer level = jParams.getInteger("level");
		Integer type = jParams.getInteger("type");
		Integer categoryId = jParams.getInteger("categoryId");
		List<Sentence> sentences = null;
		if (level != null && type != null && categoryId != null) {
			PageHelper.startPage(pageNum, PageSize.SENTENCE_RESULT);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("categoryId", categoryId);
			map.put("type", type);
			if (type == 0) {
				sentences = sentenceMapper.findSentencesByCT(map);
			} else {
				map.put("level", level);
				sentences = sentenceMapper.findSentencesByCTL(map);
			}
			PageInfo<Sentence> pageInfo = new PageInfo<Sentence>(sentences);
			detail.put("sentences", pageInfo.getList());
			result.setDetail(detail);
			result.setPager(PageJsonUtil.generatePageJson(pageInfo));
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	/**
	 * 语句等级内部类试实体
	 * 
	 * @author Hogan
	 * 
	 */
	public static class SenLevel {

		private int id;
		private String name;

		public SenLevel(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

	}

	/**
	 * 语句类型内部类试题
	 * 
	 * @author Hogan
	 * 
	 */
	public static class SenType {

		private int id;
		private String name;

		public SenType(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

	}

	/**
	 * 词汇来源实体
	 * 
	 * @author Hogan
	 * 
	 */
	public class GlossarySource {

		private Integer id;
		private String description;

		public GlossarySource(Integer id, String description) {
			this.id = id;
			this.description = description;
		}

		public Integer getId() {
			return id;
		}

		public String getDescription() {
			return description;
		}

	}

}
