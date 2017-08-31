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
	public static final String COMMON_SEPARATOR = ";";
	public static final Integer ACTIVE = 1;
	public static final Integer INACTIVE = 0;
	/**
	 * 邮件类型--发件箱
	 */
	public static final Integer EMAIL_TYPE_SEND = 0;
	/**
	 * 邮件类型--收件箱
	 */
	public static final Integer EMAIL_TYPE_RECEIVE = 1;
	/**
	 * 邮件未读
	 */
	public static final Integer EMAIL_UNREAD = 0;
	/**
	 * 邮件已读
	 */
	public static final Integer EMAIL_READ = 1;
	/**
	 * 邮件没有删除
	 */
	public static final Integer EMAIL_NOT_DELETE = 0;
	/**
	 * 邮件已经删除
	 */
	public static final Integer EMAIL_IS_DELETE = 1;
	public static final String ANSWER_NULL = "&|&";
	public static final Integer EXAM_JOINED = 1;
	public static final Integer USER_PAPER_DONE = 1;
	public static final Integer USER_PAPER_UNDO = 0;
	public static final Integer QUEUE_INIT_SIZE = 20;
	public static final Integer QUEUE_MATCH_SIZE = 2;
	public static final Integer QUESTION_COUNT = 10;


	//课程通过标准为10分，竞技通过得2分，练习通过得两分，考试通过得五分，案例通过得一分
	public static final Integer COMPETITION_PASSED=2;
	public static final Integer PRACTICE_PASSED=2;
	public static final Integer EXAM_PASSED=5;
	public static final Integer CASE_PASSED=1;
	public static final Integer COURSE_PASSED=10;


}
