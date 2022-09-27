package com.kh.tripply.free.domain;

import java.sql.Date;

public class Free {
	private int boardNo;
	private String freeTitle;
	private String freeContents;
	private String freeWriter;
	private String freeFilename;
	private String freeFileRename;
	private String freeFilePath;
	private int freeCount;
	private Date fCreateDate;
	private Date fUpdateDate;
	private String fStatus;
	private String category;
	
	// 기본생성자
	public Free() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Free(int boardNo, String freeTitle, String freeContents, String freeWriter, String freeFilename,
			String freeFileRename, String freeFilePath, int freeCount, Date fCreateDate, Date fUpdateDate,
			String fStatus, String category) {
		super();
		this.boardNo = boardNo;
		this.freeTitle = freeTitle;
		this.freeContents = freeContents;
		this.freeWriter = freeWriter;
		this.freeFilename = freeFilename;
		this.freeFileRename = freeFileRename;
		this.freeFilePath = freeFilePath;
		this.freeCount = freeCount;
		this.fCreateDate = fCreateDate;
		this.fUpdateDate = fUpdateDate;
		this.fStatus = fStatus;
		this.category = category;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getFreeTitle() {
		return freeTitle;
	}

	public void setFreeTitle(String freeTitle) {
		this.freeTitle = freeTitle;
	}

	public String getFreeContents() {
		return freeContents;
	}

	public void setFreeContents(String freeContents) {
		this.freeContents = freeContents;
	}

	public String getFreeWriter() {
		return freeWriter;
	}

	public void setFreeWriter(String freeWriter) {
		this.freeWriter = freeWriter;
	}

	public String getFreeFilename() {
		return freeFilename;
	}

	public void setFreeFilename(String freeFilename) {
		this.freeFilename = freeFilename;
	}

	public String getFreeFileRename() {
		return freeFileRename;
	}

	public void setFreeFileRename(String freeFileRename) {
		this.freeFileRename = freeFileRename;
	}

	public String getFreeFilePath() {
		return freeFilePath;
	}

	public void setFreeFilePath(String freeFilePath) {
		this.freeFilePath = freeFilePath;
	}

	public int getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(int freeCount) {
		this.freeCount = freeCount;
	}

	public Date getfCreateDate() {
		return fCreateDate;
	}

	public void setfCreateDate(Date fCreateDate) {
		this.fCreateDate = fCreateDate;
	}

	public Date getfUpdateDate() {
		return fUpdateDate;
	}

	public void setfUpdateDate(Date fUpdateDate) {
		this.fUpdateDate = fUpdateDate;
	}

	public String getfStatus() {
		return fStatus;
	}

	public void setfStatus(String fStatus) {
		this.fStatus = fStatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Free [boardNo=" + boardNo + ", freeTitle=" + freeTitle + ", freeContents=" + freeContents
				+ ", freeWriter=" + freeWriter + ", freeFilename=" + freeFilename + ", freeFileRename=" + freeFileRename
				+ ", freeFilePath=" + freeFilePath + ", freeCount=" + freeCount + ", fCreateDate=" + fCreateDate
				+ ", fUpdateDate=" + fUpdateDate + ", fStatus=" + fStatus + ", category=" + category + "]";
	}
	
	
	
	
}
