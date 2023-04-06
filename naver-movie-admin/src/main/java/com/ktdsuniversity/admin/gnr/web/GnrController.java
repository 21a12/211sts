package com.ktdsuniversity.admin.gnr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.admin.gnr.service.GnrService;
import com.ktdsuniversity.admin.gnr.vo.GnrVO;

@Controller
public class GnrController {

	@Autowired
	private GnrService gnrService;
	
	@GetMapping("/gnr/list") // http://localhost:8080/admin/gnr/list?gnrNm=장르명&pageNo=2&viewCnt=10
	public String viewGnrLIstPage(Model model, GnrVO gnrVO) {
		List<GnrVO> gnrList = gnrService.readAll(gnrVO);
		model.addAttribute("gnrList", gnrList);
		model.addAttribute("gnrVO", gnrVO);
//		model.addAttribute("gnrNm", gnrVO.getGnrNm());
//		model.addAttribute("pageNo", gnrVO.getPageNo());
//		model.addAttribute("viewCnt", gnrVO.getViewCnt());
		
		return "gnr/list";
	}
	
	@GetMapping("/gnr/search")
	public String viewGnrSearchPage(
			@RequestParam(required = false) String gnrNm
			, Model model) {
		model.addAttribute("gnrNm", gnrNm);
		if(gnrNm != null && gnrNm.length() > 0) {
			List<GnrVO> gnrList = gnrService.readAllGnrVONoPaginationi(gnrNm);
			model.addAttribute("gnrList",gnrList);
		}
		
		return "gnr/search";
	}
	
}
