package com.bupt.ltb.sem.vouching.service;

import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;

/**
 * 考试平台业务层接口
 * 
 * @author Hogan
 * 
 */
public interface ExamService {

	/**
	 * 装载章节信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadChapters(JSONObject jParams);

	/**
	 * 修改章节状态
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject updateChapterStatus(JSONObject jParams);

	/**
	 * 装载自动组卷页面
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadExamAutoInfo(JSONObject jParams);

	/**
	 * 自动生成试卷
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject generateAutoExam(JSONObject jParams, HttpSession session);

	/**
	 * 装载试卷设置页面数据
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject loadExamSetting(JSONObject jParams, HttpSession session);

	/**
	 * 修改试卷的状态
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject updateExamStatus(JSONObject jParams);

	/**
	 * 删除试卷信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject deleteExam(JSONObject jParams);

	/**
	 * 装载预览试卷页面信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadPreview(JSONObject jParams);

	/**
	 * 试卷阅览页面删除题目
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject deleteQuestion(JSONObject jParams);

	/**
	 * 初始化装载手工组卷页面
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadManualExamInfo(JSONObject jParams);

	/**
	 * 手工组卷页面查询试题
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject queryQuestions(JSONObject jParams);

	/**
	 * 装载参加考试页面
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject loadJoinExamInfo(JSONObject jParams, HttpSession session);

	/**
	 * 参加考试
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject startExam(JSONObject jParams, HttpSession session);

	/**
	 * 装载考试页面
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject loadStartExamInfo(JSONObject jParams, HttpSession session);

	/**
	 * 保存用户考试信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject generateUserPaper(JSONObject jParams, HttpSession session);

	/**
	 * 装载用户考试成绩信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadExamStudentRecord(JSONObject jParams, HttpSession session);

	/**
	 * 自动组卷页面校验考试名称
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject validateExamName(JSONObject jParams);

	/**
	 * 手动创建试卷
	 * 
	 * @param jParams
	 * @param session
	 * @return
	 */
	public LJSONObject generateManualExam(JSONObject jParams, HttpSession session);

	/**
	 * 装载批改试卷信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadMarkPaperInfo(JSONObject jParams, HttpSession session);

	/**
	 * 装载批阅试卷详情信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadMarkDetail(JSONObject jParams);

	/**
	 * 批阅试卷
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject generateMarkPaper(JSONObject jParams);

	/**
	 * 装载考试信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadScoredExamInfo(JSONObject jParams, HttpSession session);

	/**
	 * 装载成绩信息
	 * 
	 * @param jParams
	 * @return
	 */
	public LJSONObject loadExamStudentScoreInfo(JSONObject jParams);
}
