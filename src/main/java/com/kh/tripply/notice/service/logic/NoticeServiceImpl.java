package com.kh.tripply.notice.service.logic;

import java.util.List;

import org.apache.ibatis.type.NStringTypeHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.notice.domain.Notice;
import com.kh.tripply.notice.service.NoticeService;
import com.kh.tripply.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeStore nStore;
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public int registerNotice(Notice notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}

	@Override
	public List<Notice> printAllNotice(int currentPage, int boardLimit) {
		List<Notice> nList = nStore.selectAllNotice(session, currentPage, boardLimit);
		return nList;
	}

	@Override
	public int getTotalCount(String searchCondition, String searchValue) {
		int totalCount = nStore.selectTotalCount(session, searchCondition, searchValue);
		return totalCount;
	}

	@Override
	public Notice printOneNotice(int noticeNo) {
		Notice notice = nStore.selectOneNotice(session, noticeNo);
		if(notice != null) {
			nStore.updateBoardCount(session, noticeNo);
		}
		return notice;
	}

	@Override
	public int removeOneByNo(int noticeNo) {
		int result = nStore.deleteNotice(session, noticeNo);
		return result;
	}

	@Override
	public int modifyNotice(Notice notice) {
		int result = nStore.updateNotice(session, notice);
		return result;
	}
	
}
