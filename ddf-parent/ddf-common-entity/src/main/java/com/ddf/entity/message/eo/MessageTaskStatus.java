package com.ddf.entity.message.eo;

public enum MessageTaskStatus {
	WAIT("待处理"),
    PROCESSING("处理中"),
    SUCCESS("成功"),
    FAIL("失败");
	
	private String explain;
	
	private MessageTaskStatus(String explain){
		this.explain=explain;
	}
	
	public String getExplain() {
		return explain;
	}
	
	public void setExplain(String explain) {
		this.explain = explain;
	}
}
