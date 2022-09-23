package com.kh.tripply.point.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.point.service.PointService;

@Controller
public class PointController {
	@Autowired
	PointService pService;
	
	/**
	 * 포인트 충전 페이지 이동
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/point/chargeView.kh",method=RequestMethod.GET)
	public ModelAndView pointChargeView(ModelAndView mv) {
	
		mv.setViewName("/point/pointCharge");
		return mv;
	}
	
	/**
	 * 포인트 내역 페이지 이동
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/point/historyView.kh",method=RequestMethod.GET)
	public ModelAndView pointHistoryView(ModelAndView mv) {
		
		mv.setViewName("/point/pointHistory");
		return mv;
	}

	
	/**
	 * 포인트 전송 페이지 이동
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/point/send.kh",method=RequestMethod.GET)
	public ModelAndView poinSendView(ModelAndView mv) {
		
		mv.setViewName("/point/pointSend1");
		return mv;
	}
}
