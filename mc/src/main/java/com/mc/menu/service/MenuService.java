package com.mc.menu.service;

import java.util.List;

import com.mc.menu.vo.MenuVO;

public interface MenuService {

	public boolean create(MenuVO menuVO);
	public boolean update(MenuVO menuVO);
	public boolean delete(boolean menuId);
	public List<MenuVO> readAll();
	public MenuVO readOne(int menuId);

	
}
