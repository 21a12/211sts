package com.ktdsuniversity.admin.gnr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.admin.common.api.exceptions.ApiException;
import com.ktdsuniversity.admin.gnr.dao.GnrDAO;
import com.ktdsuniversity.admin.gnr.vo.GnrVO;

@Service
public class GnrServiceImpl implements GnrService {

	@Autowired
	private GnrDAO gnrDAO;
	
	@Override
	public List<GnrVO> readAll(GnrVO gnrVO) {
		return gnrDAO.readAll(gnrVO);
	}
	
	@Override
	public List<GnrVO> readAllGnrVONoPagination(String gnrNm) {
		return gnrDAO.readAllGnrVONoPagination(gnrNm);
	}

	@Override
	public boolean readOneByGnrNm(String gnrNm) {
		return gnrDAO.readOneByGnrNm(gnrNm) > 0;
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

	@Override
	public boolean deleteSelectAll(List<Integer> gnrId) {
		
		int delCount = 	gnrDAO.deleteSelectAll(gnrId); 
		boolean isSuccess = delCount == gnrId.size();
		if (!isSuccess) {
			throw new ApiException("500", "일괄 삭제 처리에 실패했습니다. 요청건수:"+gnrId.size()+", 삭제건수:"+delCount);
		}
		
		return isSuccess;
	}



}
