package com.bupt.ltb.sem.vouching.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 转发资源控制类
 * 
 * @author Hogan
 * 
 */
@Controller
@RequestMapping("/f")
public class ForwardController {

	public static final String PATH_FRONT = "/front";
	public String front = PATH_FRONT;

	public static final String PATH_BACK = "/back";
	public String back = PATH_BACK;

	public static final String PATH_ERROR = "/error";
	public String error = PATH_ERROR;

	public static final String PATH_LOGIN = "/login";
	public String login = PATH_LOGIN;

	public static final String PATH_INDEX = "/index";
	public String index = PATH_INDEX;

	public static final String PATH_USER = "/user";
	private String user = PATH_USER;

	public static final String PATH_STUDENT_LIST = "/studentlist";
	private String studentList = PATH_STUDENT_LIST;
	
	public static final String PATH_TEACHER_LIST = "/teacherlist";
	private String teacherList = PATH_TEACHER_LIST;

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
