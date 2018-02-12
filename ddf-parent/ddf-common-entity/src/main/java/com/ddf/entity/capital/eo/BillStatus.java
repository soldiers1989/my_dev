package com.ddf.entity.capital.eo;

public enum BillStatus {
	SUCCESS("交易成功"),PROCESSING("处理中"),FAILED("交易失败");
	
	private String explain;
	
	private BillStatus(String explain) {
		this.explain=explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
