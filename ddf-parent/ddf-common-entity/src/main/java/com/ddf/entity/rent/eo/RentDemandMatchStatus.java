package com.ddf.entity.rent.eo;

public enum RentDemandMatchStatus {
	/**
	 * 匹配状态（open,close)
	 */
	open("正在订阅"),
	close("停止订阅");
	
	private String explain;
	
	
	private RentDemandMatchStatus(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
