package com.ddf.entity.rent.eo;

public enum ApartmentDepositContractStatus {
	/**
	 * 枚举类型，开启open，关闭close
	 */
	open("开启"),
	close("关闭");
	
	private String explain;
	
	private ApartmentDepositContractStatus(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
