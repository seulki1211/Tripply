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
import com.kh.tripply.party.domain.PartyReply;
import com.kh.tripply.plan.domain.Plan;
import com.kh.tripply.plan.domain.PlanList;
import com.kh.tripply.plan.domain.Planner;
import com.kh.tripply.plan.domain.PlannerReply;
import com.kh.tripply.plan.service.PlanService;

@Controller
public class PlannerController {
	@Autowired
	private PlanService pService;
	
	/**
	 * 상세페이지
	 * 
	 * @param mv
	 * @param boardNo
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/planner/planerDetail.kh", method=RequestMethod.GET)
	public ModelAndView showdd(
			ModelAndView mv
			,@RequestParam("boardNo")Integer boardNo
			,@RequestParam("page")Integer page
			,HttpSession session
			) {
		try {
			List<PlannerReply>rList=pService.printReply(boardNo);
			Planner planner = pService.printInfo(boardNo);
			List<Plan>planList = pService.printAllPlan(boardNo);
			session.setAttribute("boardNo", planner.getBoardNo());
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
		
			//System.out.println(planList);
			
			mv.addObject("rList",rList);
			mv.addObject("dayList",dayList);
			mv.addObject("page",page);
			mv.addObject("planList",planList);
			mv.addObject("planner",planner);
			mv.setViewName("planner/plannerDetail");
		}
		catch (Exception e) {
			mv.addObject("msg",e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
		
	}
	
	/**
	 *  여행 제목,일정 등록
	 * 
	 * @param planner
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/plan/regist.kh", method=RequestMethod.POST)
	public ModelAndView addPlanner(
			@ModelAttribute Planner planner
			,ModelAndView mv
			,HttpSession session) {
		
		 Member member = (Member)session.getAttribute("loginUser");
		 planner.setPlanWriter(member.getMemberId());
		 
		int result=pService.registPlanner(planner);
		int boardNo=planner.getBoardNo();
		mv.addObject("boardNo",boardNo);
		mv.setViewName("redirect:/plan/addplanView.kh"); 
		
		return mv;
		
	}
	
	/**
	 * 플래너 리스트 보여지기
	 * 
	 * @param mv
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/plan/plan.kh", method=RequestMethod.GET)
	public ModelAndView planerListShow(
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
		
		List<Planner>pList = pService.printAllPlanner(currentPage,boardLimit);
		if(!pList.isEmpty()) {
			mv.addObject("page",page);
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
		
		
		
	/**
		 * 리스트 검색
		 * 
		 * @param mv
		 * @param searchCondition
		 * @param searchValue
		 * @param page
		 * @return
		 */
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
			
			try{
			maxPage = (int)((double)totalCount/boardLimit +0.9);
			startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
			endNavi = startNavi+naviLimit -1;//for문 돌리면 중간값 나옴
			if(maxPage < endNavi) {
				endNavi=maxPage;
			}
			
				List<Planner> pList = pService.printAllValue(
						searchCondition,searchValue,currentPage, boardLimit);

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
				
			}catch (Exception e) {
				mv.addObject("msg",e.toString());
				mv.setViewName("common/errorPage");
			}
			return mv;
		
		}


	
	/**
	 *plan등록하는 화면, 지도
	 *보드넘버,여행 제목,일정 데이터 넘겨줘서 보여주기
	 * 
	 * @param mv
	 * @param boardNo
	 * @return
	 */
	@RequestMapping(value="plan/addplanView.kh", method=RequestMethod.GET)
	public ModelAndView showAddPlan(
			ModelAndView mv	
			,@RequestParam("boardNo")Integer boardNo
			
			) {
		Planner planner = pService.printInfo(boardNo);
		 
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
	
	/**
	 * 지도 장소,x,y좌표 등록
	 * @param mv
	 * @param l
	 * @return
	 */
	@RequestMapping(value="/plan/registplan.kh",method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView registPlan(
			ModelAndView mv
			, @ModelAttribute(value="PlanList") PlanList l) {
		int result = pService.registPlanner(l);
		
		System.out.println(l);
		mv.setViewName("redirect:/plan/plan.kh");
		return mv;
		
	}
	
	/**
	 * 플래너 삭제
	 * 
	 * @param mv
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/plan/remove.kh",method= RequestMethod.GET)
	public ModelAndView deletePlanner(
			ModelAndView mv
			,@RequestParam("page")Integer page
			,HttpSession session
			) {
		int boardNo = (int)session.getAttribute("boardNo");
		int result = pService.deletePlanner(boardNo);
		int result2 = pService.deletePlan(boardNo);
		
		mv.setViewName("redirect:/plan/plan.kh?page="+page);
		return mv;
		
		
	}
	
	/**
	 * 닫기 누르면 info사라짐
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/plan/infoRremove.kh",method= RequestMethod.GET)
	public ModelAndView deleteInfo(
			ModelAndView mv
			,HttpSession session
			) {
		int boardNo = (int)session.getAttribute("boardNo");
		int result = pService.deletePlanner(boardNo);
		
		mv.setViewName("redirect:/plan/plan.kh");
		return mv;
		
		
	}
	
	/**
	 * 댓글 등록
	 * @param mv
	 * @param PlannerReply
	 * @param boardNo
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/plan/addReply.kh", method = RequestMethod.POST)
	public ModelAndView addReply(
			ModelAndView mv
			,@ModelAttribute PlannerReply PlannerReply
			,@RequestParam("boardNo")Integer boardNo
			,@RequestParam("page")Integer page
			,HttpSession session) {
		Member member = (Member)session.getAttribute("loginUser");
		PlannerReply.setpReplyWriter(member.getMemberId());
		PlannerReply.setBoardNo(boardNo);
		
		int result = pService.addReply(PlannerReply);
		mv.setViewName("redirect:/planner/planerDetail.kh?boardNo="+boardNo+"&page="+page);
		return mv;
		
	}
	
	@RequestMapping(value="/plan/removeReply.kh",method=RequestMethod.GET)
	public ModelAndView removeReply(
			ModelAndView mv
			,@RequestParam("replyNo")Integer replyNo
			,@RequestParam("boardNo")Integer boardNo 
			,@RequestParam("page")Integer page) {
		try {
			int result= pService.removeReply(replyNo);
	
		
		mv.setViewName("redirect:/planner/planerDetail.kh?boardNo="+boardNo+"&page="+page);
		}catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
		
		
	}
	
	// 댓글 수정 
		@RequestMapping(value = "/plan/modifyReply.kh", method = RequestMethod.POST)
		public ModelAndView modifyPartyReply(ModelAndView mv
				, @ModelAttribute PlannerReply plannerReply
				, @RequestParam("page") Integer page
				
				, HttpSession session) {
			
			try {
				System.out.println(plannerReply.toString());
				int result = pService.modifyReply(plannerReply);
				mv.setViewName("redirect:/planner/planerDetail.kh?boardNo="+plannerReply.getBoardNo()+"&page="+page);
				
			} catch (Exception e) {
				mv.addObject("msg", e.getMessage());
				mv.setViewName("common/errorPage");
			}
			return mv;
			
		}
		
		@RequestMapping(value = "/plan/mobify.kh", method = RequestMethod.GET)
		public ModelAndView modifyPlanner(
				ModelAndView mv
				,@RequestParam("boardNo")Integer boardNo
				,@RequestParam("page")Integer page
				) {
			System.out.println(boardNo);
			System.out.println(page);
			//List<Plan>planList=pService.printAllPlan(plan.getBoardNo());
			Planner planner=pService.printInfo(boardNo);
			List<Plan>planList = pService.printAllPlan(boardNo);
			
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
			
			mv.addObject("planList",planList);
			mv.addObject("dayList",dayList);
			mv.addObject("planner",planner);
			mv.setViewName("planner/plannerModifyForm");
			
			return mv;
		}
		
		
		
	

	

}
