package com.ddf.entity.message.eo;

public enum SysMessageAdminSendType {
	specified("指定人"),
	all("全部");
	
	private String explain;
	
	private SysMessageAdminSendType(String explain){
		this.explain=explain;
	}
	
	public String getExplain() {
		return explain;
	}
	
	public void setExplain(String explain) {
		this.explain = explain;
	}
}
