package com.bupt.ltb.sem.vouching.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.type.ErrorCode;
import com.bupt.ltb.common.util.StringUtils;
import com.bupt.ltb.sem.vouching.frame.Consts;
import com.bupt.ltb.sem.vouching.mapper.UserMapper;
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

}
