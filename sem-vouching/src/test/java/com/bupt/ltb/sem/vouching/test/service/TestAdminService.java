package com.bupt.ltb.sem.vouching.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.sem.vouching.service.AdminService;
import com.bupt.ltb.sem.vouching.test.base.BaseTest;

/**
 * 管理员业务测试
 * 
 * @author Hogan
 */
public class TestAdminService extends BaseTest {

	@Resource
	private AdminService adminService;
	
	private JSONObject jParams;
	
	@Test
	public void testLoadUserList(){
		jParams = new JSONObject();
		jParams.put("role", 1);
		jParams.put("pageNum", 1);
		LJSONObject result=adminService.loadUserList(jParams);
		System.out.println(result);
	}
	
}
