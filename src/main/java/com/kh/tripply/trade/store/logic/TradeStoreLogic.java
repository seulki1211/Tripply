package com.kh.tripply.trade.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.common.Paging;
import com.kh.tripply.common.Search;
import com.kh.tripply.trade.domain.Trade;
import com.kh.tripply.trade.store.TradeStore;

@Repository
public class TradeStoreLogic implements TradeStore{

	@Override
	public int insertTrade(SqlSession session, Trade trade) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Trade> selectAllTrade(SqlSession session, Paging paging) {
		RowBounds rowBounds = new RowBounds(paging.getOffset(), paging.getPageLimit());
		List<Trade> tList = session.selectList("TradeMapper.selectAllTrade",null,rowBounds);
		return tList;
	}

	@Override
	public Trade selectOneTradeByNo(SqlSession session, int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount(SqlSession session) {
		int getTotalTrade = session.selectOne("TradeMapper.selectTotalCount");
		System.out.println("왜 값을 못가져오냐.");
		return getTotalTrade;
	}

	@Override
	public List<Trade> selectSearchTrade(SqlSession session, Trade trade, Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSearchCount(SqlSession session, Search search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeTradeByNo(SqlSession session, Trade trade) {
		// TODO Auto-generated method stub
		return 0;
	}

}
