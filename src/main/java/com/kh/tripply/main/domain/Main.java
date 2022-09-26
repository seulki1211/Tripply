package com.kh.tripply.main.domain;

public class Main {
	private int boardNo;
	private String title;
	private String writer;
	private String cDate;
	private String sort;
	private int count;
	
	public Main() {}
	
	
	

	public Main(int boardNo, String title, String writer, String cDate, String sort, int count) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.writer = writer;
		this.cDate = cDate;
		this.sort = sort;
		this.count = count;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Main [boardNo=" + boardNo + ", title=" + title + ", writer=" + writer + ", cDate=" + cDate + ", sort="
				+ sort + ", count=" + count + "]";
	}


	

}
