package com.ddf.entity.member.eo;

import java.util.HashMap;
import java.util.Map;

public enum UserStatus {

	normal("正常"), 
	frozen("冻结");
	
	private String explain;
	
	private UserStatus(String explain){
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
		for (UserStatus status : UserStatus.values()){
			map.put(status.toString(), status.getExplain());
		}
		return map;
	}
}
