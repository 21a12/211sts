package com.ktdsuniversity.admin.mvppl.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

@Repository
public class MvPplDAOImpl extends SqlSessionDaoSupport implements MvPplDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<MvPplVO> readAllMvPpl(MvPplVO mvPplVO) {
		return getSqlSession().selectList("MvPpl.readAllMvPpl", mvPplVO);
	}
	
	@Override
	public List<MvPplVO> readAllMvPplNoPagination(String mvPplNm) {
		return getSqlSession().selectList("MvPpl.readAllMvPplNoPagination", mvPplNm);
	}
	

	@Override
	public MvPplVO readOneMvPplVOByMvPplId(String mvPplId) {
		return getSqlSession().selectOne("MvPpl.readOneMvPplVOByMvPplId", mvPplId);
	}
	
	
	
	@Override
	public int createOneMvPpl(MvPplVO mvPplVO) {
		return getSqlSession().insert("MvPpl.createOneMvPpl", mvPplVO);
	}

	@Override
	public int updateOneMvPpl(MvPplVO mvPplVO) {
		return getSqlSession().update("MvPpl.updateOneMvPpl", mvPplVO);
	}

	@Override
	public int deleteOneMyPpl(String mvPplId) {
		return getSqlSession().update("MvPpl.deleteOneMyPpl", mvPplId);
	}

	@Override
	public int deleteSelectAll(List<String> mvPplIdList) {
		return getSqlSession().update("MvPpl.deleteSelectAll", mvPplIdList);
	}

	
	
}
