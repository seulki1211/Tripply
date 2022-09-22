package com.kh.tripply;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.banner.controller.BannerController;
import com.kh.tripply.banner.domain.Banner;
import com.kh.tripply.banner.service.BannerService;

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
	
	@RequestMapping(value = "/home.kh", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, ModelAndView mv) {
		
		List<Banner> bList = bService.printAllBanner();
		if (!bList.isEmpty()) {
			mv.addObject("bList", bList);
			mv.setViewName("home");
		} else if (bList.isEmpty()) {
			mv.setViewName("home");
		} else {
			mv.addObject("msg", "실패");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
		
	}
	
}
