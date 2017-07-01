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

	/**
	 * 重置密码
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject resetPassword(JSONObject jParams);

	/**
	 * 装载导航栏信息
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject loadNavigate(JSONObject jParams, HttpSession session);

	/**
	 * 修改用户密码
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject modifyPassowrd(JSONObject jParams, HttpSession session);

	/**
	 * 装载班级列表
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadClassList(JSONObject jParams);

	/**
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject loadUsersInClass(JSONObject jParams, HttpSession session);

}
