package com.ddf.entity.rent.eo;

public enum ApartmentMatchStatus {
	/**
	 * 枚举类型，招租状态（open,close)
	 */
	open("开始匹配"),
	close("关闭匹配");
	
	private String explain;
	
	private ApartmentMatchStatus(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
