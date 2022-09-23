package com.kh.tripply.point.service;

import java.util.List;

import com.kh.tripply.common.Paging;
import com.kh.tripply.point.domain.Point;

public interface PointService {
	public List<Point> printAllPointHistory(Paging paging,Point point);
	public int getHistoryTotalCount(Point point);
	public int modifyChargePoint(Point point);
	public int registerPointHistory(Point point);
}
