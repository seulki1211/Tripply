package com.kh.tripply.party.domain;

import java.sql.Date;

public class PartyReply {
   	private int pReplyNo;
	private int refBoardNo;
	private String pReplyWriter;
	private String pReplyContents;
	private Date prCreateDate;
	private Date prUpdateDate;
	private String prStatus;
	
	public int getpReplyNo() {
		return pReplyNo;
	}
	public void setpReplyNo(int pReplyNo) {
		this.pReplyNo = pReplyNo;
	}
	public int getRefBoardNo() {
		return refBoardNo;
	}
	public void setRefBoardNo(int refBoardNo) {
		this.refBoardNo = refBoardNo;
	}
	public String getpReplyWriter() {
		return pReplyWriter;
	}
	public void setpReplyWriter(String pReplyWriter) {
		this.pReplyWriter = pReplyWriter;
	}
	public String getpReplyContents() {
		return pReplyContents;
	}
	public void setpReplyContents(String pReplyContents) {
		this.pReplyContents = pReplyContents;
	}
	public Date getPrCreateDate() {
		return prCreateDate;
	}
	public void setPrCreateDate(Date prCreateDate) {
		this.prCreateDate = prCreateDate;
	}
	public Date getPrUpdateDate() {
		return prUpdateDate;
	}
	public void setPrUpdateDate(Date prUpdateDate) {
		this.prUpdateDate = prUpdateDate;
	}
	public String getPrStatus() {
		return prStatus;
	}
	public void setPrStatus(String prStatus) {
		this.prStatus = prStatus;
	}
	@Override
	public String toString() {
		return "PartyReply [pReplyNo=" + pReplyNo + ", refBoardNo=" + refBoardNo + ", pReplyWriter=" + pReplyWriter
				+ ", pReplyContents=" + pReplyContents + ", prCreateDate=" + prCreateDate + ", prUpdateDate="
				+ prUpdateDate + ", prStatus=" + prStatus + "]";
	}
	
	
	
	
	
	
}
