package com.ddf.entity.db.eo;

public enum DataSourceName {
	
	ddf("点点房"),
	account("积分");
	
	private String explain;
	
	
	private DataSourceName(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}



}