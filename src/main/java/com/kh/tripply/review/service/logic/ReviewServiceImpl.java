package com.kh.tripply.review.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.review.common.Paging;
import com.kh.tripply.review.common.Search;
import com.kh.tripply.review.domain.Review;
import com.kh.tripply.review.service.ReviewService;
import com.kh.tripply.review.store.ReviewStore;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewStore rStore;
	@Autowired
	SqlSession session;
	@Override
	public int registerReview(Review review) {
		int result = rStore.insertReview(session, review);
		return result;
	}
	@Override
	public List<Review> printAllReview(Paging paging) {
		List<Review> rList = rStore.selectAllReview(session,paging);
		return rList;
	}
	@Override
	public int getTotalCount() {
		int totalCount = rStore.getTotalCount(session);
		return totalCount;
	}
	@Override
	public Review printOneReviewByNo(int boardNo) {
		Review review = rStore.selectOneReviewByNo(session, boardNo);
		return review;
	}
	@Override
	public int removeReviewByNo(Review review) {
		int result = rStore.deleteReviewByNo(session, review);
		return result;
	}
	@Override
	public int modifyReviewByNo(Review review) {
		int result = rStore.updateReviewByNo(session,review);
		return result;
	}
	@Override
	public List<Review> printSearchReview(Search search, Paging paging) {
		List<Review> rList = rStore.selectSearchReview(session,search,paging);
		return rList;
	}
	@Override
	public int getSearchCount(Search search) {
		int result = rStore.getSearchCount(session,search);
		return result;
	}
	
}
