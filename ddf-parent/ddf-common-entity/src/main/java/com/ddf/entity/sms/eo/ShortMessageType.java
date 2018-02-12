package com.ddf.entity.sms.eo;

public enum ShortMessageType {
	
	send_code("短信验证码"),
	send_content("自定义短信");
	
	private String explain;
	
	private ShortMessageType(String explain){
		this.explain=explain;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
}
