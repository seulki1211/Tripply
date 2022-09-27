package com.kh.tripply.free.service;

import java.util.List;

import com.kh.tripply.free.domain.Free;
import com.kh.tripply.free.domain.FreeReply;

public interface FreeService {
	// 게시글 등록
	public int registerBoard(Free free);
	// 게시글 수정
	public int modifyBoard(Free free);
	// 게시글 제거
	public int removeOneByNo(int boardNo);
	// 전체 게시글 출력
	public List<Free> printAllBoard(int currentPage, int boardLimit);
	// 게시글 전체 개수 
	public int getTotalCount(String SearchCondition, String SearchValue);
	// 게시글 번호로 검색
	public Free printOneByNo(Integer boardNo);
	public List<Free> printAllByValue(
			String searchCondition, String searchValue
			, int currentPage, int boardLimit);
	public List<FreeReply> printAllReply(int boardNo);
	// 댓글 관리
	public int registerReply(FreeReply fReply);
	public int modifyReply(FreeReply fReply);
	public int deleteReply(Integer freeReplyNo);
	// 내가 쓴 게시글
	public int getEveryTotalCount(String searchCondition, String searchValue);
	public List<Free> printEveryTbl(int currentPage, int boardLimit, String memberNickname);
	
}
