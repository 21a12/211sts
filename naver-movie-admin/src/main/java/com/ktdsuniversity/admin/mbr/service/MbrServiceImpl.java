package com.ktdsuniversity.admin.mbr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.admin.common.api.exceptions.ApiException;
import com.ktdsuniversity.admin.common.util.SHA256Util;
import com.ktdsuniversity.admin.mbr.dao.MbrDAO;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;
import com.ktdsuniversity.admin.mbrlgnhist.dao.MbrLgnHistDAO;
import com.ktdsuniversity.admin.mbrlgnhist.vo.MbrLgnHistVO;

@Service
public class MbrServiceImpl implements MbrService {

	@Autowired
	private MbrDAO mbrDAO;

	@Autowired
	private MbrLgnHistDAO mbrLgnHistDAO;
	
	@Override
	public MbrVO readOneMbrByIdAndPwd(MbrVO mbrVO) {

		String lgnBlockYn = mbrDAO.readLgnBlockYnById(mbrVO.getMbrId());
		if (lgnBlockYn == null) {
			throw new ApiException("403", "ID 또는 비밀번호가 일치하지 않습니다.");
		}
		else if (lgnBlockYn.equals("Y")) {
			throw new ApiException("403", "계정이 잠겼습니다. 관리자에게 문의하세요");
		}
		
		String salt = mbrDAO.readSaltMbrById(mbrVO.getMbrId());
		if (salt == null) {
			throw new ApiException("403", "ID 또는 비밀번호가 일치하지 않습니다.");
		}
		
		String pwd = mbrVO.getPwd();
		pwd = SHA256Util.getEncrypt(pwd, salt);
		mbrVO.setPwd(pwd);
		
		MbrVO loginData = mbrDAO.readOneMbrByIdAndPwd(mbrVO);
		if (loginData == null) {
			// 실패
			// 로그인 실패 횟수 증가
			mbrDAO.updateMbrLgnFail(mbrVO);
			// 로그인 제한하기
			mbrDAO.updateMbrLgnBlock(mbrVO);
		}
		else {
			// 성공
			mbrDAO.updateMbrLgnSucc(mbrVO);
			MbrLgnHistVO lgnHistVO = new MbrLgnHistVO();
			lgnHistVO.setMbrId(mbrVO.getMbrId());
			lgnHistVO.setAct("login");
			lgnHistVO.setIp(mbrVO.getLstLgnIp());
			mbrLgnHistDAO.createMbrLgnHist(lgnHistVO);
		}
		
		return loginData;
	}

	@Override
	public List<MbrVO> readAllAdminMbr() {
		return mbrDAO.readAllAdminMbr();
	}

	@Override
	public boolean createNewAdminMbr(MbrVO mbrVO) {
		
		int mbrCount = this.readCountMbrById(mbrVO.getMbrId());
		if (mbrCount > 0) {
			throw new ApiException("500", "이미 사용중인 ID입니다.");
		}
		
		String defaultPassword = "admin1234";
		String salt = SHA256Util.generateSalt();
		mbrVO.setPwdSalt(salt);
		
		defaultPassword = SHA256Util.getEncrypt(defaultPassword, salt);
		mbrVO.setPwd(defaultPassword);
		
		try {
			return mbrDAO.createNewAdminMbr(mbrVO) > 0;
		} catch(RuntimeException re) {
			throw new ApiException("500", "이미 사용중인 ID입니다.");
		}
		
	}

	@Override
	public boolean updateOneAdminMbr(MbrVO mbrVO) {
		return mbrDAO.updateOneAdminMbr(mbrVO) > 0;
	}

	@Override
	public boolean deleteOneAdminMbr(String mbrId) {
		return mbrDAO.deleteOneAdminMbr(mbrId) > 0;
	}

	@Override
	public int readCountMbrById(String mbrId) {
		return mbrDAO.readCountMbrById(mbrId);
	}

	@Override
	public int logout(MbrLgnHistVO mbrLgnHistVO) {
		return mbrLgnHistDAO.createMbrLgnHist(mbrLgnHistVO);
	}
	
}
