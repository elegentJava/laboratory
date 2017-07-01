package com.bupt.ltb.sem.vouching.service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;

/**
 * 资源管理业务层接口
 * 
 * @author Hogan
 */
public interface ResourceService {

	/**
	 * 装载词汇来源
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadGlossarySource(JSONObject jParams);

	/**
	 * 查询指定词汇
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject queryGlossary(JSONObject jParams);

	/**
	 * 装载语句类型树
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadSentenceCategoryTree(JSONObject jParams);

	/**
	 * 装载语句的类型和等级
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadSentenceLevelAndType(JSONObject jParams);

	/**
	 * 查询语句
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject querySentence(JSONObject jParams);

}
