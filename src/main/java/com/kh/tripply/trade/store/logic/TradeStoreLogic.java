package com.kh.tripply.trade.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
import com.kh.tripply.trade.domain.Trade;
import com.kh.tripply.trade.domain.TradeReply;
import com.kh.tripply.trade.store.TradeStore;

@Repository
public class TradeStoreLogic implements TradeStore{

	@Override
	public int insertTrade(SqlSessionTemplate session, Trade trade) {
		int result = session.insert("TradeMapper.insertTrade", trade);
		return result;
	}

	@Override
	public Trade selectOneTradeByNo(SqlSessionTemplate session, int boardNo) {
		Trade trade = session.selectOne("TradeMapper.selectOneTrade",boardNo);
		return trade;
	}
	
	@Override
	public List<Trade> selectAllTrade(SqlSessionTemplate session, Paging paging) {
		RowBounds rowBounds = new RowBounds(paging.getOffset(), paging.getPageLimit());
		List<Trade> tList = session.selectList("TradeMapper.selectAllTrade",null,rowBounds);
		return tList;
	}


	@Override
	public int getTotalCount(SqlSessionTemplate session) {
		int getTotalTrade = session.selectOne("TradeMapper.selectTotalCount");
		return getTotalTrade;
	}

	@Override
	public List<Trade> selectSearchTrade(SqlSessionTemplate session, Search search, Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSearchCount(SqlSessionTemplate session, Search search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeTradeByNo(SqlSessionTemplate session, Trade trade) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int registerTradeReply(TradeReply rReply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TradeReply> printTradeReplyByNo(SqlSessionTemplate session,int boardNo) {
		List<TradeReply> tReplyList = session.selectList("TradeReplyMapper.selectTradeReply", boardNo);
		return tReplyList;
	}

	@Override
	public int tradeViewCount(SqlSessionTemplate session, int boardNo) {
		int result = session.update("TradeMapper.updateTradeViewCount", boardNo);
		return result;
	}

	@Override
	public int updateTradeByNo(SqlSessionTemplate session, Trade trade) {
		int result = session.update("TradeMapper.updateTrade", trade);
		return result;
	}

}
