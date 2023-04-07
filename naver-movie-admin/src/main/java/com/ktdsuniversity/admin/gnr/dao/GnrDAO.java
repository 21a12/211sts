package com.ktdsuniversity.admin.gnr.dao;

import java.util.List;

import com.ktdsuniversity.admin.gnr.vo.GnrVO;

public interface GnrDAO {

	public List<GnrVO> readAll(GnrVO gnrVO);
	public List<GnrVO> readAllGnrVONoPagination(String gnrNm);
	
	public int readOneByGnrNm(String gnrNm);
	public int createOneGnr(GnrVO gnrVO);
	public int updateOneGnr(GnrVO gnrVO);
	public int deleteOneGnr(int gnrId);
	public int deleteSelectAll(List<Integer> gnrId);
}
