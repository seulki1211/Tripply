package com.kh.tripply.party.store;


import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.party.domain.Party;

public interface PartyStore {
	
	public int insertParty(Party party, SqlSession session);


}

