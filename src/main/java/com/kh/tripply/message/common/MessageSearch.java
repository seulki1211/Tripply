package com.kh.tripply.message.common;

public class MessageSearch {
	private String searchCondition;
	private String searchValue;
	private String msgWriter;
	private String msgReciever;
	
	public MessageSearch() {};
	
	public MessageSearch(String searchCondition, String searchValue, String msgWriter, String msgReciever) {
		super();
		this.searchCondition = searchCondition;
		this.searchValue = searchValue;
		this.msgWriter = msgWriter;
		this.msgReciever = msgReciever;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
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

	@Override
	public String toString() {
		return "MessageSearch [searchCondition=" + searchCondition + ", searchValue=" + searchValue + ", msgWriter="
				+ msgWriter + ", msgReciever=" + msgReciever + "]";
	}

	

	
	
	
	
}
