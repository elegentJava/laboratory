package com.bupt.ltb.sem.vouching.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;

/**
 * 管理员业务层接口
 * 
 * @author Hogan
 */
public interface AdminService {

	/**
	 * 装载用户列表数据
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadUserList(JSONObject jParams);

	/**
	 * 批量添加用户
	 * 
	 * @param params
	 * @param request
	 * @return
	 */
	public LJSONObject batchInsertUsers(Map<String, Object> params, HttpServletRequest request);

}
