package com.ddf.entity.rent.eo;

public enum ApartmentZhuangxiu {
	/**
	 * 枚举类型，nothing（毛坯），simple（简装），fine（精装））
	 */
	nothing("毛坯"),
	simple("简装"),
	fine("精装");
	
	private String explain;
	
	private ApartmentZhuangxiu(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
