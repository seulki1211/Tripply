package com.kh.tripply.main.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.main.domain.Main;
import com.kh.tripply.main.store.MainStore;

@Repository
public class MainStoreLogic implements MainStore {

	@Override
	public List<Main> selectAllRecent(SqlSession session) {
		return session.selectList("MainMapper.selectRecent");
	}

}
