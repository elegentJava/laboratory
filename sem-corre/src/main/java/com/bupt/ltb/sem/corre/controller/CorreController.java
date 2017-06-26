package com.bupt.ltb.sem.corre.controller;

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
import com.bupt.ltb.sem.corre.service.CorreService;

/**
 * 函电控制层实现
 * 
 * @author Hogan
 *
 */
@Controller
@RequestMapping("/c")
public class CorreController {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private CorreService correService;

	/**
	 * 装载函电类型信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("lcc")
	public @ResponseBody JSONObject loadCorreCategory(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = correService.loadCorreCategory(rt.getJParams());
		} catch (Exception e) {
			log.error("装载函电类型信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 装载函电信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("lc")
	public @ResponseBody JSONObject loadCorre(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = correService.loadCorre(rt.getJParams());
		} catch (Exception e) {
			log.error("装载函电信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 发送函电邮件
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("sc")
	public @ResponseBody JSONObject sendCorre(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = correService.sendCorre(rt.getJParams());
		} catch (Exception e) {
			log.error("发送函电邮件失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

}
