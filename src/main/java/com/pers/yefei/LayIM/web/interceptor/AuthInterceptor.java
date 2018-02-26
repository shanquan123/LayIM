package com.pers.yefei.LayIM.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pers.yefei.LayIM.component.bean.SessionManager;
import com.pers.yefei.LayIM.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);
	
	private static final String LOGIN_URL = "/rest/login/login.html";

	@Autowired
	private SessionManager sessionManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		try {
			String contextPath = request.getContextPath();
			String requestUri = request.getRequestURI();

			User user = sessionManager.getUserFromSession(request);
			if (user == null){
				response.sendRedirect(contextPath + LOGIN_URL);
				return false;
			}


			return true;
		} catch (Exception e ) {
			LOGGER.error(e.toString(), e);
		}
		return false;
	}
	


}
