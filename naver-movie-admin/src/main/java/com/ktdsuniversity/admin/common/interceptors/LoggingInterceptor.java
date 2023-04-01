package com.ktdsuniversity.admin.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggingInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request
							, HttpServletResponse response
							, Object handler)
			throws Exception {
		
		logger.info("[Controller Medthod 호출 전]");
		logger.info("[" + request.getMethod() + "]");
		logger.info("[" + request.getRequestURI() + "]");
		
		return true;
//		return super.preHandle(request, response, handler);
	}

}
