package com.kh.tripply.review.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.review.store.ReviewStore;

@Service
public class ReviewServiceImpl {
	@Autowired
	ReviewStore rStore;
	@Autowired
	SqlSession session;
	
}
