package com.kh.tripply.free.store;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.free.domain.Free;

public interface FreeStore {

	int insertBoard(SqlSession session, Free free);

}
