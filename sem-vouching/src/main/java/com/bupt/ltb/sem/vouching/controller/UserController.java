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
@RequestMapping("/u")
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
	@RequestMapping("lg")
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
	@RequestMapping("lt")
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

}
