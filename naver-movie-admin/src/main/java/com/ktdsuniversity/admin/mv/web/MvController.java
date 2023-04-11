package com.ktdsuniversity.admin.mv.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	@GetMapping("/mv/detail/{mvId}")
	public String viewMvDetailPage(@PathVariable String mvId, Model model) {
		MvVO mvVO = mvService.readOneMvByMvId(mvId);
		model.addAttribute("mvVO", mvVO);
		return "mv/detail";
	}
	
	
}
