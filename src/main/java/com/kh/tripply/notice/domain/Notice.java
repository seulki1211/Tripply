package com.kh.tripply.notice.domain;

import java.sql.Date;

public class Notice {

	private int noticeNo;
	private String noticeTitle;
	private String noticeContents;
	private String noticeWriter;
	private int noticeCount;
	private Date nCreateDate;
	private Date nUpdateDate;
	private String nStatus;

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContents() {
		return noticeContents;
	}

	public void setNoticeContents(String noticeContents) {
		this.noticeContents = noticeContents;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public Date getnCreateDate() {
		return nCreateDate;
	}

	public void setnCreateDate(Date nCreateDate) {
		this.nCreateDate = nCreateDate;
	}

	public Date getnUpdateDate() {
		return nUpdateDate;
	}

	public void setnUpdateDate(Date nUpdateDate) {
		this.nUpdateDate = nUpdateDate;
	}

	public String getnStatus() {
		return nStatus;
	}

	public void setnStatus(String nStatus) {
		this.nStatus = nStatus;
	}

	public int getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContents=" + noticeContents
				+ ", noticeWriter=" + noticeWriter + ", nCreateDate=" + nCreateDate + ", nUpdateDate=" + nUpdateDate
				+ ", nStatus=" + nStatus + ", noticeCount=" + noticeCount + "]";
	}

}
