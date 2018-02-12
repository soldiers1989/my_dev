package com.ddf.admin.system.domain;

public enum StudentType {
	admin("管理员"),common("会员");
	
	private String explain;
	
	private StudentType(String explain) {
		this.explain=explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	
}
