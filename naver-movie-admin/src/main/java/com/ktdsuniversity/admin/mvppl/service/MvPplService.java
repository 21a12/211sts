package com.ktdsuniversity.admin.mvppl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

public interface MvPplService {
	
	public List<MvPplVO> readAllMvPpl(MvPplVO mvPplVO);
	public boolean createOneMvPpl(MvPplVO mvPplVO, MultipartFile uploadFile);
	public boolean updateOneMvPpl(MvPplVO mvPplVO, MultipartFile uploadFile);
	public boolean deleteOneMyPpl(String mvPplId);
	public boolean deleteSelectAll(List<String> mvPplIdList);

}
