package com.kh.tripply.message.service;

import java.util.List;

import com.kh.tripply.message.common.MessageSearch;
import com.kh.tripply.message.domain.Message;

public interface MessageService {

	// 쪽지 보내기
	public int sendMessage(Message msg);
	
	// 받는이 체크
	public int chkMsgReciever(String msgReciever);
	
	// 메세지 1개 불러오기
	public Message printOneNotice(int msgNo);
	
	// 보낸 쪽지 카운트
	public int getTotalSendCount(MessageSearch mSearch);

	// 보낸 쪽지함
	public List<Message> printSendList(MessageSearch mSearch,int currentPage,int boardLimit);

	// 보낸 쪽지 검색
	public List<Message> printSearchSendList(MessageSearch mSearch, int currentPage, int boardLimit);

	//받는 쪽지 카운트
	public int getTotalRecvCount(MessageSearch mSearch);

	// 받은 쪽지함
	public List<Message> printRecvList(MessageSearch mSearch,int currentPage,int boardLimit);

	// 받은 쪽지 검색
	public List<Message> printSearchRecvList(MessageSearch mSearch, int currentPage, int boardLimit);

}
