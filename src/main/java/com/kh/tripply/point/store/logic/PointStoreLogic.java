package com.kh.tripply.point.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.common.Paging;
import com.kh.tripply.point.domain.Point;
import com.kh.tripply.point.store.PointStore;

@Repository
public class PointStoreLogic implements PointStore {

	@Override
	public int updateChargePoint(SqlSession session, Point point) {
		int result = session.update("PointMapper.updateChargePoint", point);
		return result;
	}

	@Override
	public int insertPointHistory(SqlSession session, Point point) {
		int result = session.update("PointMapper.insertPointHistory", point);
		return result;
	}

	@Override
	public List<Point> selectAllPointHistory(SqlSession session, Paging paging, Point point) {
		List<Point> pList = session.selectList("PointMapper.selectAllPointHistory", point,new RowBounds(paging.getOffset(), paging.getPageLimit()));
		return pList;
	}

	@Override
	public int getHistoryTotalCount(SqlSession session,Point point) {
		int result = session.selectOne("PointMapper.selectCountAllPointHistory",point);
		return result;
	}

}
