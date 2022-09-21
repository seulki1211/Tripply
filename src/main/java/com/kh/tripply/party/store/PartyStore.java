package com.kh.tripply.party.store;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.party.domain.Party;
import com.kh.tripply.party.domain.PartyReply;

public interface PartyStore {
	
	public int insertParty(Party party, SqlSession session);
	
	public List<Party> selectAllParty(int currentPage, int boardLimit, SqlSession session);

	public int selectTotalCount(String searchCondition,String searchValue,String searchRegion, SqlSession session);
	
	public Party selectOneParty(int partyNo, SqlSession session);

	public int deleteOneByNo(int partyNo, SqlSession session);
	
	public int updateParty(Party party, SqlSession session);

	public int updateBoardCount(int partyNo, SqlSession session);

	public List<Party> selectAllByValue(String searchCondition, String searchValue,String searchRegion,int currentPage,int boardLimit, SqlSession session);

	public int insertPartyReply(PartyReply pReply, SqlSession session);
	
	public List<PartyReply> selectAllPartyReply(int refBoardNo, SqlSession session);

	public int deleteOneReply(int pReplyNo, SqlSession session);
	
	public int updateReply(PartyReply pReply, SqlSession session);



}

