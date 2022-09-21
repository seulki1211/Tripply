package com.kh.tripply.party.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.party.domain.Party;
import com.kh.tripply.party.domain.PartyReply;
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
	public List<Party> printAllParty(int currentPage, int boardLimit) {
		List<Party> pList = pStore.selectAllParty(currentPage, boardLimit, session);
		return pList;
	}
	
	@Override
	public int getTotalCount(String searchCondition, String searchValue,  String searchRegion) {
		int result = pStore.selectTotalCount(searchCondition, searchValue, searchRegion, session);
		return result;
	}

	@Override
	public Party printOneParty(int partyNo) {
		Party party = pStore.selectOneParty(partyNo, session);
		if(party != null) {
			pStore.updateBoardCount(partyNo, session);

		}
		
		return party;
	}

	@Override
	public int removeOneByNo(int partyNo) {
		int result = pStore.deleteOneByNo(partyNo, session);
		return result;
	}

	@Override
	public int modifyParty(Party party) {
		int result = pStore.updateParty(party, session);
		return result;
	}

	@Override
	public List<Party> printAllByValue(String searchCondition, String searchValue, String searchRegion, int currentPage, int boardLimit) {
		List<Party> pList = pStore.selectAllByValue(searchCondition, searchValue, searchRegion, currentPage, boardLimit, session);
		return pList;
	}

	@Override
	public int addPartyReply(PartyReply pReply) {
		int result = pStore.insertPartyReply(pReply, session);
		return result;
	}

	@Override
	public List<PartyReply> printAllPartyReply(int refBoardNo) {
		List<PartyReply> prList = pStore.selectAllPartyReply(refBoardNo, session);
		return prList;
	}

	@Override
	public int removeOneReply(int pReplyNo) {
		int result = pStore.deleteOneReply(pReplyNo, session);
		return result;
	}

	@Override
	public int modifyReply(PartyReply pReply) {
		int result = pStore.updateReply(pReply, session);
		return result;
	}

}
