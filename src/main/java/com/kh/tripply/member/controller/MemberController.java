package com.kh.tripply.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value="member/register.kh", method=RequestMethod.POST)
	public String memberJoin(
			@RequestParam("memberId") String memberId
			, @RequestParam("memberPwd") String memberPwd
			, @RequestParam("memberName") String memberName
			, @RequestParam("memberNickname") String memberNickname
			, @RequestParam("memberEmail") String memberEmail
			, @RequestParam("memberBirth") String memberBirth
			, @RequestParam("memberGender") String memberGender
			, @RequestParam("memberPhone") String memberPhone
			, @RequestParam("post") String post
			, @RequestParam("address1") String address1
			, @RequestParam("address2") String address2) {
		Member member = new Member(memberId, memberPwd, memberName, memberNickname, memberEmail, memberBirth, memberGender, memberPhone
				, post + "," + address1 + "," +address2);
		int result = mService.registerMember(member);
		if(result > 0) {
			return "redirect:/home.kh";
		}else {
			return "";
		}
	}
	
}
