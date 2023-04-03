package com.ktdsuniversity.admin.gnr.service;

import java.util.List;

import com.ktdsuniversity.admin.gnr.vo.GnrVO;

public interface GnrService {

	public List<GnrVO> readAll(GnrVO gnrVO);
	public boolean readOneByGnrNm(String gnrNm);
	
	public boolean createOneGnr(GnrVO gnrVO);
	public boolean updateOneGnr(GnrVO gnrVO);
	public boolean deleteOneGnr(int gnrId);
	public boolean deleteSelectAll(List<Integer> gnrId);
	
}
