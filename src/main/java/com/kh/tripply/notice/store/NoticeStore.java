package com.kh.tripply.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.notice.domain.Notice;

public interface NoticeStore {
	
	public int insertNotice(SqlSession session, Notice notice);

	public List<Notice> selectAllNotice(SqlSession session, int currentPage, int boardLimit);
	
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue);

	public Notice selectOneNotice(SqlSession session, int noticeNo);
	
	public int deleteNotice(SqlSession session, int noticeNo);
	
	public int updateNotice(SqlSession session, Notice notice);

	public int updateBoardCount(SqlSession session, int noticeNo);

}
