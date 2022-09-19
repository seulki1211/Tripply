package com.kh.tripply.free.domain;

import java.sql.Date;

public class FreeBoard {
	private int boardNo;
	private String freeTitle;
	private String freeContents;
	private String freeWriter;
	private String freeFilename;
	private String freeRename;
	private String freeFilepath;
	private int freeCount;
	private Date fCreateDate;
	private Date fUpdateDate;
	private String fStatus;
	
	// 기본생성자
	public FreeBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	// 전체 매개변수 생성자
	public FreeBoard(int boardNo, String freeTitle, String freeContents, String freeWriter, String freeFilename,
			String freeRename, String freeFilepath, int freeCount, Date fCreateDate, Date fUpdateDate, String fStatus) {
		super();
		this.boardNo = boardNo;
		this.freeTitle = freeTitle;
		this.freeContents = freeContents;
		this.freeWriter = freeWriter;
		this.freeFilename = freeFilename;
		this.freeRename = freeRename;
		this.freeFilepath = freeFilepath;
		this.freeCount = freeCount;
		this.fCreateDate = fCreateDate;
		this.fUpdateDate = fUpdateDate;
		this.fStatus = fStatus;
	}
	
	// getter / setter
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

	public String getFreeRename() {
		return freeRename;
	}

	public void setFreeRename(String freeRename) {
		this.freeRename = freeRename;
	}

	public String getFreeFilepath() {
		return freeFilepath;
	}

	public void setFreeFilepath(String freeFilepath) {
		this.freeFilepath = freeFilepath;
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
	
	// toString
	@Override
	public String toString() {
		return "FreeBoard [boardNo=" + boardNo + ", freeTitle=" + freeTitle + ", freeContents=" + freeContents
				+ ", freeWriter=" + freeWriter + ", freeFilename=" + freeFilename + ", freeRename=" + freeRename
				+ ", freeFilepath=" + freeFilepath + ", freeCount=" + freeCount + ", fCreateDate=" + fCreateDate
				+ ", fUpdateDate=" + fUpdateDate + ", fStatus=" + fStatus + "]";
	}
	
}
