package com.kh.tripply.plan.service.Logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.tripply.plan.domain.Plan;
import com.kh.tripply.plan.domain.PlanList;
import com.kh.tripply.plan.domain.Planner;
import com.kh.tripply.plan.domain.PlannerReply;
import com.kh.tripply.plan.service.PlanService;
import com.kh.tripply.plan.store.PlanStore;
@Service
public class PlanServiceImpl implements PlanService{
	@Autowired
	private SqlSession session;
	@Autowired
	private PlanStore pStore;
	
	//Planner등록
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
	//플래너 리스트 불러오기
	@Override
	public List<Planner> printAllPlanner(int currentPage, int limit) {
		List<Planner>pList = pStore.selectPlanner(session,currentPage,limit);
		return pList;
	}
	//플래너 리스트에서 검색
	@Override
	public List<Planner> printAllValue(String searchCondition, String searchValue, int currentPage, int boardLimit) {
		List<Planner>pList = pStore.selectAllByValue(session, searchCondition, searchValue,currentPage, boardLimit);
		return pList;
	}
   //플랜 등록(좌표,주소)
	@Override
	public int registPlanner(PlanList l) {
		int result = pStore.insertPlan(session, l);
		return result;
	}


	
	//디테일 플랜 불러오기
	@Override
	public List<Plan> printAllPlan(Integer boardNo) {
		List<Plan>planList=pStore.selectAllPlan(session,boardNo);
		int result = 0;
		if(planList != null) {
			pStore.updatePlannerCount(session,boardNo);
		}
		return planList;
	}

	//Planner지우기
	@Override
	public int deletePlanner(int boardNo) {
		int result = pStore.deletePlanner(session,boardNo);
		return result;
	}
	
	//Plan지우기
	@Override
	public int deletePlan(int boardNo) {
		int result2 = pStore.deletePlan(session,boardNo);
		return result2;
	}

	//댓글 등록
	@Override
	public int addReply(PlannerReply plannerReply) {
		int result = pStore.insertReply(session,plannerReply);
		return result;
	}

	//댓글 보여지기
	@Override
	public List<PlannerReply> printReply(Integer boardNo) {
		List<PlannerReply>rList=pStore.selectReply(session,boardNo);
		return rList;
	}

	//댓글 삭제
	@Override
	public int removeReply(Integer replyNo) {
		int result = pStore.deleteReply(session,replyNo);
		return result;
	}

	@Override
	public int modifyReply(PlannerReply plannerReply) {
		int result = pStore.updateReply(session,plannerReply);
		return result;
	}


	
}
