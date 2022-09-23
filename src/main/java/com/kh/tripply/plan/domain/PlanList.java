package com.kh.tripply.plan.domain;

import java.util.ArrayList;
import java.util.List;

public class PlanList {
	
	private ArrayList<Plan> planList;

	public ArrayList<Plan> getPlanList() {
		return planList;
	}

	public void setPlanList(ArrayList<Plan> planList) {
		this.planList = planList;
	}

	@Override
	public String toString() {
		return "PlanList [planList=" + planList + "]";
	}
	

}
