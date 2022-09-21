package com.kh.tripply.trade.domain;

import java.sql.Date;

public class TradeReply {
	private int tReplyNo ;
	private int boardNo;
	private int tRefReplyNo;
	private String tReplyWriter;
	private String tReplyContents;
	private Date trCreateDate;
	private Date trUpdateDate;
	private String trStatus;
	private String reReplyYn;
	
	public TradeReply() {}
	
	public TradeReply(int tReplyNo, int boardNo, int tRefReplyNo, String tReplyWriter, String tReplyContents,
			Date trCreateDate, Date trUpdateDate, String trStatus, String reReplyYn) {
		super();
		this.tReplyNo = tReplyNo;
		this.boardNo = boardNo;
		this.tRefReplyNo = tRefReplyNo;
		this.tReplyWriter = tReplyWriter;
		this.tReplyContents = tReplyContents;
		this.trCreateDate = trCreateDate;
		this.trUpdateDate = trUpdateDate;
		this.trStatus = trStatus;
		this.reReplyYn = reReplyYn;
	}

	public int gettReplyNo() {
		return tReplyNo;
	}

	public void settReplyNo(int tReplyNo) {
		this.tReplyNo = tReplyNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int gettRefReplyNo() {
		return tRefReplyNo;
	}

	public void settRefReplyNo(int tRefReplyNo) {
		this.tRefReplyNo = tRefReplyNo;
	}

	public String gettReplyWriter() {
		return tReplyWriter;
	}

	public void settReplyWriter(String tReplyWriter) {
		this.tReplyWriter = tReplyWriter;
	}

	public String gettReplyContents() {
		return tReplyContents;
	}

	public void settReplyContents(String tReplyContents) {
		this.tReplyContents = tReplyContents;
	}

	public Date getTrCreateDate() {
		return trCreateDate;
	}

	public void setTrCreateDate(Date trCreateDate) {
		this.trCreateDate = trCreateDate;
	}

	public Date getTrUpdateDate() {
		return trUpdateDate;
	}

	public void setTrUpdateDate(Date trUpdateDate) {
		this.trUpdateDate = trUpdateDate;
	}

	public String getTrStatus() {
		return trStatus;
	}

	public void setTrStatus(String trStatus) {
		this.trStatus = trStatus;
	}

	public String getReReplyYn() {
		return reReplyYn;
	}

	public void setReReplyYn(String reReplyYn) {
		this.reReplyYn = reReplyYn;
	}

	@Override
	public String toString() {
		return "TradeReply [tReplyNo=" + tReplyNo + ", boardNo=" + boardNo + ", tRefReplyNo=" + tRefReplyNo
				+ ", tReplyWriter=" + tReplyWriter + ", tReplyContents=" + tReplyContents + ", trCreateDate="
				+ trCreateDate + ", trUpdateDate=" + trUpdateDate + ", trStatus=" + trStatus + ", reReplyYn="
				+ reReplyYn + "]";
	}
	
	
	
	
	
	
}
