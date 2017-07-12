package com.bupt.ltb.sem.vouching.service;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;

/**
 * 竞技模块业务层接口
 * 
 * @author Hogan
 *
 */
public interface CompetitionService {

	/**
	 * 装载排行榜信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadRankInfo(JSONObject jParams);

	/**
	 * 加入到匹配队列中
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject joinQueue(JSONObject jParams, HttpSession session);

	/**
	 * 匹配
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject matching(JSONObject jParams, HttpSession session);

	/**
	 * 装载竞技试题
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject loadCompetitionExam(JSONObject jParams, HttpSession session);

	/**
	 * 装载竞技记录
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject loadCompetitionRecord(JSONObject jParams, HttpSession session);

	/**
	 * 显示试题内容
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject showAnswer(JSONObject jParams, HttpSession session);

	/**
	 * 检测积分状态
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject creditHandle(JSONObject jParams, HttpSession session);

}
