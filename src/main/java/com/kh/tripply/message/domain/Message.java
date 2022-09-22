package com.kh.tripply.message.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Message {
	private int msgNo;
	private String msgTitle;
	private String msgContents;
	private String msgWriter;
	private String msgReciever;
	private Timestamp  msgCreateDate;
	public int getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgContents() {
		return msgContents;
	}
	public void setMsgContents(String msgContents) {
		this.msgContents = msgContents;
	}
	public String getMsgWriter() {
		return msgWriter;
	}
	public void setMsgWriter(String msgWriter) {
		this.msgWriter = msgWriter;
	}
	public String getMsgReciever() {
		return msgReciever;
	}
	public void setMsgReciever(String msgReciever) {
		this.msgReciever = msgReciever;
	}
	public Timestamp getMsgCreateDate() {
		return msgCreateDate;
	}
	public void setMsgCreateDate(Timestamp msgCreateDate) {
		this.msgCreateDate = msgCreateDate;
	}
	@Override
	public String toString() {
		return "Message [msgNo=" + msgNo + ", msgTitle=" + msgTitle + ", msgContents=" + msgContents + ", msgWriter="
				+ msgWriter + ", msgReciever=" + msgReciever + ", msgCreateDate=" + msgCreateDate + "]";
	}
	
	
	
}
