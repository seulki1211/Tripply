package com.kh.tripply.free.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.free.domain.Free;
import com.kh.tripply.free.store.FreeStore;

@Repository
public class FreeStoreLogic implements FreeStore{

	@Override
	public int insertBoard(SqlSession session, Free free) {
		int result = session.insert("FreeMapper.insertFreeBoard", free);
		return result;
	}

}
