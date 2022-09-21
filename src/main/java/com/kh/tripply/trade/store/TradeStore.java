package com.kh.tripply.trade.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
import com.kh.tripply.trade.domain.Trade;


public interface TradeStore {
	public int insertTrade(SqlSession session,Trade trade);
	public List<Trade> selectAllTrade(SqlSession session,Paging paging);
	public Trade selectOneTradeByNo(SqlSession session,int boardNo);
	public int getTotalCount(SqlSession session);
	public List<Trade> selectSearchTrade(SqlSession session,Trade trade,Paging paging);
	public int getSearchCount(SqlSession session,Search search);
	public int removeTradeByNo(SqlSession session,Trade trade);
	
}
