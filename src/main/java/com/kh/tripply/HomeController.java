package com.kh.tripply;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.banner.domain.Banner;
import com.kh.tripply.banner.service.BannerService;
import com.kh.tripply.main.domain.Main;
import com.kh.tripply.main.service.MainService;
import com.kh.tripply.notice.domain.Notice;
import com.kh.tripply.notice.service.NoticeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	private BannerService bService;
	@Autowired
	private NoticeService nService;
	@Autowired
	private MainService mainService;
	
	@RequestMapping(value = "/home.kh", method = RequestMethod.GET)
	public ModelAndView home(Locale locale
			, ModelAndView mv) {
		
		// 실재하는 배너만 출력
		ArrayList<Banner> bList = new ArrayList<Banner>();
		for(int i = 1; i < 6; i++) {
			Banner oneBanner = bService.printOneBanenr(i);
			if(!oneBanner.toString().contains("null")) {
				bList.add(oneBanner);
			}
		}
		mv.addObject("bList", bList);
		mv.setViewName("home");
		
		// 공지사항 출력
		try {
			List<Notice> nList = nService.printChoosedNotice();
			mv.addObject("nList", nList);
			mv.setViewName("home");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		
		// 최근글 출력 (mbk)
		List<Main> mainList = mainService.printAllRecent();
		if(!mainList.isEmpty()) {
			mv.addObject("mainList",mainList).
			setViewName("home");
		}else {
			mv.addObject("mainList",null).
			setViewName("home");
		}
		
		
		
		return mv;
	}
	

//공지사항 서비스 상세 뷰
	@RequestMapping(value = "/home/notice/detail.kh", method = RequestMethod.GET)
	public ModelAndView noticeDetailService(ModelAndView mv,
			@RequestParam("noticeNo") int noticeNo) {

		try {
			Notice notice = nService.printOneNotice(noticeNo);
			mv.addObject("notice", notice);
			mv.setViewName("noticeDetailService");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;

	}
}