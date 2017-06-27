package com.bupt.ltb.sem.vouching.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.controller.RequestTemplate;
import com.bupt.ltb.common.controller.ResponseTemplate;
import com.bupt.ltb.sem.vouching.service.AdminService;

/**
 * 管理员控制层实现
 * 
 * @author Hogan
 * 
 */
@Controller
@RequestMapping("/ad")
public class AdminController {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private AdminService adminService;

	/**
	 * 装载用户列表数据
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("lul")
	public @ResponseBody JSONObject loadUserList(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = adminService.loadUserList(rt.getJParams());
		} catch (Exception e) {
			log.error("装载用户列表数据失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 批量添加用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("biu")
	public @ResponseBody JSONObject batchInsertUsers(HttpServletRequest request, HttpServletResponse response) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(request);
			detail = adminService.batchInsertUsers(rt.getParams(),request);
		} catch (Exception e) {
			log.error("批量添加用户失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
}
