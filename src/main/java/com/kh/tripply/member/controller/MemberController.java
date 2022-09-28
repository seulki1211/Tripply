package com.kh.tripply.member.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	
	// 회원가입 기능
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
				mv.addObject("msg", "로그인에 실패하였습니다.");
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
			mv.addObject("msg", "로그아웃에 실패하였습니다.");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 회원상세정보 기능
	@RequestMapping(value="/member/myPage.kh", method=RequestMethod.GET)
	public ModelAndView showMyPage(HttpServletRequest request
			, ModelAndView mv) {
		try {
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("loginUser");
			String memberId = member.getMemberId();
			Member mOne = mService.printMemberId(memberId);
			String mAddr = mOne.getMemberAddr();
			String [] addrInfos = mAddr.split(",");
			mv.addObject("member", mOne).addObject("addrInfos", addrInfos);
			mv.setViewName("member/myPage");
		} catch (Exception e) {
//			mv.addObject("msg", e.getMessage()).setViewName("common/errorPage");
			
		}
		return mv;
	}
	
	// 회원정보수정 기능
	@RequestMapping(value="/member/modify.kh", method=RequestMethod.POST)
	public ModelAndView memberModify(
			ModelAndView mv
			, @ModelAttribute Member member
			, @RequestParam("post") String post
			, @RequestParam("address1") String address1
			, @RequestParam("address2") String address2
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request) {
		try {
			member.setMemberAddr(post + "," + address1 + "," + address2);
//			int result = mService.modifyMember(member);
			if(!uploadFile.equals("")) {
				String memberFilename = uploadFile.getOriginalFilename();
				// 수정, 1. 대체(replace) / 2. 삭제 후 저장
				// 파일삭제
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savedPath = root + "\\buploadFiles";
				// 파일 다시 저장
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String memberFileRename = sdf.format(new Date(System.currentTimeMillis()))
						+ "." + memberFilename.substring(memberFilename.lastIndexOf(".")+1);
				File file = new File(savedPath);
				if(!file.exists()) {
					file.mkdir();
				}
				String memberFilePath = savedPath + "\\" + memberFileRename;
				uploadFile.transferTo(new File(savedPath+"\\"+memberFileRename));
				member.setMemberFilename(memberFilename);
				member.setMemberFileRename(memberFileRename);
				member.setMemberFilePath(memberFilePath);
			}
			int result = mService.modifyMember(member);
			if(result > 0) {
				mv.setViewName("redirect:/home.kh");
			}else {
				mv.addObject("msg", "정보수정에 실패하였습니다.");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 회원탈퇴 기능
	@RequestMapping(value="/member/remove.kh", method=RequestMethod.GET)
	public ModelAndView removeMember(HttpSession session
			, ModelAndView mv) {
		try {
			Member member = (Member)session.getAttribute("loginUser");
			String memberId = member.getMemberId();
			int result = mService.removeMember(memberId);
			mv.setViewName("redirect:/member/logout.kh");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 회원ID찾기
	@RequestMapping(value="member/findIdView.kh", method=RequestMethod.GET)
	public String findMemberIdView() {
		return "/member/findId";
	}
	
	// 회원ID찾기 기능
	@RequestMapping(value="member/findId", method=RequestMethod.GET)
	public ModelAndView findMemberId(HttpServletRequest request
			, ModelAndView mv
			, @ModelAttribute Member member) {
		String memberEmail =  member.getMemberEmail();
		Member mOne = mService.findMemberId(memberEmail);
		return mv;
	}
	
	// 작성글 불러오기
	@RequestMapping(value="member/myWrite.kh", method=RequestMethod.GET)
	public ModelAndView lookMyWrite(HttpServletRequest request
			, ModelAndView mv) {
		return mv;
	}
}
