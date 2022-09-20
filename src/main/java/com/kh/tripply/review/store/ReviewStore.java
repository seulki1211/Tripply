package com.kh.tripply.review.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.review.common.Paging;
import com.kh.tripply.review.common.Search;
import com.kh.tripply.review.domain.Review;
import com.kh.tripply.review.domain.ReviewReply;

public interface ReviewStore {
	public int insertReview(SqlSession session,Review review);
	public List<Review> selectAllReview(SqlSession session,Paging paging);
	public Review selectOneReviewByNo(SqlSession session, int boardNo);
	public int getTotalCount(SqlSession session);
	public List<Review> selectSearchReview(SqlSession session,Search search,Paging paging);
	public int getSearchCount(SqlSession session,Search search);
	public int updateReviewByNo(SqlSession session,Review review);
	public int deleteReviewByNo(SqlSession session,Review review);

	////////////////////댓글
	public int insertReviewReply(SqlSession session, ReviewReply rReply);
	public List<ReviewReply> selectReviewReplyByNo(SqlSession session,int boardNo);

	////////////////////조회수 카운트
	public int reviewViewCount(SqlSession session, int boardNo);
}
