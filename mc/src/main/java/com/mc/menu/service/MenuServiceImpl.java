package com.mc.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.menu.dao.MenuDAO;
import com.mc.menu.vo.MenuVO;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDAO menuDAO;
	
	@Override
	public boolean create(MenuVO menuVO) {
		return menuDAO.create(menuVO) > 0;
	}

	@Override
	public boolean update(MenuVO menuVO) {
		return menuDAO.update(menuVO) > 0;
	}

	@Override
	public boolean delete(int menuId) {
		return menuDAO.delete(menuId) > 0;
	}

	@Override
	public List<MenuVO> readAll() {
		return menuDAO.readAll();
	}

	@Override
	public MenuVO readOne(int menuId) {
		return menuDAO.readOne(menuId);
	}

}
