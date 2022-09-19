package com.kh.tripply.party.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.party.domain.Party;
import com.kh.tripply.party.service.PartyService;
import com.kh.tripply.party.store.PartyStore;

@Service
public class PartyServiceImple implements PartyService {

	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private PartyStore pStore;
	
	@Override
	public int registerParty(Party party) {
		int result = pStore.insertParty(party, session);
		return result;
	}

	@Override
	public List<Party> printAllBoard(int currentPage, int boardLimit) {
		List<Party> pList = pStore.selectAllParty(boardLimit, currentPage, session);
		return pList;
	}
	
	public int getTotalCount(String searchCondition, String searchValue) {
		int result = pStore.selectTotalCount(searchCondition, searchValue, session);
		return result;
	}
}
