package com.ktdsuniversity.admin.mbr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.admin.mbr.service.MbrService;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;

@Controller
public class MbrController {

	@Autowired
	private MbrService mbrService;
	
	@GetMapping("/")
	public String viewAdminLoginPage() {
		return "mbr/lgn";
	}
	
	@GetMapping("/mbr/list")
	public String viewMbrListPage(Model model) {
		List<MbrVO> mbrList = mbrService.readAllAdminMbr();
		model.addAttribute("mbrList", mbrList);
		return "mbr/list";
	}
	
}
