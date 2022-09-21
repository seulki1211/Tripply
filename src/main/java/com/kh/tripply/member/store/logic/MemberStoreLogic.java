package com.kh.tripply.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
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
		int result = session.update("MemberMapper.updateMember", member);
		return result;
	}

	@Override
	public int deleteMember(SqlSession session, String memberId) {
		int result = session.delete("MemberMapper.deleteMember", memberId);
		return result;
	}

	@Override
	public Member selectMemberId(SqlSessionTemplate session, String memberId) {
		Member mOne = session.selectOne("MemberMapper.selectMemberId", memberId);
		return mOne;
	}

}
