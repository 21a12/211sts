package com.ktdsuniversity.admin.mvppl.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.admin.common.api.exceptions.ApiException;
import com.ktdsuniversity.admin.mvppl.dao.MvPplDAO;
import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

@Service
public class MvPplServiceImpl implements MvPplService {

	private static final Logger logger = LoggerFactory.getLogger(MvPplServiceImpl.class);
	
	@Autowired
	private MvPplDAO mvPplDAO;
	
	@Value("${upload.profile.path:/naver-movie-admin/files/profiles}")
	private String profilePath;
	
	@Override
	public List<MvPplVO> readAllMvPpl(MvPplVO mvPplVO) {

		// TODO
		// 조회 시 조회관련 기간(시작-끝)이 필요하여 날짜데이터 전달을 핸들링
		// Calender
		// startDt가 비어있을 경우, 현재일의 한달 전 날짜를 가져와서 세팅한다.
		if (mvPplVO.getStartDt() == null || mvPplVO.getStartDt().length() == 0) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1); // startDt를 위하여 한달전을 계산
				
			int year = cal.get(Calendar.YEAR); // 연도
			int month = cal.get(Calendar.MONDAY) + 1; // 월 **(0~11)
			int day = cal.get(Calendar.DAY_OF_MONTH); // 일
			// Calendar로 불러온 날짜 예시 -> 2023-4-4 로 한자리수의 0은 출력안됨
			// 한자리수도 0포함된 2자리수로 변경을 위해 String으로 변환
			String strMonth = month < 10 ? "0" + month : "" + month;
			String strDay = day < 10 ? "0" + day : "" + day;
			String startDt = year + "-" + strMonth + "-" + strDay;
			mvPplVO.setStartDt(startDt);
		}
		// endDt가 비어있을 경우, 현재일을 가져와서 세팅한다.
		if (mvPplVO.getEndDt() == null || mvPplVO.getEndDt().length() == 0) {
			Calendar cal = Calendar.getInstance();
				
			int year = cal.get(Calendar.YEAR); // 연도
			int month = cal.get(Calendar.MONDAY) + 1; // 월 **(0~11)
			int day = cal.get(Calendar.DAY_OF_MONTH); // 일
			// Calendar로 불러온 날짜 예시 -> 2023-4-4 로 한자리수의 0은 출력안됨
			// 한자리수도 0포함된 2자리수로 변경을 위해 String으로 변환
			String strMonth = month < 10 ? "0" + month : "" + month;
			String strDay = day < 10 ? "0" + day : "" + day;
			String endDt = year + "-" + strMonth + "-" + strDay;
			mvPplVO.setEndDt(endDt);
		}
		
		
		// Jodatime // 사용시 메모리 이슈로 현재 사용하지 않을꺼임 기능이 있다 확인정도만

		return mvPplDAO.readAllMvPpl(mvPplVO);
	}

	@Override
	public boolean createOneMvPpl(MvPplVO mvPplVO, MultipartFile uploadFile) {

		if (uploadFile != null && !uploadFile.isEmpty()) {
			
			File dir = new File(profilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			String uuidFileName = UUID.randomUUID().toString();
			File profileFile = new File(dir, uuidFileName);
			try {
				uploadFile.transferTo(profileFile);
			} catch (IllegalStateException | IOException e) {
				logger.error(e.getMessage(), e);
			}
			
			mvPplVO.setPrflPctr(uuidFileName);
		}
		
		return mvPplDAO.createOneMvPpl(mvPplVO) > 0;
	}

	@Override
	public boolean updateOneMvPpl(MvPplVO mvPplVO, MultipartFile uploadFile) {

		if (uploadFile != null && !uploadFile.isEmpty()) {
			
			File dir = new File(profilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			String uuidFileName = UUID.randomUUID().toString();
			File profileFile = new File(dir, uuidFileName);
			try {
				uploadFile.transferTo(profileFile);
			} catch (IllegalStateException | IOException e) {
				logger.error(e.getMessage(), e);
			}
			
			mvPplVO.setPrflPctr(uuidFileName);
		}
		
		boolean isModify = false;
		// 수정데이터와 원본데이터를 비교하기 위하여 원본데이터를 호출해옴
		MvPplVO originalMvPplData = mvPplDAO.readOneMvPplVOByMvPplId(mvPplVO.getMvPplId());
		MvPplVO updateMvPplVO = new MvPplVO();
		updateMvPplVO.setMdfyr(mvPplVO.getMdfyr());
		updateMvPplVO.setMvPplId(mvPplVO.getMvPplId());
		updateMvPplVO.setRlNm(mvPplVO.getRlNm());
		
		
		if ((mvPplVO.getPrflPctr() == null
				|| mvPplVO.getPrflPctr().length() == 0)
				&& mvPplVO.getIsDeletePctr().equals("N")) {
			updateMvPplVO.setPrflPctr(originalMvPplData.getPrflPctr());
		} else {
			updateMvPplVO.setPrflPctr(mvPplVO.getPrflPctr());
			isModify = true;
		}
		
		
		if (!originalMvPplData.getNm().equals(mvPplVO.getNm())) {
			logger.debug("이름비교했다");
			isModify = true;
			updateMvPplVO.setNm(mvPplVO.getNm());
		}
		String rlNm = originalMvPplData.getRlNm();
		if (rlNm == null) { //오리진 이름이 널 이었을 때, 널포인터익셉션을 방
			rlNm = "";
		}
		if (!rlNm.equals(mvPplVO.getRlNm())) {
			logger.debug("리얼이름비교했다");
			isModify = true;
		}
//		if (!originalMvPplData.getPrflPctr().equals(mvPplVO.getPrflPctr())) {
//			isModify = true;
//		}
		if (!originalMvPplData.getUseYn().equals(mvPplVO.getUseYn())) {
			logger.debug("사용비교했다");
			isModify = true;
			updateMvPplVO.setUseYn(mvPplVO.getUseYn());
		}
		
		if (isModify) {
			return mvPplDAO.updateOneMvPpl(updateMvPplVO) > 0;
		} else {
			throw new ApiException("400", "변경된 정보 없음");
		}
		
		
	}

	@Override
	public boolean deleteOneMyPpl(String mvPplId) {
		return mvPplDAO.deleteOneMyPpl(mvPplId) > 0;
	}

	@Override
	public boolean deleteSelectAll(List<String> mvPplIdList) {

		int delCount = mvPplDAO.deleteSelectAll(mvPplIdList);
		boolean isSuccess = delCount == mvPplIdList.size();
		if (!isSuccess) {
			throw new ApiException("500", "일괄 삭제 처리에 실패했습니다. 요청건수:" + mvPplIdList.size() + ", 삭제건수:" + delCount);
		}

		return isSuccess;
	}

}
