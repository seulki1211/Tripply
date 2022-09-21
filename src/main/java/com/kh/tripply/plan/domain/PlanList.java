package com.kh.tripply.plan.domain;

import java.util.List;

public class PlanList {
	
	private List<Plan> planList;

	public List<Plan> getPlanList() {
		return planList;
	}

	public void setPlanList(List<Plan> planList) {
		this.planList = planList;
	}

	@Override
	public String toString() {
		return "PlanList [planList=" + planList + "]";
	}
	

}
