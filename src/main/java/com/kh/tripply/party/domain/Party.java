package com.kh.tripply.party.domain;

import java.sql.Date;

public class Party {
	private int partyNo;
	private String partyTitle;
	private String partyContents;
	private String partyWriter;
	private Date partyFirstDate;
	private Date partyLastDate;
	private String partyLocation;
	private String partyFileName;
	private String partyFileRename;
	private String partyFilePath;
	private int partyCount;
	private Date pCreateDate;
	private Date pUpdateDate;
	private String pStatus;
	
	
	public int getPartyNo() {
		return partyNo;
	}
	public void setPartyNo(int partyNo) {
		this.partyNo = partyNo;
	}
	public String getPartyTitle() {
		return partyTitle;
	}
	public void setPartyTitle(String partyTitle) {
		this.partyTitle = partyTitle;
	}
	public String getPartyContents() {
		return partyContents;
	}
	public void setPartyContents(String partyContents) {
		this.partyContents = partyContents;
	}
	public String getPartyWriter() {
		return partyWriter;
	}
	public void setPartyWriter(String partyWriter) {
		this.partyWriter = partyWriter;
	}
	public Date getPartyFirstDate() {
		return partyFirstDate;
	}
	public void setPartyFirstDate(Date partyFirstDate) {
		this.partyFirstDate = partyFirstDate;
	}
	public Date getPartyLastDate() {
		return partyLastDate;
	}
	public void setPartyLastDate(Date partyLastDate) {
		this.partyLastDate = partyLastDate;
	}
	public String getPartyLocation() {
		return partyLocation;
	}
	public void setPartyLocation(String partyLocation) {
		this.partyLocation = partyLocation;
	}
	public String getPartyFileName() {
		return partyFileName;
	}
	public void setPartyFileName(String partyFileName) {
		this.partyFileName = partyFileName;
	}
	public String getPartyFileRename() {
		return partyFileRename;
	}
	public void setPartyFileRename(String partyFileRename) {
		this.partyFileRename = partyFileRename;
	}
	public String getPartyFilePath() {
		return partyFilePath;
	}
	public void setPartyFilePath(String partyFilePath) {
		this.partyFilePath = partyFilePath;
	}
	public int getPartyCount() {
		return partyCount;
	}
	public void setPartyCount(int partyCount) {
		this.partyCount = partyCount;
	}
	public Date getpCreateDate() {
		return pCreateDate;
	}
	public void setpCreateDate(Date pCreateDate) {
		this.pCreateDate = pCreateDate;
	}
	public Date getpUpdateDate() {
		return pUpdateDate;
	}
	public void setpUpdateDate(Date pUpdateDate) {
		this.pUpdateDate = pUpdateDate;
	}
	public String getpStatus() {
		return pStatus;
	}
	public void setpStatus(String pStatus) {
		this.pStatus = pStatus;
	}
	
	
	
}

