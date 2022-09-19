package com.kh.tripply.party.store;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.party.domain.Party;

public interface PartyStore {
	
	public int insertParty(Party party, SqlSession session);
	
	public List<Party> selectAllParty(int currentPage, int boardLimit, SqlSession session);

	public int selectTotalCount(String searchCondition,String searchValue,SqlSession session);
}

