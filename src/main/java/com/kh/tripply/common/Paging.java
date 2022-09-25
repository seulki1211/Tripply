package com.kh.tripply.common;

import org.springframework.stereotype.Component;

public class Paging {
	
	//생성자에 매개변수를 넣어주면
	//필요한 값들을 getter로 얻을 수 있다.
	//1.게시물 개수, 2.현재페이지, 3.페이지당 게시물개수, 4.페이징네비사이즈
	
	private int totalCount;
	private int page;
	private int pageLimit;
	private int startPage;
	private int endPage;
	private int naviSize;
	private int startNavi;
	private int endNavi;
	private int offset;
	
	public Paging(int totalCount, int page, int pageLimit, int naviSize) {
		super();
		this.totalCount = totalCount;
		this.page = page;
		this.pageLimit = pageLimit;
		this.naviSize = naviSize;

		startPage = 1;
		endPage = (int) ((double) totalCount / pageLimit + 0.9);
		startNavi = ((page - 1) / naviSize) * naviSize + 1;
		endNavi = startNavi + naviSize - 1;
		if (endNavi > endPage) {
			endNavi = endPage;
		}
		if (page < 1) {
			page = 1;
		}
		if (page > endPage) {
			page = endPage;
		}
		offset = (page - 1) * pageLimit;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getpage() {
		return page;
	}

	public void setpage(int page) {
		this.page = page;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getNaviSize() {
		return naviSize;
	}

	public void setNaviSize(int naviSize) {
		this.naviSize = naviSize;
	}

	public int getStartNavi() {
		return startNavi;
	}

	public void setStartNavi(int startNavi) {
		this.startNavi = startNavi;
	}

	public int getEndNavi() {
		return endNavi;
	}

	public void setEndNavi(int endNavi) {
		this.endNavi = endNavi;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "Paging [totalCount=" + totalCount + ", page=" + page + ", pageLimit=" + pageLimit
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", naviSize=" + naviSize + ", startNavi="
				+ startNavi + ", endNavi=" + endNavi + ", offset=" + offset + "]";
	}
	
	

}