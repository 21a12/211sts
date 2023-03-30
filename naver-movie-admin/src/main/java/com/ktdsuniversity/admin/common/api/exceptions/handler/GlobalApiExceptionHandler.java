package com.ktdsuniversity.admin.common.api.exceptions.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ktdsuniversity.admin.common.api.exceptions.ApiArgsException;
import com.ktdsuniversity.admin.common.api.exceptions.ApiException;
import com.ktdsuniversity.admin.common.api.vo.ApiResponseVO;
import com.ktdsuniversity.admin.common.api.vo.ApiStatus;

@RestControllerAdvice("com.ktdsuniversity.admin")
public class GlobalApiExceptionHandler {
	
	@ExceptionHandler(ApiArgsException.class)
	public ApiResponseVO handleApiArgsException(ApiArgsException ae) {
		return new ApiResponseVO(ApiStatus.MISSING_ARGS, ae.getMessage(), ae.getErrorCode(), "");
	}
	
	@ExceptionHandler(ApiException.class)
	public ApiResponseVO handleApiException(ApiException ae) {
		return new ApiResponseVO(ApiStatus.FAIL, ae.getMessage(), ae.getErrorCode(), "");
	}

}
