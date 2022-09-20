package com.kh.tripply.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.member.domain.Member;
import com.kh.tripply.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{

	@Override
	public Member selectLoginMember(SqlSession session, Member member) {
		Member mOne = session.selectOne("MemberMapper.selectLoginMember", member);
		return mOne;
	}

	@Override
	public int insertMember(SqlSession session, Member member) {
		int result = session.insert("MemberMapper.insertMember", member);
		return result;
	}

	@Override
	public int updatemember(SqlSession session, Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(SqlSession session, String memberId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
