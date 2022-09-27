package com.kh.tripply.plan.service;

import java.util.List;

import com.kh.tripply.plan.domain.Plan;
import com.kh.tripply.plan.domain.PlanList;
import com.kh.tripply.plan.domain.Planner;
import com.kh.tripply.plan.domain.PlannerReply;


public interface PlanService {

	public int registPlanner(Planner planner);

		public Planner printInfo(Integer boardNo);

		public int getTotalCount(String searchCondition, String searchRegion, String searchValue );

	public List<Planner> printAllPlanner(int offset, int limit);

	public List<Planner> printAllValue(String searchCondition,  String searchRegion, String searchValue, int currentPage, int boardLimit);

	public int registPlanner(PlanList l);

	public List<Plan> printAllPlan(Integer boardNo);

	public int deletePlanner(int boardNo);

	public int deletePlan(int boardNo);

	public int addReply(PlannerReply plannerReply);

	public List<PlannerReply> printReply(Integer boardNo);

	public int removeReply(Integer replyNo);

	public int modifyReply(PlannerReply plannerReply);

	public int organizePlan();

}
