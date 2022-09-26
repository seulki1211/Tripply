package com.kh.tripply.plan.domain;

import java.sql.Date;

public class Planner {
	private int boardNo;
	private String planWriter;
	private String planTitle;
	private Date firstDay;
	private Date lastDay;
	private int plannerCount;
	private Date pCreateDate;
	private String pStatus;
	private String plannerFileName;
	private String plannerFileRename;
	private String plannerFilePath;
	private String plannerLocation;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getPlanWriter() {
		return planWriter;
	}
	public void setPlanWriter(String planWriter) {
		this.planWriter = planWriter;
	}
	public String getPlanTitle() {
		return planTitle;
	}
	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}
	public Date getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(Date firstDay) {
		this.firstDay = firstDay;
	}
	public Date getLastDay() {
		return lastDay;
	}
	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}
	public int getPlannerCount() {
		return plannerCount;
	}
	public void setPlannerCount(int plannerCount) {
		this.plannerCount = plannerCount;
	}
	public Date getpCreateDate() {
		return pCreateDate;
	}
	public void setpCreateDate(Date pCreateDate) {
		this.pCreateDate = pCreateDate;
	}
	public String getpStatus() {
		return pStatus;
	}
	public void setpStatus(String pStatus) {
		this.pStatus = pStatus;
	}
	public String getPlannerFileName() {
		return plannerFileName;
	}
	public void setPlannerFileName(String plannerFileName) {
		this.plannerFileName = plannerFileName;
	}
	public String getPlannerFileRename() {
		return plannerFileRename;
	}
	public void setPlannerFileRename(String plannerFileRename) {
		this.plannerFileRename = plannerFileRename;
	}
	public String getPlannerFilePath() {
		return plannerFilePath;
	}
	public void setPlannerFilePath(String plannerFilePath) {
		this.plannerFilePath = plannerFilePath;
	}
	public String getPlannerLocation() {
		return plannerLocation;
	}
	public void setPlannerLocation(String plannerLocation) {
		this.plannerLocation = plannerLocation;
	}
	@Override
	public String toString() {
		return "Planner [boardNo=" + boardNo + ", planWriter=" + planWriter + ", planTitle=" + planTitle + ", firstDay="
				+ firstDay + ", lastDay=" + lastDay + ", plannerCount=" + plannerCount + ", pCreateDate=" + pCreateDate
				+ ", pStatus=" + pStatus + ", plannerFileName=" + plannerFileName + ", plannerFileRename="
				+ plannerFileRename + ", plannerFilePath=" + plannerFilePath + ", plannerLocation=" + plannerLocation
				+ "]";
	}
	
	
	
	
}