package com.mc.menu.dao;

import java.util.List;

import com.mc.menu.vo.MenuVO;

public interface MenuDAO {

	public int create(MenuVO menuVO);
	public int update(MenuVO menuVO);
	public int delete(int menuId);
	public List<MenuVO> readAll();
	public MenuVO readOne(int menuId);

	
}
