package com.ddf.entity.rent.eo;

public enum ApartmentDepositContractRentType {
	/**
	 * 枚举类型，租房类型（日租date_rent，月租month_rent）
	 */
	date_rent("日租"),
	month_rent("月租");
	
	private String explain;
	
	private ApartmentDepositContractRentType(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
