package com.kh.tripply.point.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.point.service.PointService;
import com.kh.tripply.point.store.PointStore;

@Service
public class PointServiceImpl implements PointService {
	@Autowired
	PointStore pStore;
	@Autowired
	SqlSession session;
	
}
