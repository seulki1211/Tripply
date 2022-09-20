package com.kh.tripply.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.tripply.member.domain.Member;
import com.kh.tripply.member.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService mService;

	@RequestMapping(value="member/joinView.kh", method=RequestMethod.GET)
	public String memberJoinView() {
		
		return "member/join";
	}
	
//	@RequestMapping(value="member/register.kh", method=RequestMethod.POST)
//	public String memberJoin(
//			@RequestParam("memberId") String memberId
//			, @RequestParam("memberPwd") String memberPwd
//			, @RequestParam("memberName") String memberName
//			, @RequestParam("memberNickname") String memberNickname
//			, @RequestParam("memberEmail") String memberEmail
//			, @RequestParam("memberBirth") String memberBirth
//			, @RequestParam("memberGender") String memberGender
//			, @RequestParam("memberPhone") String memberPhone
//			, @RequestParam("post") String post
//			, @RequestParam("address1") String address1
//			, @RequestParam("address2") String address2) {
//		Member member = new Member(memberId, memberPwd, memberName, memberNickname, memberEmail, memberBirth, memberGender, memberPhone
//				, post + "," + address1 + "," +address2);
//		int result = mService.registerMember(member);
//		if(result > 0) {
//			return "redirect:/home.kh";
//		}else {
//			return "";
//		}
//	}
	
	@RequestMapping(value="/member/register.kh", method=RequestMethod.POST)
	public ModelAndView memberJoin(
			@ModelAttribute Member member
			, @RequestParam("post") String post
			, @RequestParam("address1") String address1
			, @RequestParam("address2") String address2
			, ModelAndView mv) {
		try {
			member.setMemberAddr(post + "," + address1 + "," + address2);
			int result = mService.registerMember(member);
			if(result > 0) {
				mv.setViewName("redirect:/home.kh");
			} else {
				mv.addObject("msg", "회원가입에 실패하였습니다.");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "회원가입에 실패하였습니다.");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 로그인 기능
		@RequestMapping(value="/member/login.kh", method=RequestMethod.POST)
		public ModelAndView memberLogin(
				@ModelAttribute Member member
				, ModelAndView mv
				, HttpServletRequest request) {
			try {
				Member loginUser = mService.loginMember(member);
				if(loginUser != null) {
					HttpSession session = request.getSession();
					session.setAttribute("loginUser", loginUser);
					mv.setViewName("redirect:/home.kh");
				}else {
					mv.addObject("msg", "회원정보를 찾을 수 없습니다.");
					mv.setViewName("common/errorPage");
				}
			} catch (Exception e) {
				mv.addObject("msg", e.toString());
				mv.setViewName("common/errorPage");
			}
			return mv;
		}
		
	// 로그아웃 기능
		@RequestMapping(value="/member/logout.kh", method=RequestMethod.GET)
		public ModelAndView memberLogOut(
				ModelAndView mv
				, HttpServletRequest request) {
			HttpSession session = request.getSession();
			if(session != null) {
				session.invalidate();
				mv.setViewName("redirect:/home.kh");
			} else {
				mv.addObject("msg", "로그아웃 실패");
				mv.setViewName("common/errorPage");
			}
			return mv;
		}
}
