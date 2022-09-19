package com.kh.tripply.notice.service;

import java.util.List;

import com.kh.tripply.notice.domain.Notice;

public interface NoticeService {
	
	public int registerNotice(Notice notice);

	public List<Notice> printAllNotice(int currentPage, int boardLimit);
	
	public int getTotalCount(String searchCondition, String searchValue);
	
	public Notice printOneNotice(int noticeNo);
	
	public int removeOneByNo(int noticeNo);
	
	public int modifyNotice(Notice notice);

}
 