package com.ktdsuniversity.admin.gnr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.admin.gnr.dao.GnrDAO;
import com.ktdsuniversity.admin.gnr.vo.GnrVO;

@Service
public class GnrServiceImpl implements GnrService {

	@Autowired
	private GnrDAO gnrDAO;
	
	@Override
	public List<GnrVO> readAll(String gnrNm) {
		return gnrDAO.readAll(gnrNm);
	}

	@Override
	public boolean createOneGnr(GnrVO gnrVO) {
		return gnrDAO.createOneGnr(gnrVO) > 0;
	}

	@Override
	public boolean updateOneGnr(GnrVO gnrVO) {
		return gnrDAO.updateOneGnr(gnrVO) > 0;
	}

	@Override
	public boolean deleteOneGnr(int gnrId) {
		return gnrDAO.deleteOneGnr(gnrId) > 0;
	}

}
