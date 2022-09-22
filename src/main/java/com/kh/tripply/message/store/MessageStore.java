package com.kh.tripply.message.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.message.common.MessageSearch;
import com.kh.tripply.message.domain.Message;

public interface MessageStore {

	public int insertMessage(Message msg, SqlSession session);

	public List<Message> selectSendList(MessageSearch mSearch,int currentPage,int boardLimit, SqlSession session);

	public List<Message> selectRecvList(MessageSearch mSearch,int currentPage,int boardLimit, SqlSession session);

	public int chkMsgReciever(String msgReciever, SqlSession session);
	
	public Message selectOneNotice(int msgNo, SqlSession session);
	
	public int selectTotalSendCount(MessageSearch mSearch, SqlSession session);

	public int selectTotalRecvCount(MessageSearch mSearch, SqlSession session);

	public List<Message> selectSearchSendList(MessageSearch mSearch,int currentPage,int boardLimit, SqlSession session);

	public List<Message> selectSearchRecvList(MessageSearch mSearch,int currentPage,int boardLimit, SqlSession session);

}
