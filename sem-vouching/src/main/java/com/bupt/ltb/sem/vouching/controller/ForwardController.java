package com.bupt.ltb.sem.vouching.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bupt.ltb.sem.vouching.frame.Consts;
import com.bupt.ltb.sem.vouching.frame.GlobalContext;
import com.bupt.ltb.sem.vouching.pojo.User;

/**
 * 转发资源控制类
 * 
 * @author Hogan
 * 
 */
@Controller
@RequestMapping("/forward")
public class ForwardController {

	@Resource
	private GlobalContext globalContext;

	//////////////////////////// 第一级目录////////////////////////////

	public static final String PATH_FRONT = "/front";
	public String front = PATH_FRONT;

	public static final String PATH_BACK = "/back";
	public String back = PATH_BACK;

	public static final String PATH_ERROR = "/error";
	public String error = PATH_ERROR;

	//////////////////////////// 第二级目录////////////////////////////

	public static final String PATH_COMMON = "/common";
	public String common = PATH_COMMON;

	public static final String PATH_COMPEPITION = "/competition";
	public String competition = PATH_COMPEPITION;
	
	public static final String PATH_COMPEPITION_STATION = "/competition_station";
	public String competitionStation = PATH_COMPEPITION_STATION;

	public static final String PATH_COMPEPITION_EXAM = "/competition_exam";
	public String competitionExam = PATH_COMPEPITION_EXAM;

	public static final String PATH_COMPETITION_RECORD = "/competition_record";
	public String competitionRecord = PATH_COMPETITION_RECORD;

	public static final String PATH_EMAIL = "/email";
	public String email = PATH_EMAIL;

	public static final String PATH_RESOURCE = "/resource";
	public String resource = PATH_RESOURCE;

	public static final String PATH_EXAM = "/exam";
	public String exam = PATH_EXAM;

	public static final String PATH_PRACTICE = "/practice";
	public String practice = PATH_PRACTICE;

	public static final String PATH_INDEX = "/index";
	public String index = PATH_INDEX;

	public static final String PATH_LOGIN = "/login";
	public String login = PATH_LOGIN;

	public static final String PATH_MODIFY_PASSOWRD = "/modify_password";
	public String modifyPassword = PATH_MODIFY_PASSOWRD;

	//////////////////////////// 第三级目录////////////////////////////

	/*
	 * common目录下
	 */
	public static final String PATH_MAIN = "/main";
	public String main = PATH_MAIN;

	public static final String PATH_FOOT = "/foot";
	public String foot = PATH_FOOT;

	/*
	 * Email目录下
	 */
	public static final String PATH_EMAIL_STATION = "/email_station";
	public String emailStation = PATH_EMAIL_STATION;

	public static final String PATH_FLASH = "/flash";
	public String flash = PATH_FLASH;

	public static final String PATH_RECEIVE_BOX = "/email_receive_box";
	public String receiveBox = PATH_RECEIVE_BOX;

	public static final String PATH_SEND_BOX = "/email_send_box";
	public String sendBox = PATH_SEND_BOX;

	public static final String PATH_SEND_EMAIL = "/email_send";
	public String sendEmail = PATH_SEND_EMAIL;

	public static final String PATH_EMAIL_DETAIL = "/email_detail";
	public String emailDetail = PATH_EMAIL_DETAIL;

	/*
	 * Exam目录下
	 */
	public static final String PATH_AUTO = "/exam_auto";
	public String auto = PATH_AUTO;

	public static final String PATH_CHAPTER = "/exam_chapter";
	public String chapter = PATH_CHAPTER;

	public static final String PATH_MANUAL = "/exam_manual";
	public String manual = PATH_MANUAL;

	public static final String PATH_PREVIEW = "/exam_preview";
	public String preview = PATH_PREVIEW;

	public static final String PATH_JOIN_EXAM = "/exam_join";
	public String joinExam = PATH_JOIN_EXAM;

	public static final String PATH_START_EXAM = "/exam_start";
	public String startExam = PATH_START_EXAM;

	public static final String PATH_EXAM_RECORD = "/exam_student_record";
	public String examRecord = PATH_EXAM_RECORD;

	public static final String PATH_MARK_PAPER = "/exam_mark_paper";
	public String markPaper = PATH_MARK_PAPER;

	public static final String PATH_MARK_DETAIL = "/exam_mark_detail";
	public String markDetail = PATH_MARK_DETAIL;

	public static final String PATH_EXAM_SETTING = "/exam_setting";
	public String examSetting = PATH_EXAM_SETTING;

	public static final String PATH_EXAM_STUDENT_SCORE = "/exam_student_score";
	public String examStudentScore = PATH_EXAM_STUDENT_SCORE;
	
	/*
	 * practice目录下
	 */
	public static final String PATH_PRACTICE_SELECT = "/practice_select";
	public String practiceSelect = PATH_PRACTICE_SELECT;

	public static final String PATH_START_PRACTICE = "/practice_start";
	public String practiceStart = PATH_START_PRACTICE;

	public static final String PATH_PRACTICE_RECORD = "/practice_record";
	public String practiceRecord = PATH_PRACTICE_RECORD;

	/*
	 * resource目录下
	 */
	public static final String PATH_CORRESPONDENCE = "/correspondence";
	public String correspondence = PATH_CORRESPONDENCE;

	public static final String PATH_FILE = "/file";
	public String file = PATH_FILE;

	public static final String PATH_GLOSSARY = "/glossary";
	public String glossary = PATH_GLOSSARY;

	public static final String PATH_SENTENCE = "/sentence";
	public String sentence = PATH_SENTENCE;

	//////////////////////////// 第四级目录////////////////////////////

	public static final String PATH_UPLOAD = "/upload";
	public String upload = PATH_UPLOAD;

	public static final String PATH_DOWNLOAD = "/download";
	public String download = PATH_DOWNLOAD;

	/////////////////////////////////////// 后台/////////////////////////////////

	public static final String PATH_USER = "/user";
	public String user = PATH_USER;

	public static final String PATH_CLASS = "/class";
	public String clas = PATH_CLASS;

	public static final String PATH_STUDENT_LIST = "/studentList";
	public String studentList = PATH_STUDENT_LIST;

	public static final String PATH_ADD_STUDENT = "/addStudent";
	public String addStudent = PATH_ADD_STUDENT;

	public static final String PATH_TEACHER_LIST = "/teacherlist";
	public String teacherList = PATH_TEACHER_LIST;

	public static final String PATH_ADD_TEACHER = "/addteacher";
	public String addTeacher = PATH_ADD_TEACHER;

	public static final String PATH_CLASS_LIST = "/classlist";
	public String classList = PATH_CLASS_LIST;

	public static final String PATH_ADD_CLASS = "/addclass";
	public String addClass = PATH_ADD_CLASS;

	public static final String PATH_CLASS_ASSIGN = "/classassign";
	public String classAssign = PATH_CLASS_ASSIGN;

	/**
	 * 跳转到前台登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardFrontLogin")
	public String forwardFrontLogin() {
		return formFrontResult(login);
	}

	/**
	 * 跳转到后台登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardFrontIndex")
	public String forwardFrontIndex(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		request.setAttribute("detail", user);
		return formFrontResult(index);
	}

	/**
	 * 跳转前台首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardFrontMain")
	public String forwardFrontMain(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		request.setAttribute("detail", user);
		return formFrontResult(main);
	}

	/**
	 * 跳转到修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardModifyPassword")
	public String forwardModifyPassword() {
		return formFrontResult(modifyPassword);
	}

	/**
	 * 跳转到词汇查询页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardGlossary")
	public String forwardGlossary() {
		return formFrontResult(resource, glossary);
	}

	/**
	 * 跳转到语句查询页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardSentence")
	public String forwardSentence() {
		return formFrontResult(resource, sentence);
	}

	/**
	 * 跳转到站内信首页
	 * 
	 * @return
	 */
	@RequestMapping("forwardEmailStation")
	public String forwardEmailStation() {
		return formFrontResult(email, emailStation);
	}

	/**
	 * 跳转到发送站内信页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardSendEmail")
	public String forwardSendEmail() {
		return formFrontResult(email, sendEmail);
	}

	/**
	 * 跳转到收件箱页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardReceiveBox")
	public String forwardReceiveBox() {
		return formFrontResult(email, receiveBox);
	}

	/**
	 * 跳转到发件箱页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardSendBox")
	public String forwardSendBox() {
		return formFrontResult(email, sendBox);
	}

	/**
	 * 跳转到详情页面
	 * 
	 * @param type
	 * @param emailId
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardEmailDetail")
	public String forwardEmailDetail(HttpSession session, Integer type, Integer emailId) {
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		globalContext.getCurrentEmailIndex().put(user.getUserId(), new Integer[] { emailId, type });
		return formFrontResult(email, emailDetail);
	}

	////////////////////////////// 练习平台模块相关//////////////////////////////

	/**
	 * 跳转到练习选题页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardPracticeSelect")
	public String forwardPracticeSelect() {
		return formFrontResult(practice, practiceSelect);
	}

	/**
	 * 跳转到练习页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardStartPractice")
	public String forwardStartPractice() {
		return formFrontResult(practice, practiceStart);
	}

	/**
	 * 跳转到练习记录页面
	 * 
	 * @return
	 */
	@RequestMapping("forwardShowRecord")
	public String forwardShowRecord() {
		return formFrontResult(practice, practiceRecord);
	}

	////////////////////////////// 考试平台模块相关//////////////////////////////

	/**
	 * 跳转到手工组卷页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardManual")
	public String forwardManual() {
		return formFrontResult(exam, manual);
	}

	/**
	 * 跳转到手工组卷结果页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardAuto")
	public String forwardAuto() {
		return formFrontResult(exam, auto);
	}

	/**
	 * 跳转到章节设置页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardChapter")
	public String forwardChapter() {
		return formFrontResult(exam, chapter);
	}

	/**
	 * 跳转到考试设置页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardExamSetting")
	public String forwardExamSetting() {
		return formFrontResult(exam, examSetting);
	}

	/**
	 * 跳转到考试设置页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardJoinExam")
	public String forwardJoinExam() {
		return formFrontResult(exam, joinExam);
	}

	/**
	 * 跳转到考试设置页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardStartExam")
	public String forwardStartExam() {
		return formFrontResult(exam, startExam);
	}

	/**
	 * 跳转到成绩记录页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardExamRecord")
	public String forwardExamRecord() {
		return formFrontResult(exam, examRecord);
	}

	/**
	 * 跳转到批改试卷页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardMarkPaper")
	public String forwardMarkPaper() {
		return formFrontResult(exam, markPaper);
	}

	/**
	 * 跳转到批改试卷详情页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardMarkDetail")
	public String forwardMarkDetail(String token, String userPaperId, HttpServletRequest request) {
		request.setAttribute("userPaperId", userPaperId);
		return formFrontResult(exam, markDetail);
	}

	/**
	 * 跳转到试卷预览页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardPreview")
	public String forwardPreview(String examId, HttpServletRequest request) {
		request.setAttribute("examId", examId);
		return formFrontResult(exam, preview);
	}
	
	/**
	 * 跳转到学生成绩页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardExamStudentScore")
	public String forwardExamStudentScore() {
		return formFrontResult(exam, examStudentScore);
	}
	
	/**
	 * 跳转到竞技匹配页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardCompetitionStation")
	public String forwardCompetition(){
		return formFrontResult(competition, competitionStation);
	}
	
	/**
	 * 跳转到竞技试卷页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardCompetitionExam")
	public String forwardCompetitionExam(){
		return formFrontResult(competition, competitionExam);
	}
	
	/**
	 * 跳转到竞技记录页面
	 * 
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("forwardCompetitionRecord")
	public String forwardCompetitionRecord(){
		return formFrontResult(competition, competitionRecord);
	}
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 跳转到后台登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("fbl")
	public String forwardBackLogin() {
		return formBackResult(login);
	}

	/**
	 * 跳转到后台首页
	 * 
	 * @return
	 */
	@RequestMapping("fbi")
	public String forwardBackIndex(HttpSession session) {
		return formBackResult(index);
	}

	/**
	 * 跳转到后台学生列表
	 * 
	 * @return
	 */
	@RequestMapping("fsl")
	public String forwardStudentList(HttpSession session) {
		return formBackResult(user, studentList);
	}

	/**
	 * 跳转到后台教师列表
	 * 
	 * @return
	 */
	@RequestMapping("ftl")
	public String forwardTeacherList(HttpSession session) {
		return formBackResult(user, teacherList);
	}

	/**
	 * 组合返回路径字符串
	 * 
	 * @param path
	 * @return
	 */
	private String formResult(String... path) {
		StringBuilder sb = new StringBuilder();
		for (String e : path) {
			sb.append(e);
		}
		return sb.toString();
	}

	/**
	 * 组合前台返回字符串
	 * 
	 * @param path
	 * @return
	 */
	private String formFrontResult(String... path) {
		return front + formResult(path);
	}

	/**
	 * 组合后台返回字符串
	 * 
	 * @param path
	 * @return
	 */
	private String formBackResult(String... path) {
		return back + formResult(path);
	}

}
