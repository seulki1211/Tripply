package com.kh.tripply.member.service;

import com.kh.tripply.member.domain.Member;

public interface MemberService {
	// loginMember 		로그인
	public Member loginMember(Member member);
	// registerMember 	회원가입
	public int registerMember(Member member);
	// modifyMember		회원정보수정
	public int modifyMember(Member member);
	// removeMember		회원탈퇴
	public int removeMember(String memberId);
}
