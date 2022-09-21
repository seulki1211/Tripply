package com.kh.tripply.banner.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.banner.domain.Banner;
import com.kh.tripply.banner.service.BannerService;
import com.kh.tripply.notice.domain.Notice;

@Controller
public class BannerController {

	@Autowired
	private BannerService bService;
	
	// 배너 리스트 및 등록 뷰
	@RequestMapping(value="admin/banner/list.kh", method=RequestMethod.GET)
	public ModelAndView showBannerWriteAndList(ModelAndView mv) {
		
		List<Banner> bList = bService.printAllBanner();
		if(!bList.isEmpty()) {
			mv.addObject("bList", bList);
			mv.setViewName("/admin/banner/bannerRegisterAndList");
		}else {
			mv.addObject("msg", "실패");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	// 배너 등록 수정중
	@RequestMapping(value="admin/banner/register.kh", method=RequestMethod.POST)
	public ModelAndView registerBanner(ModelAndView mv
									,@ModelAttribute Banner banner
									,@RequestParam("bannerNo") int bannerNo
									,@RequestParam(value = "uploadFile", required = false) 
													MultipartFile uploadFile
									, HttpServletRequest request) { // resources 경로 가져오려고
		
		
		int chkBanner  = bService.chkBanner(bannerNo);
		
		if(chkBanner > 0) { // 업데이트
				try {
				
				String bannerFileName = uploadFile.getOriginalFilename();
				
				if(!bannerFileName.equals("")) {
					String root = request.getSession().getServletContext().getRealPath("resources");
					String savePath = root + "\\buploadFiles"; // 저장경로 지정
					File file = new File(savePath);
					
					// 파일 이름이 같다고 안들어가는 경우 방지 --> 파일명을 날짜+시간으로 바꿈
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String bannerFileRename = sdf.format(new Date(System.currentTimeMillis()))+"."
							+bannerFileName.substring(bannerFileName.lastIndexOf(".")+1);// .다음부터 끝까지 잘라서 반환
					if(!file.exists()) {
						file.mkdir(); // 경로 폴더가 없으면 폴더 생성
					}
					uploadFile.transferTo(new File(savePath + "\\" + bannerFileRename));//파일을 buploadFile경로에 저장하는 메소드
					
					banner.setBannerFileName(bannerFileName);
					banner.setBannerFileRename(bannerFileRename);
					
					String boardFilepath = savePath + "\\" +bannerFileRename;// 절대경로
					
					banner.setBannerFilePath(boardFilepath);
					
				}
				
				int result = bService.renewBanner(banner);
				mv.setViewName("redirect:/admin/banner/list.kh");
				
			}catch (Exception e) {
				e.printStackTrace();
				mv.addObject("msg", e.getMessage());
				mv.setViewName("common/errorPage");		
			}
			return mv;
		
		}else{
			// 인서트
			try {
				
				String bannerFileName = uploadFile.getOriginalFilename();
				
				if(!bannerFileName.equals("")) {
					String root = request.getSession().getServletContext().getRealPath("resources");
					String savePath = root + "\\buploadFiles"; // 저장경로 지정
					File file = new File(savePath);
					
					// 파일 이름이 같다고 안들어가는 경우 방지 --> 파일명을 날짜+시간으로 바꿈
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String bannerFileRename = sdf.format(new Date(System.currentTimeMillis()))+"."
							+bannerFileName.substring(bannerFileName.lastIndexOf(".")+1);// .다음부터 끝까지 잘라서 반환
					if(!file.exists()) {
						file.mkdir(); // 경로 폴더가 없으면 폴더 생성
					}
					uploadFile.transferTo(new File(savePath + "\\" + bannerFileRename));//파일을 buploadFile경로에 저장하는 메소드
					
					banner.setBannerFileName(bannerFileName);
					banner.setBannerFileRename(bannerFileRename);
					
					String boardFilepath = savePath + "\\" +bannerFileRename;// 절대경로
					
					banner.setBannerFilePath(boardFilepath);
					
					banner.setBannerNo(bannerNo);
					banner.setBannerWriter("관리자");
					
					
				}
				
				int result = bService.registerBanner(banner);
				mv.setViewName("redirect:/admin/banner/list.kh");
				
			}catch (Exception e) {
				e.printStackTrace();
				mv.addObject("msg", e.getMessage());
				mv.setViewName("common/errorPage");		
			}
			return mv;
		}
		
		
	}
	


}

