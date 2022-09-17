package com.kh.tripply.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.review.domain.Review;
import com.kh.tripply.review.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	ReviewService rService;
	
	/**
	 * 후기게시판 목록 페이지 이동
	 * @return /review/reviewList
	 */
	@RequestMapping(value="/review/list.kh", method=RequestMethod.GET)
	public ModelAndView reviewListView(ModelAndView mv) {
		
		try {
			List<Review> rList = rService.printAllReview();
			if(!rList.isEmpty()) {
				mv.addObject("rList",rList).setViewName("review/reviewList");
			}else {
				
			}
		} catch (Exception e) {
		}
		return mv;
	}
	
	/**
	 * 후기 게시물 작성 페이지 이동
	 * @return /review/reviewWrite
	 */
	@RequestMapping(value="/review/writeView.kh",method=RequestMethod.GET)
	public String reviewWriteView() {
		return "/review/reviewWrite";
	}
	
	/**
	 * 후기 게시물 작성 버튼 로직
	 * @param mv
	 * @return mv
	 */
	@RequestMapping(value="/review/write.kh",method=RequestMethod.POST)
	public ModelAndView reviewWrite(ModelAndView mv,
					@ModelAttribute Review review) {
		int result = rService.registerReview(review);
		
		try {
			if(result > 0) {
				mv.setViewName("/review/reviewList");
			}else {
				mv.addObject("msg","게시물 저장에 실패하였습니다.").setViewName("/common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg",e.getMessage()).setViewName("/common/errorPage");
		}
		return mv;
	}

}
