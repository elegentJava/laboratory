package com.bupt.ltb.common.type;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应结果类型
 * 
 * @author Hogan
 * 
 */
public enum ErrorCode implements ErrorType {

	/**
	 * 操作成功
	 */
	SUCCESS(1, "success"),
	/**
	 * 操作失败
	 */
	FAILD(2, "faild"),
	/**
	 * 参数异常
	 */
	PARAM_ABNORMAL(3, "参数异常!"),;

	private int id;
	private String description;

	ErrorCode(int id, String description) {
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

	private static final Map<Integer, ErrorCode> DIRC = new HashMap<Integer, ErrorCode>();

	static {
		for (ErrorCode e : ErrorCode.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static ErrorCode byId(int id) {
		return DIRC.get(id);
	}

}