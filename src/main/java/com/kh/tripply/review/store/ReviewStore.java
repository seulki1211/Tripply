package com.kh.tripply.review.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.review.common.Paging;
import com.kh.tripply.review.common.Search;
import com.kh.tripply.review.domain.Review;

public interface ReviewStore {
	public int insertReview(SqlSession session,Review review);
	public List<Review> selectAllReview(SqlSession session,Paging paging);
	public Review selectOneReviewByNo(SqlSession session, int boardNo);
	public int getTotalCount(SqlSession session);
	public List<Review> selectSearchReview(SqlSession session,Search search,Paging paging);
	public int getSearchCount(SqlSession session,Search search);
	public int updateReviewByNo(SqlSession session,Review review);
	public int deleteReviewByNo(SqlSession session,Review review);
	
}
