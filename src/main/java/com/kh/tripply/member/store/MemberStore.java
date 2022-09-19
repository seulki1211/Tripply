package com.kh.tripply.member.store;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.member.domain.Member;

public interface MemberStore {
	// selectLoginMember
	public Member selectLoginMember(SqlSession session, Member member);
	// insertMember
	public int insertMember(SqlSession session, Member member);
	// updateMember
	public int updatemember(SqlSession session, Member member);
	// deleteMember
	public int deleteMember(SqlSession session, String memberId);
}
