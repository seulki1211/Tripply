package com.kh.tripply.review.service;

import java.util.List;

import com.kh.tripply.review.common.Paging;
import com.kh.tripply.review.common.Search;
import com.kh.tripply.review.domain.Review;
import com.kh.tripply.review.domain.ReviewReply;

public interface ReviewService {
	public int registerReview(Review review);
	public List<Review> printAllReview(Paging paging);
	public Review printOneReviewByNo(int boardNo);
	public int getTotalCount();
	public List<Review> printSearchReview(Search search,Paging paging);
	public int getSearchCount(Search search);
	public int modifyReviewByNo(Review review);
	public int removeReviewByNo(Review review);

	////////////////////댓글
	public int registerReviewReply(ReviewReply rReply);
	public List<ReviewReply> printReviewReplyByNo(int boardNo);
	
	////////////////////조회수 카운트
	public int reviewViewCount(int boardNo);
}
