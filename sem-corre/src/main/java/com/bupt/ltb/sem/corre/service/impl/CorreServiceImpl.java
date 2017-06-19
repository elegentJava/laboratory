package com.bupt.ltb.sem.corre.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.type.ErrorCode;
import com.bupt.ltb.sem.corre.mapper.CorreCategoryMapper;
import com.bupt.ltb.sem.corre.pojo.CorreCategory;
import com.bupt.ltb.sem.corre.service.CorreService;

/**
 * 函电业务层实现
 * 
 * @author Hogan
 * @date 2017年6月19日
 */
public class CorreServiceImpl implements CorreService {

	@Resource
	private CorreCategoryMapper correCategoryMapper;

	@Override
	public LJSONObject loadCorreCategory(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		List<CorreCategory> correCategories = correCategoryMapper.findAllCorreCategories();
		detail.put("correCategories", correCategories);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject loadCorre(JSONObject jParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LJSONObject sendCorre(JSONObject jParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
