package com.kh.tripply.main.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.main.domain.Main;
import com.kh.tripply.main.service.MainService;
import com.kh.tripply.main.store.MainStore;

@Service
public class MainServiceLogic implements MainService {
	@Autowired
	MainStore mainStore;
	@Autowired
	SqlSession session;

	@Override
	public List<Main> printAllRecent() {
		return mainStore.selectAllRecent(session);
	}


}
