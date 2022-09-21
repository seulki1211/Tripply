package com.kh.tripply.common;

public class Search {
	//검색에 필요한 검색조건, 검색지역, 검색값을 다루기 위한 클래스입니다.
	
	private String searchCondition;
	private String searchRegion;
	private String searchValue;
	
	public Search() {}
	
	public Search(String searchCondition, String searchRegion, String searchValue) {
		super();
		this.searchCondition = searchCondition;
		this.searchRegion = searchRegion;
		this.searchValue = searchValue;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchRegion() {
		return searchRegion;
	}
	public void setSearchRegion(String searchRegion) {
		this.searchRegion = searchRegion;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString() {
		return "Search [searchCondition=" + searchCondition + ", searchRegion=" + searchRegion + ", searchValue="
				+ searchValue + "]";
	}
	
}
