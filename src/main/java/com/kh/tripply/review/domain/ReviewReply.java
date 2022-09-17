package com.kh.tripply.review.domain;

import java.sql.Date;

public class ReviewReply {
   	private int rReplyNo;
	private int boardNo;
	private int rRefReplyNo;
	private String rReplyWriter;
	private String rReplyContents;
	private Date rrCreateDate;
	private Date rrUpdateDate;
	private String rrStatus;
	
	public ReviewReply() {}
	
	public ReviewReply(int rReplyNo, int boardNo, int rRefReplyNo, String rReplyWriter, String rReplyContents,
			Date rrCreateDate, Date rrUpdateDate, String rrStatus) {
		super();
		this.rReplyNo = rReplyNo;
		this.boardNo = boardNo;
		this.rRefReplyNo = rRefReplyNo;
		this.rReplyWriter = rReplyWriter;
		this.rReplyContents = rReplyContents;
		this.rrCreateDate = rrCreateDate;
		this.rrUpdateDate = rrUpdateDate;
		this.rrStatus = rrStatus;
	}

	public int getrReplyNo() {
		return rReplyNo;
	}

	public void setrReplyNo(int rReplyNo) {
		this.rReplyNo = rReplyNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getrRefReplyNo() {
		return rRefReplyNo;
	}

	public void setrRefReplyNo(int rRefReplyNo) {
		this.rRefReplyNo = rRefReplyNo;
	}

	public String getrReplyWriter() {
		return rReplyWriter;
	}

	public void setrReplyWriter(String rReplyWriter) {
		this.rReplyWriter = rReplyWriter;
	}

	public String getrReplyContents() {
		return rReplyContents;
	}

	public void setrReplyContents(String rReplyContents) {
		this.rReplyContents = rReplyContents;
	}

	public Date getRrCreateDate() {
		return rrCreateDate;
	}

	public void setRrCreateDate(Date rrCreateDate) {
		this.rrCreateDate = rrCreateDate;
	}

	public Date getRrUpdateDate() {
		return rrUpdateDate;
	}

	public void setRrUpdateDate(Date rrUpdateDate) {
		this.rrUpdateDate = rrUpdateDate;
	}

	public String getRrStatus() {
		return rrStatus;
	}

	public void setRrStatus(String rrStatus) {
		this.rrStatus = rrStatus;
	}

	@Override
	public String toString() {
		return "ReviewReply [rReplyNo=" + rReplyNo + ", boardNo=" + boardNo + ", rRefReplyNo=" + rRefReplyNo
				+ ", rReplyWriter=" + rReplyWriter + ", rReplyContents=" + rReplyContents + ", rrCreateDate="
				+ rrCreateDate + ", rrUpdateDate=" + rrUpdateDate + ", rrStatus=" + rrStatus + "]";
	}
	
	
	
	
   	
}
