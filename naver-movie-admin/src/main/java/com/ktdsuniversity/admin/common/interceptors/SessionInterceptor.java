package com.ktdsuniversity.admin.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktdsuniversity.admin.mbr.vo.MbrVO;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("[preHandle SessionInterceptor]");
		logger.info("[request contextPath / method / requestURI: "
				+ request.getContextPath() +" / "+ request.getMethod() +" / "+ request.getRequestURI() + "]");
		
		HttpSession session = request.getSession(); // Session 객체를 얻어온다.
		// 세션 로그인 정보를 얻어온다.
		MbrVO member = (MbrVO) session.getAttribute("__ADMIN__");
		
		if (member == null) {
			// 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		return true; // Controller 실행을 계속한다.
	}

}
