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
	public int readCountMbrById(String mbrId) {
		return getSqlSession().selectOne("Mbr.readCountMbrById", mbrId);
	}
	
	@Override
	public String readSaltMbrById(String mbrId) {
		return getSqlSession().selectOne("Mbr.readSaltMbrById", mbrId);
	}

	@Override
	public String readLgnBlockYnById(String mbrId) {
		return getSqlSession().selectOne("Mbr.readLgnBlockYnById", mbrId);
	}	
	
	@Override
	public int updateMbrLgnSucc(MbrVO mbrVO) {
		return getSqlSession().update("Mbr.updateMbrLgnSucc", mbrVO);
	}

	@Override
	public int updateMbrLgnFail(MbrVO mbrVO) {
		return getSqlSession().update("Mbr.updateMbrLgnFail", mbrVO);
	}

	@Override
	public int updateMbrLgnBlock(MbrVO mbrVO) {
		return getSqlSession().update("Mbr.updateMbrLgnBlock", mbrVO);
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
