package com.ktdsuniversity.admin.mvppl.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ktdsuniversity.admin.common.util.DownloadUtil;
import com.ktdsuniversity.admin.mvppl.service.MvPplService;
import com.ktdsuniversity.admin.mvppl.vo.MvPplVO;

@Controller
public class MvPplController {
	
	private static final Logger log = LoggerFactory.getLogger(MvPplController.class); 
	
	@Autowired
	private MvPplService mvPplService;
	
	@Value("${upload.profile.path:/naver-movie-admin/files/profiles}")
	private String profilePath;
	
	
	@GetMapping("/mvppl/list")
	public String viewMvPplListPage(MvPplVO mvPplVO, Model model) {
		List<MvPplVO> mvPplList = mvPplService.readAllMvPpl(mvPplVO);
		model.addAttribute("mvPplList", mvPplList);
		model.addAttribute("mvPplVO", mvPplVO);
		
		return "mvppl/list";
	}
	
	@GetMapping("/mvppl/prfl/{filename}")
	public void downloadPrflPctr(@PathVariable String filename,
								HttpServletRequest request,
								HttpServletResponse response){
		log.debug("다운로드 프로필사진의 파일이름 : " + filename);
		File imageFile = new File(profilePath, filename);
		if (!imageFile.exists() || !imageFile.isFile()) {
			log.debug("파일이 경로에 없어서 기본값으로 호출한다");
			filename = "base_profile.jpg";
		}
		DownloadUtil dnUtil = new DownloadUtil(response, request, profilePath + "/" + filename);
		dnUtil.download(filename);
	}
	
	
	
}
