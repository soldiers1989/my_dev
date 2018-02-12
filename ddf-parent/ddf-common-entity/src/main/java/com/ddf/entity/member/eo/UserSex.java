package com.ddf.entity.member.eo;

import java.util.HashMap;
import java.util.Map;

public enum UserSex {
	men("男"),
	women("女");
	private String explain;
	
	private UserSex(String explain){
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
		for (UserSex status : UserSex.values()){
			map.put(status.toString(), status.getExplain());
		}
		return map;
	}
}
