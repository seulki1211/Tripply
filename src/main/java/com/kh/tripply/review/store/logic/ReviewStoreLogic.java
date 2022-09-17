package com.kh.tripply.review.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.review.domain.Review;
import com.kh.tripply.review.store.ReviewStore;

@Repository
public class ReviewStoreLogic implements ReviewStore{

	@Override
	public int insertReview(SqlSession session, Review review) {
		int result = session.insert("ReviewMapper.insertReview", review);
		return result;
	}

	@Override
	public List<Review> selectAllReview(SqlSession session) {
		List<Review> rList = session.selectList("ReviewMapper.selectAllReview");
		return rList;
	}

}
