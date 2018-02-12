package com.ddf.entity.rent.eo;

public enum ApartmentStatus {
	/**
	 * 枚举类型，待审核wait_review，已审核review_pass，已驳回review_reject
	 */
	wait_review("待审核"),
	review_pass("已审核"),
	review_reject("已驳回");
	
	private String explain;
	
	private ApartmentStatus(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
