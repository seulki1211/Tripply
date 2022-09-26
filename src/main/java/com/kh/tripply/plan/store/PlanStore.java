package com.kh.tripply.plan.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.plan.domain.Plan;
import com.kh.tripply.plan.domain.PlanList;
import com.kh.tripply.plan.domain.Planner;
import com.kh.tripply.plan.domain.PlannerReply;


public interface PlanStore {
	
	public int insertPlanner(SqlSession session, Planner planner);

	public Planner selectInfo(SqlSession session,Integer boardNo);

	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue, String searchRegion);

	public List<Planner> selectPlanner(SqlSession session, int currentPage, int limit);

	public List<Planner> selectAllByValue(SqlSession session, String searchCondition, String searchValue, String searchRegion,int boardLimit,
			int currentPage);

	public int insertPlan(SqlSession session, PlanList l);

	public List<Plan> selectAllPlan(SqlSession session, Integer boardNo);

	public int deletePlanner(SqlSession session, int boardNo);

	public int deletePlan(SqlSession session, int boardNo);

	public int updatePlannerCount(SqlSession session, Integer boardNo);

	public int insertReply(SqlSession session, PlannerReply plannerReply);

	public List<PlannerReply> selectReply(SqlSession session, Integer boardNo);

	public int deleteReply(SqlSession session, Integer replyNo);

	public int updateReply(SqlSession session, PlannerReply plannerReply);

}
