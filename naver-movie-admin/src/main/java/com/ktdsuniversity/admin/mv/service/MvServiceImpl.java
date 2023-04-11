package com.ktdsuniversity.admin.mv.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.admin.common.api.exceptions.ApiArgsException;
import com.ktdsuniversity.admin.common.api.exceptions.ApiException;
import com.ktdsuniversity.admin.mv.dao.MvDAO;
import com.ktdsuniversity.admin.mv.vo.MvVO;
import com.ktdsuniversity.admin.mvgnr.dao.MvGnrDAO;
import com.ktdsuniversity.admin.mvgnr.vo.MvGnrVO;
import com.ktdsuniversity.admin.prdcprtcptnppl.dao.PrdcPrtcptnPplDAO;
import com.ktdsuniversity.admin.prdcprtcptnppl.vo.PrdcPrtcptnPplVO;

@Service
public class MvServiceImpl implements MvService {

	@Value("${upload.mv.poster.path:/naver-movie-admin/files/mv/poster}")
	private String pstrPath;
	
	@Autowired
	private MvDAO mvDAO;

	@Autowired
	private PrdcPrtcptnPplDAO prdcPrtcptnPplDAO;
	
	@Autowired
	private MvGnrDAO mvGnrDAO;
	
	@Override
	public boolean createNewMv(MvVO mvVO, MultipartFile uploadFile) {
		
		if (mvVO.getMvTtl() == null || mvVO.getMvTtl().length() == 0) {
			throw new  ApiArgsException("404", "영화제목을 입력하세요.");
		}
		
		List<MvGnrVO> gnrList = mvVO.getGnrList();
		if (gnrList == null || gnrList.isEmpty()) {
			throw new ApiArgsException("404", "장르를 선택세요.");
		}
		
		List<PrdcPrtcptnPplVO> pplList = mvVO.getPplList();
		if (pplList == null || pplList.isEmpty()) {
			throw new ApiArgsException("404", "영화참여인을 선택하세요.");
		}
		
		if (uploadFile != null && !uploadFile.isEmpty()) {
			// 파일저장
			// 포스터를 저장할 폴더 있는지 체크
			File dir = new File(pstrPath);
			if (!dir.exists()) {
				// 없다면 폴더 생성
				dir.mkdirs();
			}
			// 난수 이름의 파일을 임시 생성
			String uuidFIleName = UUID.randomUUID().toString();
			File pstrFile = new File(dir, uuidFIleName);
			
			// 임시 생성한 파일에 업로드 파일 이동
			try {
				uploadFile.transferTo(pstrFile);
				
			} catch (IllegalStateException | IOException e) {
				throw new ApiException("500", "포스터 업로드를 실패했습니다.");
			}
			mvVO.setPstr(uuidFIleName);
		}
		
		int mvCreateCount = mvDAO.createNewMv(mvVO);
		
		if (mvCreateCount > 0) {
			// 장르 등록 - 반복
			for (MvGnrVO gnr: gnrList) {
				gnr.setMvId(mvVO.getMvId());
				gnr.setCrtr(mvVO.getCrtr());
				gnr.setMdfyr(mvVO.getMdfyr());
				mvGnrDAO.createNewMvGnr(gnr);
			}
			
			// 영화참여인 등록 - 반복
			for (PrdcPrtcptnPplVO ppl: pplList) {
				ppl.setMvId(mvVO.getMvId());
				ppl.setCrtr(mvVO.getCrtr());
				ppl.setMdfyr(mvVO.getMdfyr());
				prdcPrtcptnPplDAO.createNewPrdcPrtcptnPpl(ppl);
			}
		}
		
		return mvCreateCount > 0;
	}

	@Override
	public boolean updateOneMv(MvVO mvVO, MultipartFile uploadFile) {
		
		if (mvVO.getMvTtl() == null || mvVO.getMvTtl().length() == 0) {
			throw new  ApiArgsException("404", "영화제목을 입력하세요.");
		}
		
		List<MvGnrVO> gnrList = mvVO.getGnrList();
		if (gnrList == null || gnrList.isEmpty()) {
			throw new ApiArgsException("404", "장르를 선택세요.");
		}
		
		List<PrdcPrtcptnPplVO> pplList = mvVO.getPplList();
		if (pplList == null || pplList.isEmpty()) {
			throw new ApiArgsException("404", "영화참여인을 선택하세요.");
		}
		
		if (uploadFile != null && !uploadFile.isEmpty()) {
			// 파일저장
			// 포스터를 저장할 폴더 있는지 체크
			File dir = new File(pstrPath);
			if (!dir.exists()) {
				// 없다면 폴더 생성
				dir.mkdirs();
			}
			// 난수 이름의 파일을 임시 생성
			String uuidFIleName = UUID.randomUUID().toString();
			File pstrFile = new File(dir, uuidFIleName);
			
			// 임시 생성한 파일에 업로드 파일 이동
			try {
				uploadFile.transferTo(pstrFile);
				
			} catch (IllegalStateException | IOException e) {
				throw new ApiException("500", "포스터 업로드를 실패했습니다.");
			}
			mvVO.setPstr(uuidFIleName);
		}
		
		int mvUpdateCount = mvDAO.updateOneMv(mvVO);
		
		if (mvUpdateCount > 0) {
			// 장르 등록 - 반복
			for (MvGnrVO gnr: gnrList) {
				
				if (gnr.getAdded() != null && gnr.getAdded().equals("true")) {
					gnr.setMvId(mvVO.getMvId());
					gnr.setCrtr(mvVO.getCrtr());
					gnr.setMdfyr(mvVO.getMdfyr());
					mvGnrDAO.createNewMvGnr(gnr);
				} else if (gnr.getDeleted() != null && gnr.getDeleted().length() > 0) {
					gnr.setMvId(mvVO.getMvId());
					gnr.setMdfyr(mvVO.getMdfyr());
					gnr.setGnrId(Integer.parseInt(gnr.getDeleted()));
					mvGnrDAO.deleteOneMvGnrIdAndMvId(gnr);
				}
			}
			
			// 영화참여인 등록 - 반복
			for (PrdcPrtcptnPplVO ppl: pplList) {
				
				if (ppl.getAdded() != null && ppl.getAdded().equals("true")) {
					ppl.setMvId(mvVO.getMvId());
					ppl.setCrtr(mvVO.getCrtr());
					ppl.setMdfyr(mvVO.getMdfyr());
					prdcPrtcptnPplDAO.createNewPrdcPrtcptnPpl(ppl);
				} else if (ppl.getModified() != null && ppl.getModified().length() > 0) {
					ppl.setMdfyr(mvVO.getMdfyr());
					ppl.setPrdcPrtcptnId(ppl.getModified());
					prdcPrtcptnPplDAO.updateOnePrdcPrtcptnPpl(ppl);
				} else if (ppl.getDeleted() != null && ppl.getDeleted().length() > 0) {
					prdcPrtcptnPplDAO.deleteOnePrdcPrtcptnPplByPrdcPrtcptnPplId(ppl.getDeleted());
				}
					
			}
		}
		
		return mvUpdateCount > 0;
	}

	@Override
	public boolean deleteOneMvByMvId(String mvId) {
		return false;
	}

	@Override
	public boolean deleteMvIdList(List<String> mvIdList) {
		return false;
	}

	@Override
	public MvVO readOneMvByMvId(String mvId) {
		return mvDAO.readOneMvByMvId(mvId);
	}

	@Override
	public List<MvVO> readAllMv(MvVO mvVO) {
		return null;
	}

	
	
}
