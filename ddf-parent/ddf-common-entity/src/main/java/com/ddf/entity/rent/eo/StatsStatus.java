package com.ddf.entity.rent.eo;

public enum StatsStatus {

	WAIT("待处理"),
	PROCESSING("处理中"),
	SUCCESS("成功"),
	FAIL("失败");

	private String explain;


	private StatsStatus(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
