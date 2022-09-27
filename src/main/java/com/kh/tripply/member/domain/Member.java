package com.kh.tripply.member.domain;

import java.sql.Date;

public class Member {
	private String memberId;		// 아이디
	private String memberPwd;		// 비밀번호
	private String memberName;		// 이름
	private String memberNickname;	// 닉네임
	private String memberEmail;		// 이메일
	private String memberBirth;		// 생년월일
	private String memberGender;	// 성별
	private String memberPhone;		// 전화번호
	private String memberAddr;		// 주소
	private Date enrollDate;		// 회원가입일
	private Date updateDate;		// 정보수정일
	private String mStatus;			// 탈퇴여부
	private int pointBalance;		// 포인트
	private String memberFilename;	// 프로필 파일이름
	private String memberFileRename;// 파일변경이름
	private String memberFilePath;	// 파일경로
	
	// 기본생성자
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 회원가입용 생성자
	public Member(String memberId, String memberPwd, String memberName, String memberNickname, String memberEmail,
			String memberBirth, String memberGender, String memberPhone, String memberAddr) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberNickname = memberNickname;
		this.memberEmail = memberEmail;
		this.memberBirth = memberBirth;
		this.memberGender = memberGender;
		this.memberPhone = memberPhone;
		this.memberAddr = memberAddr;
	}
	
	

	public Member(String memberId, String memberPwd, String memberName, String memberNickname, String memberEmail,
			String memberBirth, String memberGender, String memberPhone, String memberAddr, Date enrollDate,
			Date updateDate, String mStatus, int pointBalance, String memberFilename, String memberFileRename,
			String memberFilePath) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberNickname = memberNickname;
		this.memberEmail = memberEmail;
		this.memberBirth = memberBirth;
		this.memberGender = memberGender;
		this.memberPhone = memberPhone;
		this.memberAddr = memberAddr;
		this.enrollDate = enrollDate;
		this.updateDate = updateDate;
		this.mStatus = mStatus;
		this.pointBalance = pointBalance;
		this.memberFilename = memberFilename;
		this.memberFileRename = memberFileRename;
		this.memberFilePath = memberFilePath;
	}

	// getter / setter
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberAddr() {
		return memberAddr;
	}

	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getmStatus() {
		return mStatus;
	}

	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}

	public int getPointBalance() {
		return pointBalance;
	}

	public void setPointBalance(int pointBalance) {
		this.pointBalance = pointBalance;
	}

	public String getMemberFilename() {
		return memberFilename;
	}

	public void setMemberFilename(String memberFilename) {
		this.memberFilename = memberFilename;
	}

	public String getMemberFileRename() {
		return memberFileRename;
	}

	public void setMemberFileRename(String memberFileRename) {
		this.memberFileRename = memberFileRename;
	}

	public String getMemberFilePath() {
		return memberFilePath;
	}

	public void setMemberFilePath(String memberFilePath) {
		this.memberFilePath = memberFilePath;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName
				+ ", memberNickname=" + memberNickname + ", memberEmail=" + memberEmail + ", memberBirth=" + memberBirth
				+ ", memberGender=" + memberGender + ", memberPhone=" + memberPhone + ", memberAddr=" + memberAddr
				+ ", enrollDate=" + enrollDate + ", updateDate=" + updateDate + ", mStatus=" + mStatus
				+ ", pointBalance=" + pointBalance + ", memberFilename=" + memberFilename + ", memberFileRename="
				+ memberFileRename + ", memberFilePath=" + memberFilePath + "]";
	}
	
}
