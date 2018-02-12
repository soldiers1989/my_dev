package com.ddf.entity.capital.eo;

public enum WithdrawOrderStatus {
	/**
	 * 提现成功
	 */
	SUCCESS("提现成功"),
	/**
	 * 提现失败
	 */
	FAILED("提现失败"),
	/**
	 * 取消
	 */
	CANCLE("取消提现"),
	/**
	 * 提现处理中
	 */
	PROCESSING("提现中");
	
	private String explain;

	private WithdrawOrderStatus(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
