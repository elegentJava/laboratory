package com.bupt.ltb.sem.vouching.service;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;

/**
 * 练习平台业务接口
 * 
 * @author Hogan
 * 
 */
public interface PracticeService {

	/**
	 * 装载练习试题选择页面
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadPracticeSelectInfo(JSONObject jParams);

	/**
	 * 生成练习试卷
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject generatePracticePaper(JSONObject jParams, HttpSession session);

	/**
	 * 装载开始考试页面
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadStartPracticeInfo(JSONObject jParams, HttpSession session);

	/**
	 * 装载练习记录
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadPracticeRecord(JSONObject jParams, HttpSession session);

	/**
	 * 展示答案和分数
	 * 
	 * @param jParams
	 * @param session 
	 * @return
	 */
	public LJSONObject showAnswerAndScore(JSONObject jParams, HttpSession session);

}
