package com.kh.tripply.plan.service.Logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.plan.domain.Planner;
import com.kh.tripply.plan.service.PlanService;
import com.kh.tripply.plan.store.PlanStore;
@Service
public class PlanServiceImpl implements PlanService{
	@Autowired
	private SqlSession session;
	@Autowired
	private PlanStore pStore;
	@Override
	public int registPlanner(Planner planner) {
		int result = pStore.insertPlanner(session,planner);
		return result;
	}
	
	//제목 여행날짜 select
	@Override
	public Planner printInfo(Integer boardNo) {
		Planner planner = pStore.selectInfo(session,boardNo);
		return planner;
	}

	@Override
	public int getTotalCount(String searchCondition, String searchValue) {
		int totalCount = pStore.selectTotalCount(session,searchCondition,searchValue);
		return totalCount;
	}

	@Override
	public List<Planner> printAllPlan(int currentPage, int limit) {
		List<Planner>pList = pStore.selectPlanner(session,currentPage,limit);
		return pList;
	}

	@Override
	public List<Planner> printAllValue(String searchCondition, String searchValue, int currentPage, int boardLimit) {
		List<Planner>pList = pStore.selectAllByValue(session, searchCondition, searchValue,currentPage, boardLimit);
		return pList;
	}

}
