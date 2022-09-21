package com.kh.tripply.plan.service;

import java.util.List;

import com.kh.tripply.plan.domain.PlanList;
import com.kh.tripply.plan.domain.Planner;


public interface PlanService {

	public int registPlanner(Planner planner);

		public Planner printInfo(Integer boardNo);

		public int getTotalCount(String searchCondition, String searchValue);

	public List<Planner> printAllPlan(int offset, int limit);

	public List<Planner> printAllValue(String searchCondition, String searchValue, int currentPage, int boardLimit);

	public int registPlanner(PlanList l);
}
