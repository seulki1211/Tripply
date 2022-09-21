package com.kh.tripply.trade.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
import com.kh.tripply.trade.domain.Trade;
import com.kh.tripply.trade.domain.TradeReply;
import com.kh.tripply.trade.service.TradeService;
import com.kh.tripply.trade.store.TradeStore;

@Service
public class TradeServiceImpl implements TradeService {
	@Autowired
	SqlSessionTemplate session;

	@Autowired
	TradeStore tStore;

	@Override
	public int registerTrade(Trade trade) {
		int result = tStore.insertTrade(session, trade);
		return result;
	}

	@Override
	public Trade printOneTradeByNo(int boardNo) {
		Trade trade = tStore.selectOneTradeByNo(session, boardNo);
		return trade;
	}

	@Override
	public List<Trade> printAllTrade(Paging paging) {
		List<Trade> tList = tStore.selectAllTrade(session, paging);
		return tList;
	}

	@Override
	public int getTotalCount() {
		int getTotalTrade = tStore.getTotalCount(session);
		return getTotalTrade;
	}

	@Override
	public List<Trade> printSearchTrade(Trade trade, Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSearchCount(Search search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeTradeByNo(Trade trade) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int registerReviewReply(TradeReply rReply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TradeReply> printTradeReplyByNo(int boardNo) {
		List<TradeReply> tReplyList = tStore.printTradeReplyByNo(session,boardNo);
		return tReplyList;
	}

	@Override
	public int tradeViewCount(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyTradeByNo(Trade trade) {
		// TODO Auto-generated method stub
		return 0;
	}

}
