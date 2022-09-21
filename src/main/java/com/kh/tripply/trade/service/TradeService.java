package com.kh.tripply.trade.service;

import java.util.List;
import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
import com.kh.tripply.trade.domain.Trade;
import com.kh.tripply.trade.domain.TradeReply;


public interface TradeService {
	
	public int registerTrade(Trade trade);
	public List<Trade> printAllTrade(Paging paging);
	public Trade printOneTradeByNo(int boardNo);
	public int getTotalCount();
	public List<Trade> printSearchTrade(Search search,Paging paging);
	public int getSearchCount(Search search);
	public int modifyTradeByNo(Trade trade);
	public int removeTradeByNo(Trade trade);
	
	////////////////////댓글
	public int registerReviewReply(TradeReply rReply);
	public List<TradeReply> printTradeReplyByNo(int boardNo);
	
	////////////////////조회수 카운트
	public int tradeViewCount(int boardNo);
	
	
}
