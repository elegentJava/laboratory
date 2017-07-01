package com.bupt.ltb.sem.vouching.test.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.sem.vouching.service.ResourceService;
import com.bupt.ltb.sem.vouching.test.base.BaseTest;

/**
 * 测试资源管理业务
 * 
 * @author Administrator
 *
 */
public class TestResourceService extends BaseTest {

	@Resource
	private ResourceService resourceService;

	/**
	 * 测试查询语句
	 */
	@Test
	public void TestquerySentence() {
		JSONObject jParams = new JSONObject();
		jParams.put("pageNum", 1);
		jParams.put("level", 0);
		jParams.put("type", 0);
		jParams.put("categoryId", 8);
		LJSONObject result = resourceService.querySentence(jParams);
		Assert.assertNotNull(result);
	}
}
