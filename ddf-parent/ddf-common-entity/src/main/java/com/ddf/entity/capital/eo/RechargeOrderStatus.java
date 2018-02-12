package com.ddf.entity.capital.eo;

public enum RechargeOrderStatus {
	SUCCESS("充值成功"),PROCESSING("处理中"),FAILED("充值失败");
	
	private String explain;
	
	private RechargeOrderStatus(String explain) {
		this.explain=explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
