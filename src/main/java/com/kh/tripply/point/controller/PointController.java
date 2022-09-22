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
	
	@RequestMapping(value="/point/chargeView.kh",method=RequestMethod.GET)
	public ModelAndView pointChargeView(ModelAndView mv) {
	
		mv.setViewName("/point/pointCharge");
		return mv;
	}

}
