package com.kh.tripply.member.service;

import com.kh.tripply.member.domain.Member;

public interface MemberService {
	// 로그인
	public Member loginMember(Member member);
	// 회원가입
	public int registerMember(Member member);
	// 회원정보수정
	public int modifyMember(Member member);
	// 회원탈퇴
	public int removeMember(String memberId);
	// 회원상세정보
	public Member printMemberId(String memberId);
}
