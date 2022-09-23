package com.kh.tripply.member.service.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.member.domain.Member;
import com.kh.tripply.member.service.MemberService;
import com.kh.tripply.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private MemberStore mStore; 
	
	@Override
	public Member loginMember(Member member) {
		Member mOne = mStore.selectLoginMember(session, member);
		return mOne;
	}

	@Override
	public int registerMember(Member member) {
		int result = mStore.insertMember(session, member);
		return result;
	}

	@Override
	public int modifyMember(Member member) {
		int result = mStore.updatemember(session, member);
		return result;
	}

	@Override
	public int removeMember(String memberId) {
		int result = mStore.deleteMember(session, memberId);
		return result;
	}

	@Override
	public Member printMemberId(String memberId) {
		Member mOne = mStore.selectMemberId(session, memberId);
		return mOne;
	}
	
	@Override
	public Member findMemberId(String memberEmail) {
		Member mOne = mStore.selectMemberIdByEmail(session, memberEmail);
		return mOne;
	}

}
