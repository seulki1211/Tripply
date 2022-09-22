package com.kh.tripply.free.domain;

import java.sql.Date;

public class FreeReply {
	private int freeReplyNo;
	private int boardNo;
	private String freeReplyWriter;
	private String freeReplyContents;
	private Date fCreateDate;
	private Date fUpdateDate;
	private String fStatus;
	
	// getter / setter
	public int getFreeReplyNo() {
		return freeReplyNo;
	}
	public void setFreeReplyNo(int freeReplyNo) {
		this.freeReplyNo = freeReplyNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getFreeReplyWriter() {
		return freeReplyWriter;
	}
	public void setFreeReplyWriter(String freeReplyWriter) {
		this.freeReplyWriter = freeReplyWriter;
	}
	public String getFreeReplyContents() {
		return freeReplyContents;
	}
	public void setFreeReplyContents(String freeReplyContents) {
		this.freeReplyContents = freeReplyContents;
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
	
	@Override
	public String toString() {
		return "FreeReply [freeReplyNo=" + freeReplyNo + ", boardNo=" + boardNo + ", freeReplyWriter=" + freeReplyWriter
				+ ", freeReplyContents=" + freeReplyContents + ", fCreateDate=" + fCreateDate + ", fUpdateDate="
				+ fUpdateDate + ", fStatus=" + fStatus + "]";
	}
	
	
}
