package com.kh.tripply.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.main.domain.Main;
import com.kh.tripply.main.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	MainService mainService;
	
	/**
	 * 최신 게시물 5개 출력
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/main/recent.kh",method=RequestMethod.GET)
	public ModelAndView mainRecent(ModelAndView mv) {
		
		List<Main> mainList = mainService.printAllRecent();
		if(!mainList.isEmpty()) {
			mv.addObject("mainList",mainList).
			setViewName("/main/recent");
		}else {
			mv.addObject("mainList",null).
			setViewName("/main/recent");
		}
		
		return mv;
	}

}
