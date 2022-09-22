package com.kh.tripply.trade.domain;

import java.sql.Date;

public class Trade {
	private int boardNo;
	private String tradeTitle;
	private String tradeContents;
	private String tradeWriter;
	private String tLocationCode;
	private String tLocationName;
	private String tradeFileName;
	private String tradeFileReName;
	private String tradeFilePath;
	private int tradeCount;
	private Date tCreateDate;
	private Date tUpdateDate;
	private String tStatus;
	private String buyerId;
	private String soldOut;
	private String thumbnailPath;

	public Trade() {
	}

	public Trade(int boardNo, String tradeTitle, String tradeContents, String tradeWriter, String tLocationCode,
			String tLocationName, String tradeFileName, String tradeFileReName, String tradeFilePath, int tradeCount,
			Date tCreateDate, Date tUpdateDate, String tStatus, String buyerId, String soldOut, String thumbnailPath) {
		super();
		this.boardNo = boardNo;
		this.tradeTitle = tradeTitle;
		this.tradeContents = tradeContents;
		this.tradeWriter = tradeWriter;
		this.tLocationCode = tLocationCode;
		this.tLocationName = tLocationName;
		this.tradeFileName = tradeFileName;
		this.tradeFileReName = tradeFileReName;
		this.tradeFilePath = tradeFilePath;
		this.tradeCount = tradeCount;
		this.tCreateDate = tCreateDate;
		this.tUpdateDate = tUpdateDate;
		this.tStatus = tStatus;
		this.buyerId = buyerId;
		this.soldOut = soldOut;
		this.thumbnailPath = thumbnailPath;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTradeTitle() {
		return tradeTitle;
	}

	public void setTradeTitle(String tradeTitle) {
		this.tradeTitle = tradeTitle;
	}

	public String getTradeContents() {
		return tradeContents;
	}

	public void setTradeContents(String tradeContents) {
		this.tradeContents = tradeContents;
	}

	public String getTradeWriter() {
		return tradeWriter;
	}

	public void setTradeWriter(String tradeWriter) {
		this.tradeWriter = tradeWriter;
	}

	public String gettLocationCode() {
		return tLocationCode;
	}

	public void settLocationCode(String tLocationCode) {
		this.tLocationCode = tLocationCode;
	}

	public String gettLocationName() {
		return tLocationName;
	}

	public void settLocationName(String tLocationName) {
		this.tLocationName = tLocationName;
	}

	public String getTradeFileName() {
		return tradeFileName;
	}

	public void setTradeFileName(String tradeFileName) {
		this.tradeFileName = tradeFileName;
	}

	public String getTradeFileReName() {
		return tradeFileReName;
	}

	public void setTradeFileReName(String tradeFileReName) {
		this.tradeFileReName = tradeFileReName;
	}

	public String getTradeFilePath() {
		return tradeFilePath;
	}

	public void setTradeFilePath(String tradeFilePath) {
		this.tradeFilePath = tradeFilePath;
	}

	public int getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}

	public Date gettCreateDate() {
		return tCreateDate;
	}

	public void settCreateDate(Date tCreateDate) {
		this.tCreateDate = tCreateDate;
	}

	public Date gettUpdateDate() {
		return tUpdateDate;
	}

	public void settUpdateDate(Date tUpdateDate) {
		this.tUpdateDate = tUpdateDate;
	}

	public String gettStatus() {
		return tStatus;
	}

	public void settStatus(String tStatus) {
		this.tStatus = tStatus;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getSoldOut() {
		return soldOut;
	}

	public void setSoldOut(String soldOut) {
		this.soldOut = soldOut;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	@Override
	public String toString() {
		return "Trade [boardNo=" + boardNo + ", tradeTitle=" + tradeTitle + ", tradeContents=" + tradeContents
				+ ", tradeWriter=" + tradeWriter + ", tLocationCode=" + tLocationCode + ", tLocationName="
				+ tLocationName + ", tradeFileName=" + tradeFileName + ", tradeFileReName=" + tradeFileReName
				+ ", tradeFilePath=" + tradeFilePath + ", tradeCount=" + tradeCount + ", tCreateDate=" + tCreateDate
				+ ", tUpdateDate=" + tUpdateDate + ", tStatus=" + tStatus + ", buyerId=" + buyerId + ", soldOut="
				+ soldOut + ", thumbnailPath=" + thumbnailPath + "]";
	}

}
