package com.ktdsuniversity.admin.mbr.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.admin.common.api.exceptions.ApiArgsException;
import com.ktdsuniversity.admin.common.api.exceptions.ApiException;
import com.ktdsuniversity.admin.common.api.vo.ApiResponseVO;
import com.ktdsuniversity.admin.common.api.vo.ApiStatus;
import com.ktdsuniversity.admin.mbr.service.MbrService;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;

@RestController
public class RestMbrController {

	@Autowired
	private MbrService mbrService;

	@PostMapping("/api/mbr/lgn")
	public ApiResponseVO doLoginAdminMember(MbrVO mbrVO, HttpSession session, HttpServletRequest request) {

		if (mbrVO.getMbrId() == null || mbrVO.getMbrId().trim().length() == 0) {
			throw new ApiArgsException("400", "로그인 아이디는 필수값 입니다.");
		}
		if (mbrVO.getPwd() == null || mbrVO.getPwd().trim().length() == 0) {
			throw new ApiArgsException("400", "비밀번호는 필수값 입니다.");
		}
		
		mbrVO.setLstLgnIp(request.getRemoteAddr());
		

		MbrVO mbr = mbrService.readOneMbrByIdAndPwd(mbrVO);
		if (mbr == null) {
			throw new ApiException("403", "ID 또는 비밀번호가 일치하지 않습니다.");

		} else {
			session.setAttribute("__ADMIN__", mbr);
		}
		return new ApiResponseVO(ApiStatus.OK, "", "", "/index");
	}

	
	
	@GetMapping("/api/mbr/dup/{mbrId}")
	public ApiResponseVO doCheckDupMbrId(@PathVariable String mbrId) {
		
		int mbrCount = mbrService.readCountMbrById(mbrId);
		
		if (mbrCount == 0) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		return new ApiResponseVO(ApiStatus.FAIL);
	}
		
	@PostMapping("/api/mbr/create")
	public ApiResponseVO doCreateNewAdmin(MbrVO mbrVO) {
		
		String mbrId = mbrVO.getMbrId();
		String mbrNm = mbrVO.getMbrNm();

		if (mbrId == null || mbrId.trim().length() == 0) {
			throw new ApiArgsException("400", "ID가 누락되었습니다.");
		}
		if (mbrNm == null || mbrNm.trim().length() == 0) {
			throw new ApiArgsException("400", "이름이 누락되었습니다.");
		}
		
		boolean isSuccess = mbrService.createNewAdminMbr(mbrVO);
		if (isSuccess) {
			return new ApiResponseVO(ApiStatus.OK, "", "", "");
		}
		return new ApiResponseVO(ApiStatus.FAIL, "관리자를 등록할 수 없습니다.", "500", "");
	}
	
	@PostMapping("/api/mbr/update")
	public ApiResponseVO doUpdateAdmin(MbrVO mbrVO) {
		String mbrId = mbrVO.getMbrId();
		String mbrNm = mbrVO.getMbrNm();

		if (mbrId == null || mbrId.trim().length() == 0) {
			throw new ApiArgsException("400", "ID가 누락되었습니다.");
		}
		if (mbrNm == null || mbrNm.trim().length() == 0) {
			throw new ApiArgsException("400", "이름이 누락되었습니다.");
		}
		
		boolean isSuccess = mbrService.updateOneAdminMbr(mbrVO);
		if (isSuccess) {
			return new ApiResponseVO(ApiStatus.OK, "", "", "");
		}
		return new ApiResponseVO(ApiStatus.FAIL, "관리자를 수정할 수 없습니다.", "500", "");
	}
	
	@GetMapping("/api/mbr/delete/{mbrId}")
	public ApiResponseVO doDeleteAdmin(@PathVariable String mbrId) {
		boolean isSuccess = mbrService.deleteOneAdminMbr(mbrId);
		if (isSuccess) {
			return new ApiResponseVO(ApiStatus.OK, "", "", "");
		}
		return new ApiResponseVO(ApiStatus.FAIL, "관리자를 삭제할 수 없습니다.", "500", "");
	}
	
}
