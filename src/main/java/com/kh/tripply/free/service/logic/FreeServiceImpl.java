package com.kh.tripply.free.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.free.domain.Free;
import com.kh.tripply.free.domain.FreeReply;
import com.kh.tripply.free.service.FreeService;
import com.kh.tripply.free.store.FreeStore;

@Service
public class FreeServiceImpl implements FreeService{
	@Autowired
	private SqlSession session;
	@Autowired
	private FreeStore fStore;
	// 게시글 등록
	@Override
	public int registerBoard(Free free) {
		int result = fStore.insertBoard(session, free);
		return result;
	}
	// 게시글 수정
	@Override
	public int modifyBoard(Free free) {
		int result = fStore.updateBoard(session, free);
		return result;
	}
	// 게시글 삭제
	@Override
	public int removeOneByNo(int boardNo) {
		int result = fStore.deleteOneByNo(session, boardNo);
		return result;
	}
	// 게시글 전체 출력
	@Override
	public List<Free> printAllBoard(int currentPage, int boardLimit) {
		List<Free> fList = fStore.selectAllBoard(session, currentPage, boardLimit);
		return fList;
	}
	// 게시글 전체 개수
	@Override
	public int getTotalCount(String searchCondition, String searchValue) {
		int totalCount = fStore.selectTotalCount(session, searchCondition, searchValue);
		return totalCount;
	}
	// 게시글 번호로 검색
	@Override
	public Free printOneByNo(Integer boardNo) {
		Free free = fStore.selectOneByNo(session, boardNo);
		int result = 0;
		if(free != null) {
			result = fStore.updateBoardCount(session, boardNo);
		}
		return free;
	}
	@Override
	public List<Free> printAllByValue(
			String searchCondition, String searchValue
			, int currentPage, int boardLimit) {
		List<Free> fList 
		= fStore.selectAllByValue(session, searchCondition, searchValue, currentPage, boardLimit);
		return fList;
	}
	@Override
	public List<FreeReply> printAllReply(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int registerReply(FreeReply fReply) {
		int result = fStore.insertFreeReply(session, fReply);
		return result;
	}



}
