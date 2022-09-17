package com.kh.tripply.review.store;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.review.domain.Review;

public interface ReviewStore {
	public int insertReview(SqlSession session,Review review);
//	public void selectAllReview(SqlSession session);
//	public void updateReviewById(SqlSession session);
//	public void deleteReviewById(SqlSession session);
	
}
