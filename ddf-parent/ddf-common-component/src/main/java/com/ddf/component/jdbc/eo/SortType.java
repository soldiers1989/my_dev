package com.ddf.component.jdbc.eo;

public enum SortType {
	
	ASC("升序"),
	DESC("倒序");
	
	private String explain;
	
	
	private SortType(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}



}