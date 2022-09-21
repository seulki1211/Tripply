package com.kh.tripply.free.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.free.domain.Free;
import com.kh.tripply.free.service.FreeService;
import com.kh.tripply.free.store.FreeStore;

@Service
public class FreeServiceImpl implements FreeService{
	@Autowired
	private SqlSession session;
	@Autowired
	private FreeStore fStore;

	@Override
	public int registerBoard(Free free) {
		int result = fStore.insertBoard(session, free);
		return result;
	}

}
