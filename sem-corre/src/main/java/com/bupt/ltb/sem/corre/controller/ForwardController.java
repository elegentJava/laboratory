package com.bupt.ltb.sem.corre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 转发控制层
 * 
 * @author Hogan
 * @date 2017年6月20日
 */
@Controller
@RequestMapping("/f")
public class ForwardController {

	private static final String PATH_CORRE = "/corre";

	@RequestMapping("/corre")
	public String forwardCorre() {
		return PATH_CORRE;
	}
}
