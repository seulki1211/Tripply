package com.kh.tripply.trade.service;

import java.util.HashMap;
import java.util.List;

import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
import com.kh.tripply.member.domain.Member;
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
	public int registerTradeReply(TradeReply tReply);
	public List<TradeReply> printTradeReplyByNo(int boardNo);
	public int modifyTradeReply(TradeReply tReply);
	public int removeTradeReply(TradeReply tReply);

	
	////////////////////조회수 카운트
	public int tradeViewCount(int boardNo);
	
	/////////////////////거래
	public int modifyTradeChoice(Trade trade);
	public int modifyFinalBiddingPrice(TradeReply tReply);
	public List<Trade> printMyTrade(Member loginUser);
	public int modifyTradeReplyChoiced(TradeReply tReply);
	
}
