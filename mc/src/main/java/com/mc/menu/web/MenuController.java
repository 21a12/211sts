package com.mc.menu.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mc.menu.service.MenuService;
import com.mc.menu.vo.MenuVO;

@Controller
public class MenuController {
	
	@Value("${upload.path}")
	private String uploadPath;

	@Autowired
	private MenuService menuService;
	
	@GetMapping("/menu/home")
	public String viewList(Model model) {
		List<MenuVO> menuList = menuService.readAll();
		model.addAttribute("menuList", menuList);
		return "menu/list";
	}
	
	@GetMapping("/menu/create")
	public String doMenuCreate() {
		return "menu/create";
	}
	
	@PostMapping("/menu/create")
	public String doMenuCreate(MenuVO menuVO) {
		boolean createResult = menuService.create(menuVO);
		if (createResult) {
			return "redirect:/menu/home";
		}
		return "menu/create";
	}
	
}
