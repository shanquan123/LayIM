package com.pers.yefei.LayIM.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);
	
	private static final String LOGIN_URL = "/admin/login/to_login";
	private static final String SUPER_ADMIN_LOGIN_URL = "/admin/login/to/super_admin";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		try {
			String contextPath = request.getContextPath();
			String requestUri = request.getRequestURI();

			return true;
		} catch (Exception e ) {
			LOGGER.error(e.toString(), e);
		}
		return false;
	}
	


}
