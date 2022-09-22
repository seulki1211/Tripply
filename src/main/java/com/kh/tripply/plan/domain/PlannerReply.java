package com.kh.tripply.plan.domain;

import java.sql.Date;

public class PlannerReply {
	private int pReplyNo;
	private int boardNo;
	private String pReplyContents;
	private String pReplyWriter;
	private Date pRCreateDate;
	private Date pRUpdateDate;
	private String pRStatus;
	public int getpReplyNo() {
		return pReplyNo;
	}
	public void setpReplyNo(int pReplyNo) {
		this.pReplyNo = pReplyNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getpReplyContents() {
		return pReplyContents;
	}
	public void setpReplyContents(String pReplyContents) {
		this.pReplyContents = pReplyContents;
	}
	public String getpReplyWriter() {
		return pReplyWriter;
	}
	public void setpReplyWriter(String pReplyWriter) {
		this.pReplyWriter = pReplyWriter;
	}
	public Date getpRCreateDate() {
		return pRCreateDate;
	}
	public void setpRCreateDate(Date pRCreateDate) {
		this.pRCreateDate = pRCreateDate;
	}
	public Date getpRUpdateDate() {
		return pRUpdateDate;
	}
	public void setpRUpdateDate(Date pRUpdateDate) {
		this.pRUpdateDate = pRUpdateDate;
	}
	public String getpRStatus() {
		return pRStatus;
	}
	public void setpRStatus(String pRStatus) {
		this.pRStatus = pRStatus;
	}
	@Override
	public String toString() {
		return "PlannerReply [pReplyNo=" + pReplyNo + ", boardNo=" + boardNo + ", pReplyContents=" + pReplyContents
				+ ", pReplyWriter=" + pReplyWriter + ", pRCreateDate=" + pRCreateDate + ", pRUpdateDate=" + pRUpdateDate
				+ ", pRStatus=" + pRStatus + "]";
	}
	
	
	

}
