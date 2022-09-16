package com.kh.tripply.party.store.logic;

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

}