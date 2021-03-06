package com.bupt.ltb.sem.corre.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.type.ErrorCode;
import com.bupt.ltb.sem.corre.mapper.CorreCategoryMapper;
import com.bupt.ltb.sem.corre.mapper.CorreMapper;
import com.bupt.ltb.sem.corre.pojo.Corre;
import com.bupt.ltb.sem.corre.pojo.CorreCategory;
import com.bupt.ltb.sem.corre.service.CorreService;
import com.bupt.ltb.sem.corre.util.CorreUtils;
import com.bupt.ltb.sem.corre.util.EmailUtils;

/**
 * 函电业务层实现
 * 
 * @author Hogan
 * @date 2017年6月19日
 */
@Service("correService")
public class CorreServiceImpl implements CorreService {

	@Resource
	private CorreCategoryMapper correCategoryMapper;

	@Resource
	private CorreMapper correMapper;

	@Override
	public LJSONObject loadCorreCategory(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		Integer fatherId = jParams.getInteger("fatherId");
		List<CorreCategory> correCategories = null;
		if (fatherId == null) {
			correCategories = correCategoryMapper.findAllCorreCategories();
		} else {
			correCategories = correCategoryMapper.findAllCategoriesByFatherId(fatherId);
		}
		detail.put("categories", correCategories);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject loadCorre(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		Integer cc_id = jParams.getInteger("ccid");
		Corre corre = correMapper.findCorreById(cc_id);
		corre.setCorreEnglish(CorreUtils.generateCorrespondenceStr(corre.getCorreEnglish()));
		detail.put("corre", corre);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject sendCorre(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		String receiver = jParams.getString("receiver");
		String subject = jParams.getString("subject");
		String content = jParams.getString("content");
		EmailUtils.sendHtmlEmail(receiver, subject, content);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

}
