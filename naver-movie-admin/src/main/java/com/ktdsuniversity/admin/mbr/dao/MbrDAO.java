package com.ktdsuniversity.admin.mbr.dao;

import java.util.List;

import com.ktdsuniversity.admin.mbr.vo.MbrVO;

public interface MbrDAO {

	public MbrVO readOneMbrByIdAndPwd(MbrVO mbrVO);
	public List<MbrVO> readAllAdminMbr();
	public int createNewAdminMbr(MbrVO mbrVO);
	public int updateOneAdminMbr(MbrVO mbrVO);
	public int deleteOneAdminMbr(String mbrId);
	
}
