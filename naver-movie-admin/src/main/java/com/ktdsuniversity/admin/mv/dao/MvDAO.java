package com.ktdsuniversity.admin.mv.dao;

import java.util.List;

import com.ktdsuniversity.admin.mv.vo.MvVO;

public interface MvDAO {

	public int createNewMv(MvVO mvVO);
	public int updateOneMv(MvVO mvVO);
	public int deleteOneMvByMvId(String mvId);
	public int deleteMvIdList(List<String> mvIdList);
	public MvVO readOneMvByMvId(String mvId);
	public List<MvVO> readAllMv(MvVO mvVO);
	
}
