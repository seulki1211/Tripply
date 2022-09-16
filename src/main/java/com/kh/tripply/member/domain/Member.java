package com.kh.tripply.member.domain;

import java.sql.Date;

public class Member {
	private String memberId;		// 아이디
	private String memberPwd;		// 비밀번호
	private String memberName;		// 이름
	private String memberNickname;	// 닉네임
	private String memberBirth;		// 생년월일
	private String memberEmail;		// 이메일
	private String memberGender;	// 성별
	private String memberPhone;		// 전화번호
	private String memberAddr;		// 주소
	private Date enrollDate;		// 회원가입일
	private Date updateDate;		// 정보수정일
	private String mStatus;			// 탈퇴여부
}
