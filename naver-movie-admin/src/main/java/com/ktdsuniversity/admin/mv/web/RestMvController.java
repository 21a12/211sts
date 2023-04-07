package com.ktdsuniversity.admin.mv.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.admin.common.api.vo.ApiResponseVO;
import com.ktdsuniversity.admin.mv.service.MvService;
import com.ktdsuniversity.admin.mv.vo.MvVO;

@RestController
public class RestMvController {

	@Autowired
	private MvService mvService;
	
	@PostMapping("/api/mv/create")
	public ApiResponseVO createNewMv(MvVO mvVO) {
		return null;
	}
	
	
}
