package com.ddf.entity.message.eo;

public enum MessageTaskBizType {
	sys_message_admin("推送消息");
	
	private String explain;
	
	private MessageTaskBizType(String explain){
		this.explain=explain;
	}
	
	public String getExplain() {
		return explain;
	}
	
	public void setExplain(String explain) {
		this.explain = explain;
	}
}
