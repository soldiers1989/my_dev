package com.ddf.entity.message.eo;

public enum SysMessageReadStatus {
	UNREAD("未读"),
	READ("已读");
	private String explain;
	private SysMessageReadStatus(String explain){
		this.explain=explain;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
}
