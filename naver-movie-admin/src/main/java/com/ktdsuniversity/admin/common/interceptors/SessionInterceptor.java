package com.ktdsuniversity.admin.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktdsuniversity.admin.mbr.vo.MbrVO;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(); // Session 객체를 얻어온다.
		// 세션 로그인 정보를 얻어온다.
		MbrVO member = (MbrVO) session.getAttribute("__ADMIN__");
		
		if (member == null) {
			// 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/mbr/lgn");
			return false;
		}
		
		return true; // Controller 실행을 계속한다.
	}

}
