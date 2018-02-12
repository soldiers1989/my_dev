package com.ddf.entity.member.eo;

import java.util.HashMap;
import java.util.Map;

public enum RealNameType {

	personal("个人"),
	company("企业");

	private String explain;
	
	private RealNameType(String explain){
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
		for (RealNameType status : RealNameType.values()){
			map.put(status.toString(), status.getExplain());
		}
		return map;
	}
}

