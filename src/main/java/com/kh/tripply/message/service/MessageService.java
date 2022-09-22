package com.kh.tripply.message.service;

import java.util.List;

import com.kh.tripply.message.common.MessageSearch;
import com.kh.tripply.message.domain.Message;

public interface MessageService {

	
	public int sendMessage(Message msg);
	
	// 보낸 편지함
	public List<Message> printSendList(MessageSearch mSearch,int currentPage,int boardLimit);
	
	// 받은 편지함
	public List<Message> printRecvList(MessageSearch mSearch,int currentPage,int boardLimit);
	
	public int chkMsgReciever(String msgReciever);
	
	public Message printOneNotice(int msgNo);
	
	public int getTotalSendCount(MessageSearch mSearch);
	
	public int getTotalRecvCount(MessageSearch mSearch);
	
	// 보낸 편지 검색
	public List<Message> printSearchSendList(MessageSearch mSearch, int currentPage, int boardLimit);

	// 받은 편지 검색
	public List<Message> printSearchRecvList(MessageSearch mSearch, int currentPage, int boardLimit);

}
