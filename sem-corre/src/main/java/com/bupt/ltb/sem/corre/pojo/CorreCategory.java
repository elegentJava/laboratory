package com.bupt.ltb.sem.corre.pojo;

import java.util.List;

/**
 * 函电类别
 * 
 * @author Hogan
 *
 */
public class CorreCategory {

	private Integer ccid;
	private String content;
	private List<CorreCategory> sonCategories;

	public Integer getCcid() {
		return ccid;
	}

	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<CorreCategory> getSonCategories() {
		return sonCategories;
	}

	public void setSonCategories(List<CorreCategory> sonCategories) {
		this.sonCategories = sonCategories;
	}

}
