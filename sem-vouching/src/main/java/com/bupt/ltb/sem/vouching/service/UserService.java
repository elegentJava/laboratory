package com.bupt.ltb.sem.vouching.service;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;

/**
 * 用户接口
 * 
 * @author Hogan
 */
public interface UserService {

	/**
	 * 登录验证
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loginValidate(JSONObject jParams, HttpSession session);

	/**
	 * 注销登陆
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject logout(JSONObject jParams, HttpSession session);

}
