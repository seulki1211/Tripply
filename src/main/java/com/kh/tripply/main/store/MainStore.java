package com.kh.tripply.main.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.main.domain.Main;

public interface MainStore {

	public List<Main> selectAllRecent(SqlSession session);
}
