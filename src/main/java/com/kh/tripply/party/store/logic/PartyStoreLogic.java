package com.kh.tripply.party.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.party.common.Search;
import com.kh.tripply.party.domain.Party;
import com.kh.tripply.party.domain.PartyReply;
import com.kh.tripply.party.store.PartyStore;

@Repository
public class PartyStoreLogic implements PartyStore {

	@Override
	public int insertParty(Party party, SqlSession session) {
		int result = session.insert("PartyMapper.insertParty", party);
		return result;
	}

	@Override
	public List<Party> selectAllParty(int currentPage, int boardLimit, SqlSession session) {
		int offset = (currentPage - 1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset, boardLimit); 
		List<Party> pList = session.selectList("PartyMapper.selectAllParty", null, rowBounds);
		return pList;
	}

	@Override
	public int selectTotalCount(String searchCondition, String searchValue, String searchRegion, SqlSession session) {
		Search search = new Search(searchCondition, searchRegion, searchValue);
		int result = session.selectOne("PartyMapper.selectTotalCount", search);
		return result;
	}

	@Override
	public Party selectOneParty(int partyNo, SqlSession session) {
		Party party = session.selectOne("PartyMapper.selectOneParty", partyNo);
		return party;
	}

	@Override
	public int deleteOneByNo(int partyNo, SqlSession session) {
		int result = session.delete("PartyMapper.deleteOneParty", partyNo);
		return result;
	}

	@Override
	public int updateParty(Party party, SqlSession session) {
		int result = session.update("PartyMapper.updateOneParty", party);
		return result;
	}

	@Override
	public int updateBoardCount(int partyNo, SqlSession session) {
		int result = session.update("PartyMapper.updatePartyCount", partyNo);
		return result;
	}

	@Override
	public List<Party> selectAllByValue(String searchCondition, String searchValue, String searchRegion, int currentPage, int boardLimit,
			SqlSession session) {
		
		Search search = new Search(searchCondition, searchRegion, searchValue);
		int offset = (currentPage -1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset, boardLimit);
		
		
		List<Party> pList = session.selectList("PartyMapper.selectAllByValue", search, rowBounds);
		return pList;
	}
	
 // 댓글 관리
	
	// 댓글 등록
	@Override
	public int insertPartyReply(PartyReply pReply, SqlSession session) {
		
		int result = session.insert("PartyMapper.insertPartyReply", pReply);
		return result;
	}

	// 댓글 출력
	@Override
	public List<PartyReply> selectAllPartyReply(int refBoardNo, SqlSession session) {
		List<PartyReply> prList = session.selectList("PartyMapper.selectAllPartyReply", refBoardNo);
		return prList;
	}

	@Override
	public int deleteOneReply(int pReplyNo, SqlSession session) {
		 int result = session.delete("PartyMapper.deletePartyReply", pReplyNo);
		return result;
	}

	@Override
	public int updateReply(PartyReply pReply, SqlSession session) {
		int result = session.update("PartyMapper.updatePartyReply", pReply);
		return result;
	}

}