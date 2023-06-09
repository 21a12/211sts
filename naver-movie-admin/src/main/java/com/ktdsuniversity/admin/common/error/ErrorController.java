package com.ktdsuniversity.admin.common.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

	@RequestMapping("/error/404")
	public String Error404(HttpServletRequest request, HttpServletResponse response) {
		logger.info("404 에러임");
		logger.info("[request contextPath / method / requestURI: "
				+ request.getContextPath() +" / "+ request.getMethod() +" / "+ request.getRequestURI() + "]");
		
		return "error/404";
	}
			
	@RequestMapping("/error/405")
	public String Error405(HttpServletRequest request, HttpServletResponse response) {
		logger.info("405 에러임");
		logger.info("[request contextPath / method / requestURI: "
				+ request.getContextPath() +" / "+ request.getMethod() +" / "+ request.getRequestURI() + "]");
		
		return "error/405";
	}
	
}
