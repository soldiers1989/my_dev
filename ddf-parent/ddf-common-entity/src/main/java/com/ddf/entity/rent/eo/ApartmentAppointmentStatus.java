package com.ddf.entity.rent.eo;

public enum ApartmentAppointmentStatus {
	/**
	 * 枚举类型，等待wait，取消cancel，拒绝reject
	 */
	wait("等待"),
	cancel("取消"),
	reject("拒绝");
	
	private String explain;
	
	private ApartmentAppointmentStatus(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
