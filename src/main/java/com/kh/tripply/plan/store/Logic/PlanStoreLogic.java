package com.kh.tripply.plan.store.Logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.tripply.plan.domain.PlanList;
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

	@Override
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
	}

	@Override
	public List<Planner> selectAllByValue(SqlSession session, String searchCondition, String searchValue,
			int currentPage, int boardLimit) {
		int offset = (currentPage-1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset,boardLimit);
		HashMap<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("searchCondition",searchCondition);
		paramMap.put("searchValue",searchValue);
		List<Planner>pList = session.selectList("PlannerMapper.selectByValue",paramMap,rowBounds);//마이바티스할때 두개값을 넘겨줄 수 있는가?
		//클래스 만들거나 HashMap사용하면 두개의 객체를 한번에 넘길 수 있다==>클래스로 한번 해보기
		return pList;
	}

	@Override
	public int insertPlan(SqlSession session, PlanList l) {
		int result=session.insert("PlannerMapper.insertPlan",l);
		return result;
	}
}
