package com.ktdsuniversity.admin.prdcprtcptnppl.dao;

import com.ktdsuniversity.admin.prdcprtcptnppl.vo.PrdcPrtcptnPplVO;

public interface PrdcPrtcptnPplDAO {
	
	public int createNewPrdcPrtcptnPpl(PrdcPrtcptnPplVO prdcPrtcptnPplVO);
	public int deleteOnePrdcPrtcptnPplByPrdcPrtcptnPplId(String prdcPrtcptnPplId);

}
