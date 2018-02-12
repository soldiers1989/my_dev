package com.ddf.entity.capital.eo;

public enum BillOrderType {
	recharge_bond("支付保证金"),withdraw_bond("退回证金"),deposite("支付定金"),recharge("充值"),withdraw("提现");
	
	private String explain;
	
	private BillOrderType(String explain) {
		this.explain=explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	
}
