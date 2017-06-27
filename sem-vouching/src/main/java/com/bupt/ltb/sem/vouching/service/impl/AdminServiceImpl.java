package com.bupt.ltb.sem.vouching.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.type.ErrorCode;
import com.bupt.ltb.sem.vouching.frame.Consts;
import com.bupt.ltb.sem.vouching.frame.PageSize;
import com.bupt.ltb.sem.vouching.mapper.UserMapper;
import com.bupt.ltb.sem.vouching.pojo.User;
import com.bupt.ltb.sem.vouching.service.AdminService;
import com.bupt.ltb.sem.vouching.type.error.ResourceError;
import com.bupt.ltb.sem.vouching.type.error.UserError;
import com.bupt.ltb.sem.vouching.type.excel.UserTemplate;
import com.bupt.ltb.sem.vouching.util.POIUtils;
import com.bupt.ltb.sem.vouching.util.PageJsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 管理员业务层接口
 * 
 * @author Hogan
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private POIUtils poiUtils;

	@Resource
	private UserMapper userMapper;

	@Override
	public LJSONObject loadUserList(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		Integer role = jParams.getInteger("role");
		Integer pageNum = jParams.getInteger("pageNum");
		if (role != null && pageNum != null) {
			PageHelper.startPage(pageNum, PageSize.USER_LIST_RECORD);
			List<User> users = null;
			if (Consts.ROLE_STUDENT == role) {
				users = userMapper.findUserAndClassByRole(role);
			}
			PageInfo<User> pageInfo = new PageInfo<User>(users);
			detail.put("users", pageInfo.getList());
			result.setPager(PageJsonUtil.generatePageJson(pageInfo));
			result.setDetail(detail);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public LJSONObject batchInsertUsers(Map<String, Object> params, HttpServletRequest request) {
		LJSONObject result = new LJSONObject();
		Integer role = Integer.parseInt((String) params.get("role"));
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> ite = multiRequest.getFileNames();
			while (ite.hasNext()) {
				MultipartFile file = multiRequest.getFile(ite.next());
				if (file != null) {
					try {
						List<User> users = poiUtils.read(file, UserTemplate.DEFAULT, User.class);
						for (User user : users) {
							if (userMapper.findByAccount(user.getAccount()) != null) {
								result.setErrorCode(UserError.ACCOUNT_EXISTED);
								return result;
							}
							user.setPassword(Consts.DEFAULT_PASSWORD);
							user.setIsOnline(Consts.USER_NOT_ONLINE);
							user.setClassId(Consts.DEFAULT_CLASS_ID);
							user.setRole(role);
						}
						if (userMapper.batchInsert(users) == users.size()) {
							result.setErrorCode(ErrorCode.SUCCESS);
						} else {
							result.setErrorCode(UserError.USER_BATCH_INSERT_FAILD);
						}
					} catch (Exception e) {
						log.error("上传文件失败!", e);
						result.setErrorCode(ResourceError.UPLOAD_FAILD);
					}
				}
			}
		} else {
			result.setErrorCode(ErrorCode.FAILD);
		}
		return result;
	}

}
