package com.kh.tripply.plan.service;

import java.util.List;

import com.kh.tripply.plan.domain.Planner;


public interface PlanService {

	public int registPlanner(Planner planner);

		public Planner printInfo(Integer boardNo);

		/*public int getTotalCount(String searchCondition, String searchValue);

	public List<Planner> printAllPlan(int offset, int limit);*/
}
