package com.kh.tripply.trade.service.logic;

import java.util.HashMap;
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
	public List<Trade> printSearchTrade(Search search, Paging paging) {
		List<Trade> tList = tStore.selectSearchTrade(session, search, paging);
		return tList;
	}

	@Override
	public int getSearchCount(Search search) {
		int result = tStore.getSearchCount(session, search);
		return result;
	}

	@Override
	public int removeTradeByNo(Trade trade) {
		int result = tStore.deleteTradeByNo(session, trade);
		return result;
	}

	@Override
	public int registerTradeReply(TradeReply tReply) {
		int result = tStore.insertTradeReply(session,tReply);
		return result;
	}

	@Override
	public List<TradeReply> printTradeReplyByNo(int boardNo) {
		List<TradeReply> tReplyList = tStore.printTradeReplyByNo(session,boardNo);
		return tReplyList;
	}

	@Override
	public int tradeViewCount(int boardNo) {
		int result = tStore.tradeViewCount(session, boardNo);
		return result;
	}

	@Override
	public int modifyTradeByNo(Trade trade) {
		int result = tStore.updateTradeByNo(session, trade);
		return result;
	}

	@Override
	public int	modifyBuyer(HashMap<String,String> paramMap) {
		int result = tStore.updateBuyer(session, paramMap);
		return result;
	}

}
