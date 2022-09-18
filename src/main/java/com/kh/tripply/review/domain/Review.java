package com.kh.tripply.review.domain;

import java.sql.Date;

public class Review {
	private int boardNo;
	private String reviewTitle;
	private String reviewContents;
	private String reviewWriter;
	private String rLocationCode;
	private String reviewFileName;
	private String reviewFileReName;
	private String reviewFilePath;
	private int reviewCount;
	private Date rCreateDate;
	private Date rUpdateDate;
	private String rStatus;
	private String thumbnailPath;

	public Review() {
	}

	public Review(int boardNo, String reviewTitle, String reviewContents, String reviewWriter, String rLocationCode,
			String reviewFileName, String reviewFileReName, String reviewFilePath, String thumbnailPath,
			int reviewCount, Date rCreateDate, Date rUpdateDate, String rStatus) {
		super();
		this.boardNo = boardNo;
		this.reviewTitle = reviewTitle;
		this.reviewContents = reviewContents;
		this.reviewWriter = reviewWriter;
		this.rLocationCode = rLocationCode;
		this.reviewFileName = reviewFileName;
		this.reviewFileReName = reviewFileReName;
		this.reviewFilePath = reviewFilePath;
		this.thumbnailPath = thumbnailPath;
		this.reviewCount = reviewCount;
		this.rCreateDate = rCreateDate;
		this.rUpdateDate = rUpdateDate;
		this.rStatus = rStatus;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContents() {
		return reviewContents;
	}

	public void setReviewContents(String reviewContents) {
		this.reviewContents = reviewContents;
	}

	public String getReviewWriter() {
		return reviewWriter;
	}

	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}

	public String getrLocationCode() {
		return rLocationCode;
	}

	public void setrLocationCode(String rLocationCode) {
		this.rLocationCode = rLocationCode;
	}

	public String getReviewFileName() {
		return reviewFileName;
	}

	public void setReviewFileName(String reviewFileName) {
		this.reviewFileName = reviewFileName;
	}

	public String getReviewFileReName() {
		return reviewFileReName;
	}

	public void setReviewFileReName(String reviewFileReName) {
		this.reviewFileReName = reviewFileReName;
	}

	public String getReviewFilePath() {
		return reviewFilePath;
	}

	public void setReviewFilePath(String reviewFilePath) {
		this.reviewFilePath = reviewFilePath;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public Date getrCreateDate() {
		return rCreateDate;
	}

	public void setrCreateDate(Date rCreateDate) {
		this.rCreateDate = rCreateDate;
	}

	public Date getrUpdateDate() {
		return rUpdateDate;
	}

	public void setrUpdateDate(Date rUpdateDate) {
		this.rUpdateDate = rUpdateDate;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	@Override
	public String toString() {
		return "Review [boardNo=" + boardNo + ", reviewTitle=" + reviewTitle + ", reviewContents=" + reviewContents
				+ ", reviewWriter=" + reviewWriter + ", rLocationCode=" + rLocationCode + ", reviewFileName="
				+ reviewFileName + ", reviewFileReName=" + reviewFileReName + ", reviewFilePath=" + reviewFilePath
				+ ", thumbnailPath=" + thumbnailPath + ", reviewCount=" + reviewCount + ", rCreateDate=" + rCreateDate
				+ ", rUpdateDate=" + rUpdateDate + ", rStatus=" + rStatus + "]";
	}

}
