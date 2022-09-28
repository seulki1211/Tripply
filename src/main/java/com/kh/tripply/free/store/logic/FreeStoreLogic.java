package com.kh.tripply.free.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.free.domain.Free;
import com.kh.tripply.free.domain.FreeReply;
import com.kh.tripply.free.store.FreeStore;

@Repository
public class FreeStoreLogic implements FreeStore{

	@Override
	public int insertBoard(SqlSession session, Free free) {
		int result = session.insert("FreeMapper.insertFree", free);
		return result;
	}

	@Override
	public int updateBoard(SqlSession session, Free free) {
		int result = session.update("FreeMapper.updateFree", free);
		return result;
	}
	
	@Override
	public int deleteOneByNo(SqlSession session, int boardNo) {
		int result = session.delete("FreeMapper.deleteFree", boardNo);
		return result;
	}
	
	@Override
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		int totalCount = session.selectOne("FreeMapper.selectTotalCount", paramMap);
		return totalCount;
	}

	@Override
	public List<Free> selectAllBoard(SqlSession session, int currentPage, int boardLimit) {
		int offset = (currentPage-1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset, boardLimit);
		List<Free> fList = session.selectList("FreeMapper.selectAllBoard", null, rowBounds);
		return fList;
	}

	@Override
	public Free selectOneByNo(SqlSession session, Integer boardNo) {
		Free free = session.selectOne("FreeMapper.selectOneByNo", boardNo);
		return free;
	}

	@Override
	public int updateBoardCount(SqlSession session, int boardNo) {
		int result = session.update("FreeMapper.updateCount", boardNo);
		return result;
	}

	@Override
	public List<Free> selectAllByValue(
			SqlSession session, String searchCondition
			, String searchValue , int currentPage, int boardLimit) {
		int offset = (currentPage-1)*boardLimit;
		RowBounds rowBounds 
		= new RowBounds(offset, boardLimit);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		List<Free> fList 
		= session.selectList("FreeMapper.selectAllByValue"
				, paramMap, rowBounds);
		return fList;
	}

	@Override
	public List<FreeReply> selectAllReply(SqlSession session, int boardNo) {
		List<FreeReply> fRList = session.selectList("FreeReplyMapper.selectAllReply", boardNo);
		return fRList;
	}
	
	@Override
	public int insertFreeReply(SqlSession session, FreeReply fReply) {
		int result = session.insert("FreeReplyMapper.insertFreeReply", fReply);
		return result;
	}
	
	@Override
	public int updateFreeReply(SqlSession session, FreeReply fReply) {
		int result = session.update("FreeReplyMapper.updateFreeReply", fReply);
		return result;
	}

	@Override
	public int deleteFreeReply(SqlSession session, Integer freeReplyNo) {
		int result = session.delete("FreeReplyMapper.deleteFreeReply", freeReplyNo);
		return result;
	}

	// 내가 쓴 게시글
	@Override
	public int selectEveryTotalCount(SqlSession session, String searchCondition, String searchValue) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		int everyTotalCount = session.selectOne("FreeMapper.selectEveryTotalCount", paramMap);
		return everyTotalCount;
	}

	@Override
	public List<Free> selectAllTbl(SqlSession session, int currentPage, int boardLimit, String memberNickname) {
		int offset = (currentPage-1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset, boardLimit);
		List<Free> fList = session.selectList("FreeMapper.selectAllTbl", memberNickname, rowBounds);
		return fList;
	}


}
