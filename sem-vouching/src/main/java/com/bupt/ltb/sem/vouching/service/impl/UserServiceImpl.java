package com.bupt.ltb.sem.vouching.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.type.ErrorCode;
import com.bupt.ltb.common.util.StringUtils;
import com.bupt.ltb.sem.vouching.frame.Consts;
import com.bupt.ltb.sem.vouching.mapper.ClassMapper;
import com.bupt.ltb.sem.vouching.mapper.EmailMapper;
import com.bupt.ltb.sem.vouching.mapper.UserMapper;
import com.bupt.ltb.sem.vouching.pojo.Class;
import com.bupt.ltb.sem.vouching.pojo.User;
import com.bupt.ltb.sem.vouching.service.UserService;
import com.bupt.ltb.sem.vouching.type.error.UserError;

/**
 * 用户实现
 * 
 * @author Hogan
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private EmailMapper emailMapper;
	
	@Resource
	private ClassMapper classMapper;

	@Override
	public LJSONObject loginValidate(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		String account = jParams.getString("account");
		String password = jParams.getString("password");
		Boolean isFrontLogin = jParams.getBoolean("flag");
		if (isFrontLogin != null) {
			if (!StringUtils.isNullOrBlank(account)) {
				if (!StringUtils.isNullOrBlank(password)) {
					User user = userMapper.findUserByAP(account, password);
					if (user != null) {
						if (isFrontLogin && user.getRole() != Consts.ROLE_ADMIN
								|| !isFrontLogin && user.getRole() == Consts.ROLE_ADMIN) {
							user.setLoginDate(new Date());
							userMapper.updateOnlineStatus(user.getUserId(), Consts.USER_IS_ONLINE);
							session.setAttribute(Consts.SESSION_USER, user);
							result.setErrorCode(ErrorCode.SUCCESS);
						} else {
							result.setErrorCode(UserError.NO_PRIVILEGE);
						}
					} else {
						result.setErrorCode(UserError.AP_NOT_MATCH);
					}
				} else {
					result.setErrorCode(UserError.PASSWORD_NULL);
				}
			} else {
				result.setErrorCode(UserError.ACCOUNT_IS_NULL);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public LJSONObject logout(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		user.setIsOnline(Consts.USER_NOT_ONLINE);
		user.setLastLoginDate(user.getLoginDate());
		if (userMapper.updateStatusAndLastLoginDate(user) == Consts.DATA_SINGLE_SUCCESS) {
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(UserError.LOGOUT_FAILD);
		}
		return result;
	}

	@Override
	public LJSONObject resetPassword(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		Integer userId = jParams.getInteger("userId");
		if (userId != null) {
			if (userMapper.updatePassword(userId, Consts.DEFAULT_PASSWORD) == Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(ErrorCode.SUCCESS);
			} else {
				result.setErrorCode(UserError.RESET_PASSWORD_FAILD);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public LJSONObject loadNavigate(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		Integer unreadCount = emailMapper.findUnreadReceiveEmailCount(user.getUserId());
		detail.put("unreadCount", unreadCount);
		if (Consts.ROLE_STUDENT == user.getRole()) {
			detail.put("credit", user.getCredit());
		} else {
			detail.put("credit", null);
		}
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public LJSONObject modifyPassowrd(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		String oldPassword = jParams.getString("oldPassword");
		String newPassword = jParams.getString("newPassword");
		String confirmPassword = jParams.getString("confirmPassword");
		if (!StringUtils.MultiIsNullOrBlank(oldPassword, newPassword, confirmPassword)) {
			if (newPassword.equals(confirmPassword)) {
				Integer userId = ((User) session.getAttribute(Consts.SESSION_USER)).getUserId();
				if (userMapper.findUserByIdAndPassword(userId, oldPassword) != null) {
					if (userMapper.updatePassword(userId, newPassword) == Consts.DATA_SINGLE_SUCCESS) {
						result.setErrorCode(ErrorCode.SUCCESS);
					} else {
						result.setErrorCode(UserError.MODIFY_PASSWORD_FAILD);
					}
				} else {
					result.setErrorCode(UserError.OLD_PASSWORD_INCORRECT);
				}
			} else {
				result.setErrorCode(UserError.TWICE_PASSWORD_NOT_MATCH);
			}
		} else {
			result.setErrorCode(UserError.PASSWORD_NULL);
		}
		return result;
	}

	@Override
	public LJSONObject loadUsersInClass(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		Integer classId = jParams.getInteger("classId");
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		if (classId != null) {
			List<User> users = userMapper.findUsersByClassId(classId);
			Iterator<User> iterator = users.iterator();
			while (iterator.hasNext()) {
				if (iterator.next().getUserId() == user.getUserId()) {
					iterator.remove();
					break;
				}
			}
			detail.put("users", users);
			result.setDetail(detail);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public LJSONObject loadClassList(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		List<Class> classes = classMapper.findClassByStatus(Consts.ACTIVE);
		detail.put("classes", classes);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

}
