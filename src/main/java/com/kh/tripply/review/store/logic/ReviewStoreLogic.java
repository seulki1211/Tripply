package com.kh.tripply.review.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.review.common.Paging;
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
	public List<Review> selectAllReview(SqlSession session, Paging paging) {
		RowBounds rowBounds = new RowBounds(paging.getOffset(), paging.getPageLimit());
		List<Review> rList = session.selectList("ReviewMapper.selectAllReview",null,rowBounds);
		return rList;
	}

	@Override
	public int getTotalCount(SqlSession session) {
		int totalCount = session.selectOne("ReviewMapper.selectTotalCount");
		return totalCount;
	}

	@Override
	public Review selectDetailReviewByNo(SqlSession session, int boardNo) {
		Review review  = session.selectOne("ReviewMapper.selectOneReview", boardNo);
		return review;
	}

	@Override
	public int deleteReviewByNo(SqlSession session, Review review) {
			int result = session.delete("ReviewMapper.deleteOneReview", review);
		return result;
	}

}
