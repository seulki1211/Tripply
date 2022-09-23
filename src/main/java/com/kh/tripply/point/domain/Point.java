package com.kh.tripply.point.domain;

import java.sql.Timestamp;

public class Point {

	private int pointWorkNo;
	private String pointWorkType;
	private String pointFromUser;
	private String pointToUser;
	private int pointAmount;
	private Timestamp pCreateDate;
	private String chargeMethod;
	private String bankChoice;
	private String bankAccNo;
	private String cardChoice;
	private String cardNo;
	private String loginUser;

	public Point() {
	}

	public Point(int pointWorkNo, String pointWorkType, String pointFromUser, String pointToUser, int pointAmount,
			Timestamp pCreateDate, String chargeMethod, String bankChoice, String bankAccNo, String cardChoice,
			String cardNo, String loginUser) {
		super();
		this.pointWorkNo = pointWorkNo;
		this.pointWorkType = pointWorkType;
		this.pointFromUser = pointFromUser;
		this.pointToUser = pointToUser;
		this.pointAmount = pointAmount;
		this.pCreateDate = pCreateDate;
		this.chargeMethod = chargeMethod;
		this.bankChoice = bankChoice;
		this.bankAccNo = bankAccNo;
		this.cardChoice = cardChoice;
		this.cardNo = cardNo;
		this.loginUser = loginUser;
	}

	public int getPointWorkNo() {
		return pointWorkNo;
	}

	public void setPointWorkNo(int pointWorkNo) {
		this.pointWorkNo = pointWorkNo;
	}

	public String getPointWorkType() {
		return pointWorkType;
	}

	public void setPointWorkType(String pointWorkType) {
		this.pointWorkType = pointWorkType;
	}

	public String getPointFromUser() {
		return pointFromUser;
	}

	public void setPointFromUser(String pointFromUser) {
		this.pointFromUser = pointFromUser;
	}

	public String getPointToUser() {
		return pointToUser;
	}

	public void setPointToUser(String pointToUser) {
		this.pointToUser = pointToUser;
	}

	public int getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(int pointAmount) {
		this.pointAmount = pointAmount;
	}

	public Timestamp getpCreateDate() {
		return pCreateDate;
	}

	public void setpCreateDate(Timestamp pCreateDate) {
		this.pCreateDate = pCreateDate;
	}

	public String getChargeMethod() {
		return chargeMethod;
	}

	public void setChargeMethod(String chargeMethod) {
		this.chargeMethod = chargeMethod;
	}

	public String getBankChoice() {
		return bankChoice;
	}

	public void setBankChoice(String bankChoice) {
		this.bankChoice = bankChoice;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getCardChoice() {
		return cardChoice;
	}

	public void setCardChoice(String cardChoice) {
		this.cardChoice = cardChoice;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	@Override
	public String toString() {
		return "Point [pointWorkNo=" + pointWorkNo + ", pointWorkType=" + pointWorkType + ", pointFromUser="
				+ pointFromUser + ", pointToUser=" + pointToUser + ", pointAmount=" + pointAmount + ", pCreateDate="
				+ pCreateDate + ", chargeMethod=" + chargeMethod + ", bankChoice=" + bankChoice + ", bankAccNo="
				+ bankAccNo + ", cardChoice=" + cardChoice + ", cardNo=" + cardNo + ", loginUser=" + loginUser + "]";
	}
	
	

	
	
}
