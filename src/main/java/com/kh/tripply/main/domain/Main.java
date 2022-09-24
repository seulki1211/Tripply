package com.kh.tripply.main.domain;

public class Main {
	private int boardNo;
	private String title;
	private String cDate;
	private String sort;
	
	public Main() {}

	public Main(int boardNo, String title, String cDate, String sort) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.cDate = cDate;
		this.sort = sort;
	}

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

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Main [boardNo=" + boardNo + ", title=" + title + ", cDate=" + cDate + ", sort=" + sort + "]";
	}
	
	
	

}
