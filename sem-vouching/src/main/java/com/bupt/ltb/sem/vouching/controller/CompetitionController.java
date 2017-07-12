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
import com.bupt.ltb.sem.vouching.service.CompetitionService;

/**
 * 邮件控制层实现
 * 
 * @author Hogan
 * 
 */
@Controller
@RequestMapping("/competition")
public class CompetitionController {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private CompetitionService competitionService;

	/**
	 * 装载排行榜信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadRankInfo")
	public @ResponseBody JSONObject loadRankInfo(@RequestBody JSONObject jo) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = competitionService.loadRankInfo(rt.getJParams());
		} catch (Exception e) {
			log.error("装载排行榜信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 加入到匹配队列中
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("joinQueue")
	public @ResponseBody JSONObject joinQueue(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = competitionService.joinQueue(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("加入到匹配队列中失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 匹配
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("matching")
	public @ResponseBody JSONObject matching(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = competitionService.matching(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("匹配失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载竞技试题
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadCompetitionExam")
	public @ResponseBody JSONObject loadCompetitionExam(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = competitionService.loadCompetitionExam(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("装载竞技试题失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 装载竞技记录
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadCompetitionRecord")
	public @ResponseBody JSONObject loadCompetitionRecord(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = competitionService.loadCompetitionRecord(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("装载竞技记录失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 显示试题内容
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("showAnswer")
	public @ResponseBody JSONObject showAnswer(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = competitionService.showAnswer(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("显示试题内容失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 检测积分状态
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("creditHandle")
	public @ResponseBody JSONObject creditHandle(@RequestBody JSONObject jo, HttpSession session) {
		LJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = competitionService.creditHandle(rt.getJParams(), session);
		} catch (Exception e) {
			log.error("检测积分状态失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

}
