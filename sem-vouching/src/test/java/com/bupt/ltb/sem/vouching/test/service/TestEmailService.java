package com.bupt.ltb.sem.vouching.test.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.sem.vouching.service.EmailService;
import com.bupt.ltb.sem.vouching.test.base.BaseTest;

/**
 * 管理员业务测试
 * 
 * @author Hogan
 */
public class TestEmailService extends BaseTest {

	@Resource
	private EmailService emailService;

	@Test
	public void testloadEmailBox() {
		JSONObject jParams = new JSONObject();
		jParams.put("emailType", 0);
		jParams.put("pageNum", 1);
		LJSONObject result = emailService.loadEmailBox(jParams, session);
		Assert.assertNotNull(result);
	}

}
