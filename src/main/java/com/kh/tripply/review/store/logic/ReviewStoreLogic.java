package com.kh.tripply.review.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
import com.kh.tripply.review.domain.Review;
import com.kh.tripply.review.domain.ReviewReply;
import com.kh.tripply.review.store.ReviewStore;

@Repository
public class ReviewStoreLogic implements ReviewStore{

	@Override
	public int insertReview(SqlSessionTemplate session, Review review) {
		int result = session.insert("ReviewMapper.insertReview", review);
		return result;
	}

	@Override
	public List<Review> selectAllReview(SqlSessionTemplate session, Paging paging) {
		RowBounds rowBounds = new RowBounds(paging.getOffset(), paging.getPageLimit());
		List<Review> rList = session.selectList("ReviewMapper.selectAllReview",null,rowBounds);
		return rList;
	}

	@Override
	public int getTotalCount(SqlSessionTemplate session) {
		int totalCount = session.selectOne("ReviewMapper.selectTotalCount");
		return totalCount;
	}

	@Override
	public Review selectOneReviewByNo(SqlSessionTemplate session, int boardNo) {
		Review review  = session.selectOne("ReviewMapper.selectOneReview", boardNo);
		return review;
	}

	@Override
	public int deleteReviewByNo(SqlSessionTemplate session, Review review) {
			int result = session.delete("ReviewMapper.deleteOneReview", review);
		return result;
	}

	@Override
	public int updateReviewByNo(SqlSessionTemplate session,Review review) {
		int result = session.update("ReviewMapper.updateReview", review);
		return result;
	}

	@Override
	public List<Review> selectSearchReview(SqlSessionTemplate session, Search search, Paging paging) {
		List<Review> rList = session.selectList("ReviewMapper.selectSearchReview", search, new RowBounds(paging.getOffset(), paging.getPageLimit()));
		return rList;
	}

	@Override
	public int getSearchCount(SqlSessionTemplate session, Search search) {
		int result = session.selectOne("ReviewMapper.selectSearchCount",search);
		return result;
	}

	@Override
	public int insertReviewReply(SqlSessionTemplate session, ReviewReply rReply) {
		int result = session.insert("ReviewReplyMapper.insertReviewReply", rReply);
		return result;
	}

	@Override
	public List<ReviewReply> selectReviewReplyByNo(SqlSessionTemplate session,int boardNo) {
		List<ReviewReply> rReplyList = session.selectList("ReviewReplyMapper.selectReviewReply", boardNo);
		return rReplyList;
	}

	@Override
	public int reviewViewCount(SqlSessionTemplate session, int boardNo) {
		int result = session.update("ReviewMapper.updateCountReview", boardNo);
		return result;
	}

}
