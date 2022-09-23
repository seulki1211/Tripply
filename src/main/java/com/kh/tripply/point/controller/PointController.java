package com.kh.tripply.point.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.common.Paging;
import com.kh.tripply.member.domain.Member;
import com.kh.tripply.point.domain.Point;
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
	public ModelAndView pointHistoryView(ModelAndView mv,
			HttpSession session,
			Point point,  // select 시 필요한 값 전달용 객체 생성
			@RequestParam(value="currentPage",required=false) Integer page) {
		
		//1.currentPage를 널체크한다.
		int currentPage = (page != null)? page : 1;
		
		//2.paging처리를 위해 Paging 객체를 생성한다.
		Paging paging = new Paging(pService.getHistoryTotalCount(point), currentPage, 10, 5);
		
		//3.로그인한 유저의 아이디를 세션에서 가져온다.
		Member loginMember = (Member)session.getAttribute("loginUser");
		String loginUser = loginMember.getMemberId();
		
		//4.SELECT 시 필요한 값들을 POINT객체에 담는다.
		//로그인 유저아이디,
		point.setLoginUser(loginUser);
		
		//5.페이징과 포인트 객체를 매개값으로 SELECT한다.
		List<Point> pList = pService.printAllPointHistory(paging,point);
		if(!pList.isEmpty()) {
			
			//6.화면에 pList와 paging값을 전달한다.
			mv.addObject("pList",pList)
			.addObject("paging",paging)
			.setViewName("/point/pointHistory");
		}else {
			
		}
		return mv;
	}

	
	/**
	 * 포인트 전송 페이지 이동
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/point/send.kh",method=RequestMethod.GET)
	public ModelAndView pointSendView(ModelAndView mv) {
		
		mv.setViewName("/point/pointSend1");
		return mv;
	}
	
	/**
	 * 포인트 충전
	 * @param mv
	 * @param session
	 * @param point
	 * @return
	 */
	@RequestMapping(value="/point/charge.kh", method=RequestMethod.POST)
	public ModelAndView pointCharge(ModelAndView mv,
			HttpSession session,
			@ModelAttribute Point point) {
		
		//1.포인트 충전 창에서 가져온 값을 이용하여
		//멤버 테이블의 POINT_BALANCE를	UPDATE한다.
		//충전이므로 기존 값에 충전금액을 더해준다.
		try {
			int chargeResult = pService.modifyChargePoint(point);
			if(chargeResult>0) {
				
				//2. 1번 작업 완료 시 POINT_HISTORY_TBL에 INSERT한다.
				int registerHistoryResult = pService.registerPointHistory(point);
				if(registerHistoryResult>0) {
					mv.addObject("message","충전")
					.addObject("point",point)
					.setViewName("/point/pointWorkSuccess");
				}else {
					
				}
			}
		} catch (Exception e) {
		}
		
		
		return mv;
	}
	
	
}
