package com.kh.tripply.review.service;

import java.util.List;

import com.kh.tripply.review.common.Paging;
import com.kh.tripply.review.domain.Review;

public interface ReviewService {
	public int registerReview(Review review);
	public List<Review> printAllReview(Paging paging);
	public int getTotalCount();
//	public void modifyReviewById();
//	public void removeReviewById();
}
