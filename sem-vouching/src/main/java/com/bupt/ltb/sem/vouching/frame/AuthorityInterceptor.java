package com.bupt.ltb.sem.vouching.frame;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 权限拦截器
 * 
 * @author Hogan
 *
 */
@Component
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	private static final String FRONT_LOGIN_PATH = "forwardFrontLogin";
	private static final String BACK_LOGIN_PATH = "forwardBackLogin";
	private static final String LOGIN_PATH = "user/login";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURL().toString();
		if (url.contains(FRONT_LOGIN_PATH) || url.contains(BACK_LOGIN_PATH) || url.contains(LOGIN_PATH)) {
			return true;
		}
		HttpSession session = request.getSession();
		if (session.getAttribute(Consts.SESSION_USER) == null) {
			request.getRequestDispatcher("/WEB-INF/views/front/login.jsp").forward(request, response);
			return false;
		}
		return true;
	}

}
