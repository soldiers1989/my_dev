package com.ddf.entity.rent.eo;

public enum ApartmentDepositContractPayType {
	/**
	 * 付款方式
	 * 1月一付、2月一付、3月一付、4月1付、半年一付、一年一付、一次付清（日租仅此）
	 */
	one_pay("1月一付"),
	two_pay("2月一付"),
	three_pay("3月一付"),
	four_pay("4月一付"),
	six_pay("半年一付"),
	twelve_pay("一年一付"),
	all_pay("一次付清");
	
	private String explain;
	
	
	private ApartmentDepositContractPayType(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
