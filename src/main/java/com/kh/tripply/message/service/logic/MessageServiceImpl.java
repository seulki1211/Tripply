package com.kh.tripply.message.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.message.common.MessageSearch;
import com.kh.tripply.message.domain.Message;
import com.kh.tripply.message.service.MessageService;
import com.kh.tripply.message.store.MessageStore;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageStore mStore;
	@Autowired
	private SqlSessionTemplate session;
	
	// 쪽지보내기
	@Override
	public int sendMessage(Message msg) {
		int result = mStore.insertMessage(msg, session);
		return result;
	}
	//보낸 쪽지함
	@Override
	public List<Message> printSendList(MessageSearch mSearch,int currentPage,int boardLimit) {
		List<Message> sendList = mStore.selectSendList(mSearch,currentPage,boardLimit, session);
		return sendList;
	}
	// 받은 쪽지함
	@Override
	public List<Message> printRecvList(MessageSearch mSearch,int currentPage,int boardLimit) {
		List<Message> recvList = mStore.selectRecvList(mSearch, currentPage,boardLimit,session);
		return recvList;
	}
	
	// 수신자 nickname 체크
	@Override
	public int chkMsgReciever(String msgReciever) {
		int result = mStore.chkMsgReciever(msgReciever, session);
		return result;
	}
	
	// 디테일뷰
	@Override
	public Message printOneNotice(int msgNo) {
		Message msg = mStore.selectOneNotice(msgNo, session);
		return msg;
	}
	
	// 보낸쪽지카운트
	@Override
	public int getTotalSendCount(MessageSearch mSearch) {
		int result = mStore.selectTotalSendCount(mSearch, session);
		return result;
	}
	
	// 받은쪽지 카운트
	@Override
	public int getTotalRecvCount(MessageSearch mSearch) {
		int result = mStore.selectTotalRecvCount(mSearch, session);
		return result;
	}
	
	//보낸쪽지검색
	@Override
	public List<Message> printSearchSendList(MessageSearch mSearch,int currentPage,int boardLimit) {
		List<Message> sendList = mStore.selectSearchSendList(mSearch,currentPage,boardLimit, session);
		return sendList;
	}
	// 받은쪽지 검색
	@Override
	public List<Message> printSearchRecvList(MessageSearch mSearch,int currentPage,int boardLimit) {
		List<Message> recvList = mStore.selectSearchRecvList(mSearch, currentPage,boardLimit,session);
		return recvList;
	}
}
