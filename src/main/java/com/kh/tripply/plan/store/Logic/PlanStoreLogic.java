package com.kh.tripply.plan.store.Logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.plan.domain.Planner;
import com.kh.tripply.plan.store.PlanStore;

@Repository
public class PlanStoreLogic implements PlanStore{
	@Override
	public int insertPlanner(SqlSession session, Planner planner) {
		int result = session.insert("PlannerMapper.insertPlanner",planner);
		return result;
	}

	@Override
	public Planner selectInfo(SqlSession session,Integer boardNo) {
		Planner planner=session.selectOne("PlannerMapper.selectInfo",boardNo);
		return planner;
	}

	/*@Override
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue) {
		HashMap<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("searchCondition",searchCondition);
		paramMap.put("searchValue",searchValue);
		int totalCount = session.selectOne("PlannerMapper.selectCount",paramMap);
		return totalCount;
	}

	@Override
	public List<Planner> selectPlanner(SqlSession session, int currentPage, int limit) {
		int offset=(currentPage-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<Planner>pList = session.selectList("PlannerMapper.selectAllPlanner",null,rowBounds);
		return pList;
	}*/

}
