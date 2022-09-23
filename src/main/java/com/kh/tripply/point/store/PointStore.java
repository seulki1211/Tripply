package com.kh.tripply.point.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.common.Paging;
import com.kh.tripply.point.domain.Point;

public interface PointStore {
	public List<Point> selectAllPointHistory(SqlSession session,Paging paging, Point point);
	public int getHistoryTotalCount(SqlSession session,Point point);
	public int updateChargePoint(SqlSession session, Point point);
	public int insertPointHistory(SqlSession session,Point point);
}
