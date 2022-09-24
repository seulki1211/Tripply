package com.kh.tripply.review.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
import com.kh.tripply.review.domain.Review;
import com.kh.tripply.review.domain.ReviewReply;

public interface ReviewStore {
	public int insertReview(SqlSessionTemplate session,Review review);
	public List<Review> selectAllReview(SqlSessionTemplate session,Paging paging);
	public Review selectOneReviewByNo(SqlSessionTemplate session, int boardNo);
	public int getTotalCount(SqlSessionTemplate session);
	public List<Review> selectSearchReview(SqlSessionTemplate session,Search search,Paging paging);
	public int getSearchCount(SqlSessionTemplate session,Search search);
	public int updateReviewByNo(SqlSessionTemplate session,Review review);
	public int deleteReviewByNo(SqlSessionTemplate session,Review review);

	////////////////////댓글
	public int insertReviewReply(SqlSessionTemplate session, ReviewReply rReply);
	public List<ReviewReply> selectReviewReplyByNo(SqlSessionTemplate session,int boardNo);
	public int deleteReviewReply(SqlSessionTemplate session,ReviewReply rReply);
	public int updateReviewReply(SqlSessionTemplate session,ReviewReply rReply);
	
	////////////////////조회수 카운트
	public int reviewViewCount(SqlSessionTemplate session, int boardNo);
}
