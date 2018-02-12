package com.ddf.entity.rent.eo;

public enum ApartmentOwnType {
	/**
	 * 枚举类型，非转租yes，转租no
	 */
	yes("非转租"),
	no("转租");
	
	private String explain;
	
	private ApartmentOwnType(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
