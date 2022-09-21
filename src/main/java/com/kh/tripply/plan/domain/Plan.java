package com.kh.tripply.plan.domain;

import java.io.Serializable;
import java.sql.Date;

public class Plan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int boardNo;
	private int planNo;
	private String day;
	private Float Y;
	private Float X;
	private String address;
	private String Memo;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Float getY() {
		return Y;
	}
	public void setY(Float y) {
		Y = y;
	}
	public Float getX() {
		return X;
	}
	public void setX(Float x) {
		X = x;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Plan [boardNo=" + boardNo + ", planNo=" + planNo + ", day=" + day + ", Y=" + Y + ", X=" + X
				+ ", address=" + address + ", Memo=" + Memo + "]";
	}
	
	

	
	
}
