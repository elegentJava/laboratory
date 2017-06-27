package com.bupt.ltb.common.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.bupt.ltb.common.util.Consts;

/**
 * 请求JSON模板
 * 
 * @author Hogan
 * 
 */
public class RequestTemplate {

	private HttpServletRequest request;

	/**
	 * 请求参数全部封装到params中
	 */
	public JSONObject params = null;

	public RequestTemplate(HttpServletRequest request) {
		this.request = request;
	}

	public RequestTemplate(JSONObject reqJson) {
		if (reqJson != null) {
			params = reqJson.getJSONObject(Consts.LABEL_PARAMS);
		}
	}

	/**
	 * 获取请求参数集
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		Enumeration<?> pNames = request.getParameterNames();
		while (pNames.hasMoreElements()) {
			String name = (String) pNames.nextElement();
			Object value = request.getParameter(name);
			paramsMap.put(name, value);
		}
		return paramsMap;
	}

	/**
	 * 获取JSON参数集
	 * 
	 * @return
	 */
	public JSONObject getJParams() {
		return params;
	}

}
