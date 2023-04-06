package com.ktdsuniversity.admin.mvgnr.dao;

import com.ktdsuniversity.admin.mvgnr.vo.MvGnrVO;

public interface MvGnrDAO {
	
	public int createNewMvGnr(MvGnrVO mvGnrVO);
	public int deleteOneMvGnrIdAndMvId(MvGnrVO mvGnrVO);

}
