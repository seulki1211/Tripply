package com.kh.tripply.trade.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
import com.kh.tripply.trade.domain.Trade;


public interface TradeStore {
	public int insertTrade(SqlSessionTemplate session,Trade trade);
	public List<Trade> selectAllTrade(SqlSessionTemplate session,Paging paging);
	public Trade selectOneTradeByNo(SqlSessionTemplate session,int boardNo);
	public int getTotalCount(SqlSessionTemplate session);
	public List<Trade> selectSearchTrade(SqlSessionTemplate session,Trade trade,Paging paging);
	public int getSearchCount(SqlSessionTemplate session,Search search);
	public int removeTradeByNo(SqlSessionTemplate session,Trade trade);
	
}
