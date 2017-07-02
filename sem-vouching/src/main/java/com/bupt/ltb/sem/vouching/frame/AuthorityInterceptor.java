package com.bupt.ltb.sem.vouching.frame;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 权限切面
 * 
 * @author Hogan
 *
 */
@Component
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		System.out.println(handler);
		if (session.getAttribute(Consts.SESSION_USER) == null) {
			request.getRequestDispatcher("/views/front/login.jsp").forward(request, response);
		}
		return super.preHandle(request, response, handler);
	}

}
