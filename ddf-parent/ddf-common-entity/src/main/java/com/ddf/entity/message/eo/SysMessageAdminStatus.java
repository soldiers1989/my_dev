package com.ddf.entity.message.eo;

public enum SysMessageAdminStatus {

	unaudit("未审核"), 
	audit_pass("审核通过"),
	audit_refuse("审核驳回"),
	sent("已发送");
	
	private String explain;
	
	private SysMessageAdminStatus(String explain){
		this.explain=explain;
	}
	
	public String getExplain() {
		return explain;
	}
	
	public void setExplain(String explain) {
		this.explain = explain;
	}
}
