package com.ddf.entity.rent.eo;

public enum CurrentUserIdType {
	/**
	 * 房东，房客
	 */
	landlord("房东"),
	lodger("房客");
	
	private String explain;
	
	private CurrentUserIdType(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
