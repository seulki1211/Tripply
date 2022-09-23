package com.kh.tripply.point.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.common.Paging;
import com.kh.tripply.point.domain.Point;
import com.kh.tripply.point.service.PointService;
import com.kh.tripply.point.store.PointStore;

@Service
public class PointServiceImpl implements PointService {
	@Autowired
	PointStore pStore;
	@Autowired
	SqlSession session;
	@Override
	public int modifyChargePoint(Point point) {
		int result = pStore.updateChargePoint(session, point);
		return result;
	}
	@Override
	public int registerPointHistory(Point point) {
		int result = pStore.insertPointHistory(session, point);
		return result;
	}
	@Override
	public List<Point> printAllPointHistory(Paging paging,Point point) {
		List<Point> pList = pStore.selectAllPointHistory(session, paging, point);
		return pList;
	}
	@Override
	public int getHistoryTotalCount(Point point) {
		int result = pStore.getHistoryTotalCount(session,point);
		return result;
	}
	
}
