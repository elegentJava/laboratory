package com.bupt.ltb.sem.vouching.type;

import java.util.HashMap;
import java.util.Map;

import com.bupt.ltb.common.type.Type;

/**
 * 语句分类类型
 * 
 * @author Hogan
 * 
 */
public enum SentenceType implements Type {

	/**
	 * 基本语句
	 */
	BASIC(0,"基本语句"),
	/**
	 * 委婉程度
	 */
	EUPHEMISTIC(1,"委婉程度"),
	/**
	 * 常用程度
	 */
	COMMON(2,"常用程度"),
	;

	private int id;
	private String description;

	SentenceType(int id, String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private static final Map<Integer, SentenceType> DIRC = new HashMap<>();

	static {
		for (SentenceType e : SentenceType.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static SentenceType byId(int id) {
		return DIRC.get(id);
	}

}
