package com.kh.tripply.free.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.free.domain.Free;
import com.kh.tripply.free.domain.FreeReply;

public interface FreeStore {
	// 게시글 등록
	public int insertBoard(SqlSession session, Free free);
	// 게시글 수정
	public int updateBoard(SqlSession session, Free free);
	
	public int updateBoardCount(SqlSession session, int boardNo);
	// 게시글 삭제
	public int deleteOneByNo(SqlSession session, int boardNo);
	// 게시글 전체 출력
	public List<Free> selectAllBoard(SqlSession session, int currentPage, int boardLimit);
	// 게시글 전체 개수
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue);
	// 게시글 번호로 검색
	public Free selectOneByNo(SqlSession session, Integer boardNo);
	public List<Free> selectAllByValue(SqlSession session, String searchCondition, String searchValue, int currentPage, int boardLimit);
	// 댓글 관리
	public List<FreeReply> selectAllReply(SqlSession session, int boardNo);
	public int insertFreeReply(SqlSession session, FreeReply fReply);
	public int updateFreeReply(SqlSession session, FreeReply fReply);
	public int deleteFreeReply(SqlSession session, Integer freeReplyNo);
	// 내가 쓴 게시글
	public int selectEveryTotalCount(SqlSession session, String searchCondition, String searchValue);
	public List<Free> selectAllTbl(SqlSession session, int currentPage, int boardLimit, String memberNickname);
	




}
