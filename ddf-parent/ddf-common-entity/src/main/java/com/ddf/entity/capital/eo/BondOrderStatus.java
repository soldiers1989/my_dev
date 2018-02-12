package com.ddf.entity.capital.eo;

public enum BondOrderStatus {
	
	proved("已认证"),proving("认证中"),refund("已退款"),breach("已违约") ;
	
	private String explain;
	
	private BondOrderStatus(String explain) {
		this.explain=explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
