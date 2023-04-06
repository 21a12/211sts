package com.ktdsuniversity.admin.mv.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.admin.mv.service.MvService;
import com.ktdsuniversity.admin.mv.vo.MvVO;

@Controller
public class MvController {
	
	@Autowired
	private MvService mvService;

	
	@GetMapping("/mv/list")
	public String viewMvListPage(MvVO mvVO) {
		
		return "mv/list";
	}
	
	@GetMapping("/mv/create")
	public String viewMvCreatePage() {
		
		return "mv/create";
	}
	
	
}
