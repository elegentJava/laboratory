package com.bupt.ltb.sem.vouching.frame;

/**
 * 常量
 * 
 * @author Hogan
 */
public class Consts {

	/**
	 * 角色类型--管理员
	 */
	public static final Integer ROLE_ADMIN = 0;
	/**
	 * 角色类型--学生
	 */
	public static final Integer ROLE_STUDENT = 1;
	/**
	 * 角色类型--教师
	 */
	public static final Integer ROLE_TEACHER = 2;
	/**
	 * 用户在线状态--不在线
	 */
	public static final Integer USER_NOT_ONLINE = 0;
	/**
	 * 用户在线状态--在线
	 */
	public static final Integer USER_IS_ONLINE = 1;
	/**
	 * Session中存储user的key
	 */
	public static final String SESSION_USER = "sessionUser";
	/**
	 * 处理单条数据成功标识
	 */
	public static final Integer DATA_SINGLE_SUCCESS = 1;
	/**
	 * 简单的日期格式
	 */
	public static final String DATE_SIMPLE_PATTERN = "yyyy-MM-dd HH:mm";
	/**
	 * 默认密码 1
	 */
	public static final String DEFAULT_PASSWORD = "c4ca4238a0b923820dcc509a6f75849b";
	/**
	 * 默认的班级ID(创建用户没有选择班级)
	 */
	public static final Integer DEFAULT_CLASS_ID = 1;

}
