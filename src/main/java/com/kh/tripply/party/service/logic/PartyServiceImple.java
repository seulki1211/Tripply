package com.kh.tripply.party.service.logic;

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

}

