package com.kh.tripply.trade.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
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
	public int removeTradeByNo(SqlSessionTemplate session,Trade trade);
	
	////////////////////댓글
	public int registerTradeReply(TradeReply rReply);
	public List<TradeReply> printTradeReplyByNo(SqlSessionTemplate session,int boardNo);
	
	////////////////////조회수 카운트
	public int tradeViewCount(SqlSessionTemplate session, int boardNo);
}
