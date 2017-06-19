package com.bupt.ltb.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.type.ErrorCode;
import com.bupt.ltb.common.util.Consts;

/**
 * 响应模板
 * 
 * @author Hogan
 * 
 */
public class ResponseTemplate {

	private LJSONObject detail;

	public ResponseTemplate() {
	}

	public ResponseTemplate(LJSONObject detail) {
		this.detail = detail;
	}

	/**
	 * 获取返回的Json数据
	 * 
	 * @return
	 */
	public JSONObject getReturn() {
		if (detail == null) {
			detail = new LJSONObject();
			detail.setResult(Consts.RESULT_FAILD);
		} else {
			if (ErrorCode.SUCCESS.getDescription().equals(detail.getString(Consts.LABEL_ERROR_CODE))) {
				detail.setResult(Consts.RESULT_SUCCESS);
			} else {
				detail.setResult(Consts.RESULT_FAILD);
			}
		}
		return detail;
	}

}
