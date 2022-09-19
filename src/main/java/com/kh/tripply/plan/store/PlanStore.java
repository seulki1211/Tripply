package com.kh.tripply.plan.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.tripply.plan.domain.Planner;


public interface PlanStore {
	
	public int insertPlanner(SqlSession session, Planner planner);

	public Planner selectInfo(SqlSession session,Integer boardNo);

	/*public int selectTotalCount(SqlSession session, String searchCondition, String searchValue);

	public List<Planner> selectPlanner(SqlSession session, int currentPage, int limit);
*/
}
