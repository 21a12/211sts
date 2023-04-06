package com.ktdsuniversity.admin.mv.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.admin.mv.vo.MvVO;

@Repository
public class MvDAOImpl extends SqlSessionDaoSupport implements MvDAO {

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int createNewMv(MvVO mvVO) {
		return getSqlSession().insert("Mv.createNewMv", mvVO);
	}

	@Override
	public int updateOneMv(MvVO mvVO) {
		return getSqlSession().update("Mv.updateOneMv", mvVO);
	}

	@Override
	public int deleteOneMvByMvId(String mvId) {
		return getSqlSession().update("Mv.deleteOneMvByMvId", mvId);
	}

	@Override
	public int deleteMvIdList(List<String> mvIdList) {
		return getSqlSession().delete("Mv.deleteMvIdList", mvIdList);
	}

	@Override
	public MvVO readOneMvByMvId(String mvId) {
		return getSqlSession().selectOne("Mv.readOneMvByMvId", mvId);
	}

	@Override
	public List<MvVO> readAllMv(MvVO mvVO) {
		return getSqlSession().selectList("Mv.readAllMv", mvVO);
	}
	
}
