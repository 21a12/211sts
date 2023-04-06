package com.ktdsuniversity.admin.mvppl.web;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.admin.common.api.exceptions.ApiArgsException;
import com.ktdsuniversity.admin.common.api.exceptions.ApiException;
import com.ktdsuniversity.admin.common.api.vo.ApiResponseVO;
import com.ktdsuniversity.admin.common.api.vo.ApiStatus;
import com.ktdsuniversity.admin.gnr.vo.GnrVO;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;
import com.ktdsuniversity.admin.mvppl.service.MvPplService;
import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

@RestController
public class RestMvPplController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestMvPplController.class);
	
	@Autowired
	private MvPplService mvPplService;
	
	
	
	@PostMapping("/api/mvppl/create")
	public ApiResponseVO doCreateMvPpl(MvPplVO mvPplVO
									, MultipartFile uploadFile
									, @SessionAttribute("__ADMIN__") MbrVO mbrVO) {
		
		mvPplVO.setCrtr(mbrVO.getMbrId());
		mvPplVO.setMdfyr(mbrVO.getMbrId());
		
		boolean isSuccess = mvPplService.createOneMvPpl(mvPplVO, uploadFile);
		if (isSuccess) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		else {
			return new ApiResponseVO(ApiStatus.FAIL);
		}
	}
	
	@PostMapping("/api/mvppl/update")
	public ApiResponseVO doUpdateMvPpl(MvPplVO mvPplVO
									, MultipartFile uploadFile
									, @SessionAttribute("__ADMIN__") MbrVO mbrVO) {
		
//		String nm = mvPplVO.getNm();
//		String rlNm = mvPplVO.getRlNm();
//		
//		if (nm == null || nm.trim().length() == 0) {
//			throw new ApiArgsException("400", "이름이 누락되었습니다.");
//		}
//		if (rlNm == null || rlNm.trim().length() == 0) {
//			throw new ApiArgsException("400", "본명이 누락되었습니다.");
//		}
		
		mvPplVO.setMdfyr(mbrVO.getMbrId());
		if (mvPplVO.getNm()==null || mvPplVO.getNm().replace(" ", "").length()==0) {
			logger.debug("이름누락 돌앗나");
			throw new ApiException(ApiStatus.FAIL, "이름누락맨이야");
		}
		
		
		boolean isSuccess = mvPplService.updateOneMvPpl(mvPplVO, uploadFile);
		if (isSuccess) {
			return new ApiResponseVO(ApiStatus.OK);
		}
		return new ApiResponseVO(ApiStatus.FAIL);
	}
	
	@GetMapping("/api/mvppl/delete/{mvPplId}")
	public ApiResponseVO doDeleteMvPpl(@PathVariable int mvPplId) {
		return null;
	}
	
	

}
