package com.kh.tripply.party.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.party.domain.Party;
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
	public int selectTotalCount(String searchCondition, String searchValue, SqlSession session) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		int result = session.selectOne("PartyMapper.selectTotalCount", paramMap);
		return result;
	}

}