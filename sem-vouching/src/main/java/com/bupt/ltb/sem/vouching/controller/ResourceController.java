package com.bupt.ltb.sem.vouching.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.controller.RequestTemplate;
import com.bupt.ltb.common.controller.ResponseTemplate;
import com.bupt.ltb.sem.vouching.service.ResourceService;

/**
 * 资源管理控制层实现
 * 
 * @author Hogan
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {
	
	private Logger log = Logger.getLogger(getClass());

	@Resource
	private ResourceService resourceService;
	
	/**
	 * 装载词汇来源
	 * 
	 * @param jo
	 * @param request
	 * @return
	 */
	@RequestMapping("loadGlossarySource")
	public @ResponseBody JSONObject loadGlossarySource(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.loadGlossarySource(rt.getJParams());
		} catch (Exception e) {
			log.error("装载词汇来源失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 查询指定词汇
	 * 
	 * @param jo
	 * @param request
	 * @return
	 */
	@RequestMapping("queryGlossary")
	public @ResponseBody JSONObject queryGlossary(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.queryGlossary(rt.getJParams());
		} catch (Exception e) {
			log.error("查询指定词汇失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载语句类型树
	 * 
	 * @param jo
	 * @param request
	 * @return
	 */
	@RequestMapping("loadSentenceCategoryTree")
	public @ResponseBody JSONObject loadSentenceCategoryTree(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.loadSentenceCategoryTree(rt.getJParams());
		} catch (Exception e) {
			log.error("装载语句类型树失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载语句的类型和等级
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadSentenceLevelAndType")
	public @ResponseBody JSONObject loadSentenceLevelAndType(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.loadSentenceLevelAndType(rt.getJParams());
		} catch (Exception e) {
			log.error("装载语句的类型和等级失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 查询语句
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("querySentence")
	public @ResponseBody JSONObject querySentence(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = resourceService.querySentence(rt.getJParams());
		} catch (Exception e) {
			log.error("查询语句失败!",e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
}
