package com.bupt.ltb.sem.vouching.pojo;

import java.io.Serializable;

/**
 * 班级实体
 * 
 * @author Hogan
 */
public class Class implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer classId;
	private String className;
	private Integer isActive;
	private String bak;

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getBak() {
		return bak;
	}

	public void setBak(String bak) {
		this.bak = bak;
	}

}
