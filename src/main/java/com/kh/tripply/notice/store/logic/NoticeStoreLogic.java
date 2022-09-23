package com.kh.tripply.notice.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.notice.domain.Notice;
import com.kh.tripply.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore {

	@Override
	public int insertNotice(SqlSession session, Notice notice) {
		int result = session.insert("noticeMapper.insertNotice", notice);
		return result;
	}

	@Override
	public int deleteNotice(SqlSession session, int noticeNo) {
		int result = session.update("noticeMapper.deleteNotice", noticeNo);
		return result;
	}

	@Override
	public int updateNotice(SqlSession session, Notice notice) {
		int result = session.update("noticeMapper.updateNotice", notice);
		return result;
	}

	@Override
	public int updateBoardCount(SqlSession session, int noticeNo) {
		int result = session.update("noticeMapper.updateNoticeCount", noticeNo);
		return result;
	}

	@Override
	public Notice selectOneNotice(SqlSession session, int noticeNo) {
		Notice notice = session.selectOne("noticeMapper.selectOneNotice", noticeNo);
		return notice;
	}

	@Override
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		int totalCount = session.selectOne("noticeMapper.selectTotalCount",paramMap);
		return totalCount;
	}

	@Override
	public List<Notice> selectAllNotice(SqlSession session, int currentPage, int boardLimit) {
		int offset = (currentPage - 1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset, boardLimit); 
		List<Notice> nList = session.selectList("noticeMapper.selectAllNotice", null, rowBounds);
		return nList;
	}

	@Override
	public List<Notice> selectChoosedNotice(SqlSession session) {
		 List<Notice> nList = session.selectList("noticeMapper.selectChoosedNotice");
		return nList;
	}

	@Override
	public int countChoosedNotice(SqlSession session) {
		int result = session.selectOne("noticeMapper.countChoosedNotice");
		return result;
	}

	@Override
	public int updateStatusN(SqlSession session, int noticeNo) {
		int result = session.update("noticeMapper.updateStatusN", noticeNo);
		return result;
	}

	@Override
	public int updateStatusY(SqlSession session, int noticeNo) {
		int result = session.update("noticeMapper.updateStatusY", noticeNo);
		return result;
	}
	
	



}
