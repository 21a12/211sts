package com.ktdsuniversity.admin.gnr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.admin.common.api.exceptions.ApiArgsException;
import com.ktdsuniversity.admin.common.api.vo.ApiResponseVO;
import com.ktdsuniversity.admin.common.api.vo.ApiStatus;
import com.ktdsuniversity.admin.gnr.service.GnrService;
import com.ktdsuniversity.admin.gnr.vo.GnrVO;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;

@RestController
public class RestGnrController {

	@Autowired
	private GnrService gnrService;
	
	
	@GetMapping("/api/gnr/dup/{gnrId}")
	public ApiResponseVO doCheckDupMbrId(@PathVariable String gnrId) {
		
		boolean idCheck= gnrService.readOneByGnrNm(gnrId);
		
		if (idCheck) {
			return new ApiResponseVO(ApiStatus.FAIL);
		}
		return new ApiResponseVO(ApiStatus.OK);
	}
		
	

	@PostMapping("/api/gnr/create")
	public ApiResponseVO doCreateGnr(GnrVO gnrVO
			, @SessionAttribute("__ADMIN__") MbrVO mbrVO) {
		
		String gnrNm = gnrVO.getGnrNm();

		if (gnrNm == null || gnrNm.trim().length() == 0) {
			throw new ApiArgsException("400", "장르명이 누락되었습니다.");
		}
		
		gnrVO.setCrtr(mbrVO.getMbrId());
		gnrVO.setMdfyr(mbrVO.getMbrId());
		
		boolean isSuccess = gnrService.createOneGnr(gnrVO);
		if (isSuccess) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		return new ApiResponseVO(ApiStatus.FAIL);
	}
	
	@PostMapping("/api/gnr/update")
	public ApiResponseVO doUpdateGnr(GnrVO gnrVO
			, @SessionAttribute("__ADMIN__") MbrVO mbrVO) {
		
		String gnrNm = gnrVO.getGnrNm();
		
		if (gnrNm == null || gnrNm.trim().length() == 0) {
			throw new ApiArgsException("400", "장르명이 누락되었습니다.");
		}
		
		gnrVO.setMdfyr(mbrVO.getMbrId());
		
		boolean isSuccess = gnrService.updateOneGnr(gnrVO);
		if (isSuccess) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		return new ApiResponseVO(ApiStatus.FAIL);
	}
	
	@GetMapping("/api/gnr/delete/{gnrId}")
	public ApiResponseVO doDeleteGnr(@PathVariable int gnrId) {
		
		if (gnrId == 0) {
			throw new ApiArgsException("400", "삭제불가 : 장르ID 누락");
		}
		
		boolean isSuccess = gnrService.deleteOneGnr(gnrId);
		
		if (isSuccess) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		return new ApiResponseVO(ApiStatus.FAIL);
	}
	
	@PostMapping("/api/gnr/delete")
	public ApiResponseVO doDeleteSelectAll(@RequestParam List<Integer> gnrId) {
		boolean isSuccess = gnrService.deleteSelectAll(gnrId);
		
		if (isSuccess) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		return new ApiResponseVO(ApiStatus.FAIL);
	}
		
}
