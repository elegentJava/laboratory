package com.bupt.ltb.sem.vouching.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.controller.RequestTemplate;
import com.bupt.ltb.common.controller.ResponseTemplate;
import com.bupt.ltb.sem.vouching.service.EmailService;

/**
 * 邮件控制层实现
 * 
 * @author Hogan
 * 
 */
@Controller
@RequestMapping("/email")
public class EmailController {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private EmailService emailService;

	/**
	 * 装载邮件信箱
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadEmailBox")
	public @ResponseBody JSONObject loadEmailBox(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = emailService.loadEmailBox(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("装载邮件信箱失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 发送邮件
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("sendEmail")
	public @ResponseBody JSONObject sendEmail(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = emailService.sendEmail(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("发送邮件失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 装载邮件详情信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadEmailDetail")
	public @ResponseBody JSONObject loadEmailDetail(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = emailService.loadEmailDetail(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("装载邮件详情信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 批量删除邮件
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("batchDeleteEmail")
	public @ResponseBody JSONObject batchDeleteEmail(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = emailService.batchDeleteEmail(rt.getJParams());
		} catch (Exception e) {
			log.error("批量删除邮件失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 首页装载未读站内信
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadUnreadEmailsForMain")
	public @ResponseBody JSONObject loadUnreadEmailsForMain(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = emailService.loadUnreadEmailsForMain(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("首页装载未读站内信失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

}
