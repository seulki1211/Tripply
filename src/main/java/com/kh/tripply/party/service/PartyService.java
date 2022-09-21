package com.kh.tripply.party.service;

import java.util.List;

import com.kh.tripply.party.domain.Party;
import com.kh.tripply.party.domain.PartyReply;

public interface PartyService {
	

	public int registerParty(Party party);
	
	public List<Party> printAllParty(int currentPage, int boardLimit);

	public int getTotalCount(String searchCondition, String searchValue, String searchRegion);
	
	public Party printOneParty(int partyNo);
	
	public int removeOneByNo(int partyNo);
	
	public int modifyParty(Party party);
	
	public List<Party> printAllByValue(String searchCondition, String searchValue, String searchRegion, int currentPage,int boardLimit);

	public int addPartyReply(PartyReply pReply);
	
	public List<PartyReply> printAllPartyReply(int refBoardNo);

	public int removeOneReply(int pReplyNo);

	public int modifyReply(PartyReply pReply);
}
