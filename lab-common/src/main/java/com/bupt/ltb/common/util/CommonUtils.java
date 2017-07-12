package com.bupt.ltb.common.util;

import java.util.UUID;

/**
 * 通用工具
 * 
 * @author Hogan
 *
 */
public class CommonUtils {

	/**
	 * 获取32位的UUID
	 * 
	 * @return
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}