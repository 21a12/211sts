package com.mc.menu.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mc.menu.vo.MenuVO;

@Repository
public class MenuDAOImpl extends SqlSessionDaoSupport implements MenuDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int create(MenuVO menuVO) {
		return getSqlSession().insert("Menu.create", menuVO);
	}

	@Override
	public int update(MenuVO menuVO) {
		return getSqlSession().update("Menu.update", menuVO);
	}

	@Override
	public int delete(int menuId) {
		return getSqlSession().delete("Menu.delete", menuId);
	}

	@Override
	public List<MenuVO> readAll() {
		return getSqlSession().selectList("Menu.readAll");
	}

	@Override
	public MenuVO readOne(int menuId) {
		return getSqlSession().selectOne("Menu.readOne", menuId);
	}

}
