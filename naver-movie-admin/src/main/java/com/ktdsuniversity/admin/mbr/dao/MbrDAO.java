package com.ktdsuniversity.admin.mbr.dao;

import java.util.List;

import com.ktdsuniversity.admin.mbr.vo.MbrVO;

public interface MbrDAO {
	
	public MbrVO readOneMbrByIdAndPwd(MbrVO mbrVO);
	// 중복검사용
	public int readCountMbrById(String mbrId);
	// salt 정보만 가져오기
	public String readSaltMbrById(String mbrId);
	public String readLgnBlockYnById(String mbrId);
	
	// 성공 관련
	public int updateMbrLgnSucc(MbrVO mbrVO);
	
	// 실패 관련 횟수증가/차단여부
	public int updateMbrLgnFail(MbrVO mbrVO);
	public int updateMbrLgnBlock(MbrVO mbrVO);
	
	public List<MbrVO> readAllAdminMbr();
	public int createNewAdminMbr(MbrVO mbrVO);
	public int updateOneAdminMbr(MbrVO mbrVO);
	public int deleteOneAdminMbr(String mbrId);
	
}
