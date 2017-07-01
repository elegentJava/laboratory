package com.bupt.ltb.sem.vouching.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.controller.LJSONObject;
import com.bupt.ltb.common.type.ErrorCode;
import com.bupt.ltb.common.util.StringUtils;
import com.bupt.ltb.sem.vouching.frame.Consts;
import com.bupt.ltb.sem.vouching.frame.GlobalContext;
import com.bupt.ltb.sem.vouching.frame.PageSize;
import com.bupt.ltb.sem.vouching.mapper.EmailMapper;
import com.bupt.ltb.sem.vouching.mapper.UserMapper;
import com.bupt.ltb.sem.vouching.pojo.Email;
import com.bupt.ltb.sem.vouching.pojo.User;
import com.bupt.ltb.sem.vouching.service.EmailService;
import com.bupt.ltb.sem.vouching.type.error.EmailError;
import com.bupt.ltb.sem.vouching.util.PageJsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 邮件业务层实现
 * 
 * @author Hogan
 * 
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Resource
	private EmailMapper emailMapper;

	@Resource
	private UserMapper userMapper;

	@Resource
	private GlobalContext globalContext;

	@Override
	public LJSONObject loadEmailBox(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		Integer emailType = jParams.getInteger("emailType");
		Integer pageNum = jParams.getInteger("pageNum");
		if (emailType != null && pageNum != null) {
			User user = (User) session.getAttribute(Consts.SESSION_USER);
			PageHelper.startPage(pageNum, PageSize.EMAIL_BOX_RECORD);
			List<Email> emails = null;
			PageInfo<Email> pageInfo = null;
			if (Consts.EMAIL_TYPE_RECEIVE == emailType) {
				emails = emailMapper.findReceiveEmails(user.getUserId());
				pageInfo = new PageInfo<Email>(emails);
				detail.put("receiveEmails", emails);
			} else {
				emails = emailMapper.findSendEmails(user.getUserId());
				pageInfo = new PageInfo<Email>(emails);
				detail.put("sendEmails", emails);
			}
			result.setDetail(detail);
			result.setPager(PageJsonUtil.generatePageJson(pageInfo));
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public LJSONObject sendEmail(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		String subject = jParams.getString("subject");
		String content = jParams.getString("content");
		String receiverIds = jParams.getString("receivers");
		if (!StringUtils.isNullOrBlank(subject)) {
			if (!StringUtils.isNullOrBlank(content)) {
				if (!StringUtils.isNullOrBlank(receiverIds)) {
					String[] receivers = receiverIds.split(";");
					for (int i = 0; i < receivers.length; i++) {
						User receiver = userMapper.findUserById(Integer.parseInt(receivers[i]));
						User sender = (User) session.getAttribute(Consts.SESSION_USER);
						if (receiver != null && receiver.getUserId() != sender.getUserId()) {
							Email email = new Email();
							email.setContent(content);
							email.setDate(new Date());
							email.setIsRead(Consts.EMAIL_UNREAD);
							email.setReceiveDel(Consts.EMAIL_NOT_DELETE);
							email.setReceiveName(receiver.getName());
							email.setReceiveId(receiver.getUserId());
							email.setSendDel(Consts.EMAIL_NOT_DELETE);
							email.setSendId(sender.getUserId());
							email.setSendName(sender.getName());
							email.setSubject(subject);
							if (emailMapper.saveEmail(email) != Consts.DATA_SINGLE_SUCCESS) {
								result.setErrorCode(EmailError.SEND_EMAIL_FAILD);
								return result;
							}
						} else {
							result.setErrorCode(EmailError.RECEIVER_EMAIL_INVALID);
							return result;
						}
					}
					result.setErrorCode(ErrorCode.SUCCESS);
				} else {
					result.setErrorCode(EmailError.RECEIVER_IS_NULL);
				}
			} else {
				result.setErrorCode(EmailError.CONTENT_IS_NULL);
			}
		} else {
			result.setErrorCode(EmailError.SUBJECT_IS_NULL);
		}
		return result;
	}

	@Override
	public LJSONObject loadEmailDetail(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		Integer[] emailDetail = globalContext.getCurrentEmailIndex().get(user.getUserId());
		if (emailDetail[1] == Consts.EMAIL_TYPE_RECEIVE) {// 收件箱查看修改状态
			Email email = new Email();
			email.setEmailId(emailDetail[0]);
			email.setIsRead(Consts.EMAIL_READ);
			if (emailMapper.updateEmailStatus(email) != Consts.DATA_SINGLE_SUCCESS) {
				result.setErrorCode(EmailError.SHOW_DETAIL_FAILD);
				return result;
			}
		}
		Email email = emailMapper.findEmailById(emailDetail[0]);
		if (email != null) {
			detail.put("email", email);
			result.setDetail(detail);
			result.setErrorCode(ErrorCode.SUCCESS);
		} else {
			result.setErrorCode(EmailError.SHOW_DETAIL_FAILD);
		}
		return result;
	}

	@Override
	public LJSONObject batchDeleteEmail(JSONObject jParams) {
		LJSONObject result = new LJSONObject();
		Integer type = jParams.getInteger("type");
		Integer[] emailIds = jParams.getObject("emailIds", Integer[].class);
		if (type != null && emailIds != null) {
			if (emailIds.length != 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("array", emailIds);
				if (type == Consts.EMAIL_TYPE_RECEIVE) {
					map.put("receiveDel", Consts.EMAIL_IS_DELETE);
					if (emailMapper.batchUpdateReceiveEmailDelStatus(map) == emailIds.length) {
						result.setErrorCode(ErrorCode.SUCCESS);
					} else {
						result.setErrorCode(EmailError.DEL_EMAIL_FAILD);
					}
				} else {
					map.put("sendDel", Consts.EMAIL_IS_DELETE);
					if (emailMapper.batchUpdateSendEmailDelStatus(map) == emailIds.length) {
						result.setErrorCode(ErrorCode.SUCCESS);
					} else {
						result.setErrorCode(EmailError.DEL_EMAIL_FAILD);
					}
				}
			} else {
				result.setErrorCode(EmailError.SELECTED_ZERO);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_ABNORMAL);
		}
		return result;
	}

	@Override
	public LJSONObject loadUnreadEmailsForMain(JSONObject jParams, HttpSession session) {
		LJSONObject result = new LJSONObject();
		JSONObject detail = new JSONObject();
		User user = (User) session.getAttribute(Consts.SESSION_USER);
		List<Email> emails = emailMapper.findReceiveUnreadEmails(user.getUserId());
		detail.put("emails", emails);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

}
