package com.kh.tripply.message.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.message.common.MessageSearch;
import com.kh.tripply.message.domain.Message;
import com.kh.tripply.message.store.MessageStore;

@Repository
public class MessageStoreLogic implements MessageStore {

	@Override
	public int insertMessage(Message msg, SqlSession session) {
		int result = session.insert("MessageMapper.insertMsg", msg);
		return result;
	}

	@Override
	public List<Message> selectSendList(MessageSearch mSearch,int currentPage,int boardLimit, SqlSession session) {
		int offset = (currentPage -1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset, boardLimit);
		List<Message> sendList = session.selectList("MessageMapper.selectSendMsg", mSearch, rowBounds);
		return sendList;
	}
	
	@Override
	public List<Message> selectRecvList(MessageSearch mSearch,int currentPage,int boardLimit, SqlSession session) {
		int offset = (currentPage -1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset, boardLimit);
		List<Message> recvList = session.selectList("MessageMapper.selectRecvMsg", mSearch, rowBounds);
		return recvList;
	}

	@Override
	public int chkMsgReciever(String msgReciever, SqlSession session) {
		int result = session.selectOne("MessageMapper.chkMsgReciever", msgReciever);
		return result;
	}

	@Override
	public Message selectOneNotice(int msgNo, SqlSession session) {
		Message msg = session.selectOne("MessageMapper.selectOneMsg", msgNo);
		return msg;
	}

	@Override
	public int selectTotalSendCount(MessageSearch mSearch, SqlSession session) {
		int result = session.selectOne("MessageMapper.selectTotalSendCount", mSearch);
		return result;
	}
	
	@Override
	public int selectTotalRecvCount(MessageSearch mSearch, SqlSession session) {
		int result = session.selectOne("MessageMapper.selectTotalRecvCount", mSearch);
		return result;
	}

	@Override
	public List<Message> selectSearchSendList(MessageSearch mSearch,int currentPage,int boardLimit, SqlSession session) {
		int offset = (currentPage -1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset, boardLimit);
		List<Message> sendList = session.selectList("MessageMapper.selectSearchSendMsg", mSearch, rowBounds);
		return sendList;
	}
	
	@Override
	public List<Message> selectSearchRecvList(MessageSearch mSearch,int currentPage,int boardLimit, SqlSession session) {
		int offset = (currentPage -1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset, boardLimit);
		List<Message> recvList = session.selectList("MessageMapper.selectSearchRecvMsg", mSearch, rowBounds);
		return recvList;
	}
}
