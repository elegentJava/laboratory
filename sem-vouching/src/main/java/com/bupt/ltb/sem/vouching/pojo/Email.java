package com.bupt.ltb.sem.vouching.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.bupt.ltb.sem.vouching.frame.Consts;

/**
 * Email实体
 * 
 * @author Hogan
 * 
 */
public class Email implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer emailId;
	private String subject;
	private String content;
	private Date date;
	private Integer isRead;
	private Integer sendDel;
	private Integer receiveDel;
	private Integer sendId;
	private String sendName;
	private Integer receiveId;
	private String receiveName;

	// 页面中使用
	private String formatDate;
	private String isReadName;

	public Integer getEmailId() {
		return emailId;
	}

	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Integer getSendDel() {
		return sendDel;
	}

	public void setSendDel(Integer sendDel) {
		this.sendDel = sendDel;
	}

	public Integer getReceiveDel() {
		return receiveDel;
	}

	public void setReceiveDel(Integer receiveDel) {
		this.receiveDel = receiveDel;
	}

	public Integer getSendId() {
		return sendId;
	}

	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public Integer getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getFormatDate() {
		if (this.date != null) {
			formatDate = DateFormatUtils.format(this.date, Consts.DATE_SIMPLE_PATTERN);
		}
		return formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

	public String getIsReadName() {
		isReadName = this.isRead == 0 ? "未读" : "已读";
		return isReadName;
	}

	public void setIsReadName(String isReadName) {
		this.isReadName = isReadName;
	}

}
