package com.ddf.entity.member.eo;

import java.util.HashMap;
import java.util.Map;

public enum AlipayZhimaCreditStatus {
	unaudit("未审核"), 
	audit_pass("审核通过"),
	audit_refuse("审核驳回");
	
	private String explain;
	
	private AlipayZhimaCreditStatus(String explain){
		this.explain=explain;
	}
	
	public String getExplain() {
		return explain;
	}
	
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	public static Map<String,String> allMap(){
		Map<String,String> map = new HashMap<String,String>();
		for (AlipayZhimaCreditStatus status : AlipayZhimaCreditStatus.values()){
			map.put(status.toString(), status.getExplain());
		}
		return map;
	}
}
