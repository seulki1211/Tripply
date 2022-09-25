package com.kh.tripply.trade.store;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
import com.kh.tripply.member.domain.Member;
import com.kh.tripply.trade.domain.Trade;
import com.kh.tripply.trade.domain.TradeReply;


public interface TradeStore {
	public int insertTrade(SqlSessionTemplate session,Trade trade);
	public List<Trade> selectAllTrade(SqlSessionTemplate session,Paging paging);
	public Trade selectOneTradeByNo(SqlSessionTemplate session,int boardNo);
	public int getTotalCount(SqlSessionTemplate session);
	public List<Trade> selectSearchTrade(SqlSessionTemplate session,Search search,Paging paging);
	public int getSearchCount(SqlSessionTemplate session,Search search);
	public int updateTradeByNo(SqlSessionTemplate session,Trade trade);
	public int deleteTradeByNo(SqlSessionTemplate session,Trade trade);
	
	////////////////////댓글
	public int insertTradeReply(SqlSessionTemplate session, TradeReply tReply);
	public List<TradeReply> printTradeReplyByNo(SqlSessionTemplate session,int boardNo);
	public int updateTradeReply(SqlSessionTemplate session,TradeReply tReply);
	public int deleteTradeReply(SqlSessionTemplate session,TradeReply tReply);
	
	////////////////////조회수 카운트
	public int tradeViewCount(SqlSessionTemplate session, int boardNo);
	
		/////////////////////거래
	public int updateTradeChoice(SqlSessionTemplate session, Trade trade);
	public int updateFinalBiddingPrice(SqlSessionTemplate session,TradeReply tReply);
	public List<Trade> selectMyTrade(SqlSessionTemplate session,Member loginUser);
	public int updateTradeReplyChoiced(SqlSessionTemplate session,TradeReply tReply);
}
