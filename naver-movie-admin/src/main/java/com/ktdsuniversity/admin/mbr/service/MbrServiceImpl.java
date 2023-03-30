package com.ktdsuniversity.admin.mbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.admin.common.util.SHA256Util;
import com.ktdsuniversity.admin.mbr.dao.MbrDAO;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;

@Service
public class MbrServiceImpl implements MbrService {

	@Autowired
	private MbrDAO mbrDAO;

	@Override
	public MbrVO readOneMbrByIdAndPwd(MbrVO mbrVO) {
		return mbrDAO.readOneMbrByIdAndPwd(mbrVO);
	}

	@Override
	public List<MbrVO> readAllAdminMbr() {
		return mbrDAO.readAllAdminMbr();
	}

	@Override
	public boolean createNewAdminMbr(MbrVO mbrVO) {
		
		String defaultPassword = "admin1234";
		String salt = SHA256Util.generateSalt();
		mbrVO.setPwdSalt(salt);
		
		defaultPassword = SHA256Util.getEncrypt(defaultPassword, salt);
		mbrVO.setPwd(defaultPassword);
		
		return mbrDAO.createNewAdminMbr(mbrVO) > 0;
	}

	@Override
	public boolean updateOneAdminMbr(MbrVO mbrVO) {
		return mbrDAO.updateOneAdminMbr(mbrVO) > 0;
	}

	@Override
	public boolean deleteOneAdminMbr(String mbrId) {
		return mbrDAO.deleteOneAdminMbr(mbrId) > 0;
	}
	
	
}
