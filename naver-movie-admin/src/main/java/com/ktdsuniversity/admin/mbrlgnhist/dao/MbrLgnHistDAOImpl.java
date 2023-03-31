package com.ktdsuniversity.admin.mbrlgnhist.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.mbrlgnhist.vo.MbrLgnHistVO;

@Repository
public class MbrLgnHistDAOImpl extends SqlSessionDaoSupport implements MbrLgnHistDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int createMbrLgnHist(MbrLgnHistVO mbrLgnHistVO) {
		return getSqlSession().insert("MbrLgnHist.createMbrLgnHist", mbrLgnHistVO);
	}
	
	
	
}
