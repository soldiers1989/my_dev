package com.ddf.entity.message.eo;

public enum SysMessageAdminType {
	admin("后台");
	
	private String explain;
	
	private SysMessageAdminType(String explain){
		this.explain=explain;
	}
	
	public String getExplain() {
		return explain;
	}
	
	public void setExplain(String explain) {
		this.explain = explain;
	}
}
