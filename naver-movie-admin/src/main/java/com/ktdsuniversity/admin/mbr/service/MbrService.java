package com.ktdsuniversity.admin.mbr.service;

import java.util.List;

import com.ktdsuniversity.admin.mbr.vo.MbrVO;

public interface MbrService {

	public MbrVO readOneMbrByIdAndPwd(MbrVO mbrVO);
	public List<MbrVO> readAllAdminMbr();
	public boolean createNewAdminMbr(MbrVO mbrVO);
	public boolean updateOneAdminMbr(MbrVO mbrVO);
	public boolean deleteOneAdminMbr(String mbrId);
	
}
