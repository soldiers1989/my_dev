package com.ddf.entity.rent.eo;

public enum ShareApartmentDepositStatus {
	/**
	 * 枚举类型，定金状态(open,close)
	 */
	open("已交定金"),
	close("未交定金");
	
	private String explain;
	
	private ShareApartmentDepositStatus(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
