package com.ddf.entity.rent.eo;

public enum ShareApartmentChaoxiang {
	/**
	 * 枚举类型， east（东） south（南）,west（西）north（北）
	 */
	east("朝东"),
	south("朝南"),
	west("朝西"),
	north("朝北");
	
	private String explain;
	
	private ShareApartmentChaoxiang(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
