package com.ddf.entity.rent.eo;

public enum ShareHouseOwnType {
	/**
	 * 枚举类型，非转租yes，转租no
	 */
	yes("非转租"),
	no("转租");
	
	private String explain;
	
	private ShareHouseOwnType(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
