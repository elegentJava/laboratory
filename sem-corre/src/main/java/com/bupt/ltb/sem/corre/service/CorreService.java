package com.bupt.ltb.sem.corre.service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;

/**
 * 函电业务层接口
 * 
 * @author Hogan
 * @date 2017年6月19日
 */
public interface CorreService {

	/**
	 * 装载函电类型信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadCorreCategory(JSONObject jParams);

	/**
	 * 装载函电信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadCorre(JSONObject jParams);

	/**
	 * 发送函电邮件
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject sendCorre(JSONObject jParams);

}
