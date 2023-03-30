package com.ktdsuniversity.admin.mbr.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.mbr.vo.MbrVO;

@Repository
public class MbrDAOImpl extends SqlSessionDaoSupport implements MbrDAO {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public MbrVO readOneMbrByIdAndPwd(MbrVO mbrVO) {
		return getSqlSession().selectOne("Mbr.readOneMbrByIdAndPwd", mbrVO);
	}

	@Override
	public List<MbrVO> readAllAdminMbr() {
		return getSqlSession().selectList("Mbr.readAllAdminMbr");
	}

	@Override
	public int createNewAdminMbr(MbrVO mbrVO) {
		return getSqlSession().insert("Mbr.createNewAdminMbr", mbrVO);
	}

	@Override
	public int updateOneAdminMbr(MbrVO mbrVO) {
		return getSqlSession().update("Mbr.updateOneAdminMbr", mbrVO);
	}

	@Override
	public int deleteOneAdminMbr(String mbrId) {
		return getSqlSession().update("Mbr.deleteOneAdminMbr", mbrId);
	}

}
