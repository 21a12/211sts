package com.ktdsuniversity.admin.mv.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.admin.common.api.vo.ApiResponseVO;
import com.ktdsuniversity.admin.common.api.vo.ApiStatus;
import com.ktdsuniversity.admin.mbr.vo.MbrVO;
import com.ktdsuniversity.admin.mv.service.MvService;
import com.ktdsuniversity.admin.mv.vo.MvVO;

@RestController
public class RestMvController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestMvController.class);

	@Autowired
	private MvService mvService;
	
	@PostMapping("/api/mv/create")
	public ApiResponseVO createNewMv(MvVO mvVO
					, @SessionAttribute("__ADMIN__") MbrVO mbrVO
					, MultipartFile uploadFile) {
		
		mvVO.setCrtr(mbrVO.getMbrId());
		mvVO.setMdfyr(mbrVO.getMbrId());
		
		boolean createResult = mvService.createNewMv(mvVO, uploadFile);
		if (createResult) {
									// 등록에 성공했다면, 그 영화의 상세보기로 이동
			return new ApiResponseVO(ApiStatus.OK, "/mv/detail/" + mvVO.getMvId());
		} else {
			return new ApiResponseVO(ApiStatus.FAIL);
		}
	}
	
	@PostMapping("/api/mv/update")
	public ApiResponseVO updateOneMv(MvVO mvVO
			, @SessionAttribute("__ADMIN__") MbrVO mbrVO
			, MultipartFile uploadFile) {
		
		mvVO.setCrtr(mbrVO.getMbrId());
		mvVO.setMdfyr(mbrVO.getMbrId());
		
		boolean updateResult = mvService.updateOneMv(mvVO, uploadFile);
		if (updateResult) {
			// 등록에 성공했다면, 그 영화의 상세보기로 이동
			return new ApiResponseVO(ApiStatus.OK, "/mv/detail/" + mvVO.getMvId());
		} else {
			return new ApiResponseVO(ApiStatus.FAIL);
		}
	}
	
}
