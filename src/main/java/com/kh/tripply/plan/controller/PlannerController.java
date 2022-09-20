package com.kh.tripply.plan.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.member.domain.Member;
import com.kh.tripply.plan.domain.Planner;
import com.kh.tripply.plan.service.PlanService;

@Controller
public class PlannerController {
	@Autowired
	private PlanService pService;
	@RequestMapping(value="/plan/map.kh", method=RequestMethod.GET)
	public String showMap() {
		
		return "map/map";
		
	}

	/*
	 * @RequestMapping(value="/plan/plan.kh", method=RequestMethod.GET) public
	 * String plannerShow( ) { return "planner/plannerList";
	 * 
	 * 
	 * }
	 */
	@RequestMapping(value="/plan/regist.kh", method=RequestMethod.POST)
	public ModelAndView addPlanner(
			@ModelAttribute Planner planner
			,ModelAndView mv
			,HttpSession session) {
		/*
		 * Member member = (Member)session.getAttribute("loginUser");
		 * planner.setMemberId(member.getMemberId());
		 */
		int result=pService.registPlanner(planner);
		int boardNo=planner.getBoardNo();
		mv.addObject("boardNo",boardNo);
		 mv.setViewName("redirect:/plan/addplanView.kh"); 
			/* mv.setViewName("redirect:/plan/plan.kh"); */
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/plan/plan.kh", method=RequestMethod.GET)
	public ModelAndView planShow(
			ModelAndView mv
			,@RequestParam(value="page", required=false) Integer page) {
		int currentPage=(page !=null) ? page : 1;
		int totalCount = pService.getTotalCount("","");//현재 페이지 값과 전체 게시물 갯수 가져옴
		int boardLimit=10;
		int naviLimit=5;
		int maxPage;
		int startNavi;
		int endNavi;
		
		maxPage = (int)((double)totalCount/boardLimit +0.9);
		startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
		endNavi = startNavi+naviLimit -1;//for문 돌리면 중간값 나옴
		if(maxPage < endNavi) {
			endNavi=maxPage;
		}
		
		List<Planner>pList = pService.printAllPlan(currentPage,boardLimit);
		if(!pList.isEmpty()) {
//			mv.addObject("listUrl","/plan/plan.kh?page=");
			mv.addObject("urlVal","plan");
			mv.addObject("maxPage",maxPage);
			mv.addObject("currentPage",currentPage);// boardlistview에 값을 보내줘야한다
			mv.addObject("startNavi",startNavi);
			mv.addObject("endNavi",endNavi);
			mv.addObject("pList",pList);
		}
		mv.setViewName("planner/plannerList");
		return mv;
	}
		
		
		
	/*@RequestMapping(value="/plan/regist.kh", method=RequestMethod.POST)
	public ModelAndView addPlanner(
			@ModelAttribute Planner planner
			,ModelAndView mv
			,HttpSession session) {
		Member member = (Member)session.getAttribute("loginUser");
		planner.setMemberId(member.getMemberId());
		int result=pService.registPlanner(planner);
		int boardNo=planner.getBoardNo();
		mv.addObject("boardNo",boardNo);
		mv.setViewName("redirect:/plan/addplanView.kh");
		
		return mv;
		
	}
	*/
	@RequestMapping(value="plan/addplanView.kh", method=RequestMethod.GET)
	public ModelAndView printPlanner(
			ModelAndView mv	
			,@RequestParam("boardNo")Integer boardNo
			
			) {
		Planner planner = pService.printInfo(boardNo);
//		List<Planner>pList= pService.printPlanner(boardNo);
		 
		List<String> dayList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date startDate = planner.getFirstDay();
		Date lastDate = planner.getLastDay();
		Calendar c1= Calendar.getInstance();
		Calendar c2= Calendar.getInstance();
		c1.setTime(startDate);
		c2.setTime(lastDate);
		
		while(c1.compareTo(c2) !=1) {
			dayList.add(sdf.format(c1.getTime()));
			c1.add(Calendar.DATE,1);
		}
//		 String[] dayList = new String[dList.size()];
//		 dList.toArray(dayList);
  
        ////////////////////////////////////////////일수 구하기
//        Date format1 = planner.getFirstDay();
//        Date format2 = planner.getLastDay();
//        long diffSec = (format2.getTime() - format1.getTime()) / 1000; //초 차이
//        long diffDays = diffSec / (24*60*60); //일자수 차이
//		mv.addObject("diffDays",diffDays);
		////////////////////////////////////////////////////////
		
		mv.addObject("dayList",dayList);
		mv.addObject("planner",planner);
		mv.addObject("boardNo",boardNo);
		mv.setViewName("map/map");
		return mv;
		
	}
	
	@RequestMapping(value="/plan/search.kh",method=RequestMethod.GET)
	public ModelAndView searchPlaner(
			ModelAndView mv
			, @RequestParam("searchCondition")String searchCondition
			, @RequestParam("searchValue")String searchValue
			,@RequestParam(value="page", required=false) Integer page) {
		int currentPage=(page !=null) ? page : 1;
		int totalCount = pService.getTotalCount(searchCondition,searchValue);//현재 페이지 값과 전체 게시물 갯수 가져옴
		int boardLimit=10;
		int naviLimit=5;
		int maxPage;
		int startNavi;
		int endNavi;
		
		maxPage = (int)((double)totalCount/boardLimit +0.9);
		startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
		endNavi = startNavi+naviLimit -1;//for문 돌리면 중간값 나옴
		if(maxPage < endNavi) {
			endNavi=maxPage;
		}
		
			List<Planner> pList = pService.printAllValue(
					searchCondition,searchValue,currentPage, boardLimit);
//		BOARD_TBL<-SELECT *FROM WHERE B_STATUE='Y AND BOARD_TITLE LIKE ''%'||#{searchValue||}>
//		BOARD_TBL<-SELECT *FROM WHERE B_STATUE='Y AND CONTENTS LIKE ''%'||#{searchValue||}>
//		BOARD_TBL<-SELECT *FROM WHERE B_STATUE='Y AND BOARD_WRIGHTER LIKE ''%'||#{searchValue||}>
			if(!pList.isEmpty()) {
				
				mv.addObject("pList",pList);				
			}else {
				mv.addObject("pList",null);
			}
			mv.addObject("urlVal","search");
			mv.addObject("searchCondition",searchCondition);
			mv.addObject("searchValue",searchValue);
			mv.addObject("maxPage",maxPage);
			mv.addObject("currentPage",currentPage);// boardlistview에 값을 보내줘야한다
			mv.addObject("startNavi",startNavi);
			mv.addObject("endNavi",endNavi);
			mv.setViewName("/planner/plannerList");
			/*}catch (Exception e) {
			mv.addObject("msg",e.toString());
			mv.setViewName("common/errorPage");
		}*/
		return mv;
		
		
	}
	

	

}
