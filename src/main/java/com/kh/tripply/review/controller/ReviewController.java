package com.kh.tripply.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.review.common.Paging;
import com.kh.tripply.review.domain.Review;
import com.kh.tripply.review.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	ReviewService rService;
	
	/**
	 * 후기게시판 목록 페이지 이동
	 * @param mv,
	 * @return mv
	 */
	@RequestMapping(value="/review/list.kh", method=RequestMethod.GET)
	public ModelAndView reviewListView(ModelAndView mv,
			@RequestParam(value="currentPage", required=false)Integer page) {
		
//		int totalCount=rService.getTotalCount();

//		int limit = 9;
//		int startPage = 1;
//		int endPage = (int)((double)totalCount/limit+0.9);
//		int naviSize = 5;
//		int startNavi = ((currentPage-1)/naviSize)*naviSize+1;
//		int endNavi = startNavi+naviSize-1;
//		if(endNavi>endPage) {
//			endNavi=endPage;
//		}
//		if(currentPage<1) {
//			currentPage = 1;
//		}
//		if(currentPage>endPage) {
//			currentPage = endPage;
//		}
//		int offset = (currentPage-1)*limit;
		
		System.out.println(page);
		int currentPage = (page!=null)? page : 1;
		System.out.println(currentPage);
//		게시물의 총 개수, 현재페이지, 페이지 당 게시물 개수, 페이징네비 사이즈
		Paging paging = new Paging(rService.getTotalCount(), currentPage, 9, 5);
		try {
			List<Review> rList = rService.printAllReview(paging);
			if(!rList.isEmpty()) {
				System.out.println(paging.toString());
				mv.addObject("rList",rList).
				addObject("paging",paging).
				setViewName("review/reviewList");
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
