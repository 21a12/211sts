package com.ktdsuniversity.admin.mvppl.dao;

import java.util.List;

import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

public interface MvPplDAO {
	
	public List<MvPplVO> readAllMvPpl(MvPplVO mvPplVO);
	public MvPplVO readOneMvPplVOByMvPplId(String mvPplId);
	
	public int createOneMvPpl(MvPplVO mvPplVO);
	public int updateOneMvPpl(MvPplVO mvPplVO);
	public int deleteOneMyPpl(String mvPplId);
	public int deleteSelectAll(List<String> mvPplIdList);
	
}
