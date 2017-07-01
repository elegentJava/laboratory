package com.bupt.ltb.sem.vouching.service;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;

/**
 * 邮件业务层接口
 * 
 * @author Hogan
 * 
 */
public interface EmailService {

	/**
	 * 装载邮件信箱
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject loadEmailBox(JSONObject jParams, HttpSession session);
	
	/**
	 * 装载邮件详情信息
	 * 
	 * @param jParams
	 * @param session 
	 * @return
	 */
	public LJSONObject loadEmailDetail(JSONObject jParams, HttpSession session);

	/**
	 * 发送邮件
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject sendEmail(JSONObject jParams, HttpSession session);

	/**
	 * 批量删除邮件
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject batchDeleteEmail(JSONObject jParams);

	/**
	 * 首页装载未读站内信
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadUnreadEmailsForMain(JSONObject jParams, HttpSession session);

}
