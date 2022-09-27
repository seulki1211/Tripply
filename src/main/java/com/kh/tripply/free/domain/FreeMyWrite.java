package com.kh.tripply.free.domain;

import java.sql.Date;

public class FreeMyWrite {
	private int boardNo;
	private String title;
	private String writer;
	private int count;
	private Date createDate;
	private String category;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "FreeMyWrite [boardNo=" + boardNo + ", title=" + title + ", writer=" + writer + ", count=" + count
				+ ", createDate=" + createDate + ", category=" + category + "]";
	}
	
	
	
}
