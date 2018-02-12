package com.ddf.entity.rent.eo;

public enum ShareApartmentBedroomType {
	/**
	 * 枚举类型，主卧first、次卧second、客卧guest
	 */
	first("主卧"),
	second("次卧"),
	guest("客卧");
	
	private String explain;
	
	private ShareApartmentBedroomType(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
