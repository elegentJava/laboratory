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
import com.bupt.ltb.sem.vouching.service.UserService;

/**
 * 用户控制层实现
 * 
 * @author Hogan
 * 
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private UserService userService;

	/**
	 * 登录验证
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("login")
	public @ResponseBody JSONObject login(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = userService.loginValidate(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("登录验证失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 注销登陆
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("logout")
	public @ResponseBody JSONObject logout(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = userService.logout(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("注销登陆失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 重置密码
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("resetPassword")
	public @ResponseBody JSONObject resetPassword(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = userService.resetPassword(rt.getJParams());
		} catch (Exception e) {
			log.error("重置密码失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 装载导航栏信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadNavigate")
	public @ResponseBody JSONObject loadNavigate(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = userService.loadNavigate(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("装载导航栏信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 修改用户密码
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("modifyPassowrd")
	public @ResponseBody JSONObject modifyPassowrd(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = userService.modifyPassowrd(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("修改用户密码失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载班级列表
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadClassList")
	public @ResponseBody JSONObject loadClassList(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = userService.loadClassList(rt.getJParams());
		} catch (Exception e) {
			log.error("装载班级列表失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 装载联系人列表
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadUsersInClass")
	public @ResponseBody JSONObject loadUsersInClass(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = userService.loadUsersInClass(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("装载联系人列表失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

}
