package com.kh.tripply.party.service;

import java.util.List;

import com.kh.tripply.party.domain.Party;

public interface PartyService {

	public int registerParty(Party party);
	
	public List<Party> printAllParty(int currentPage, int boardLimit);

	public int getTotalCount(String searchCondition, String searchValue);
	
	public Party printOneParty(int partyNo);
	
	public int removeOneByNo(int partyNo);
}
