package com.ddf.entity.rent.eo;

public enum ApartmentAppointmentApartmentType {
	/**
	 * 枚举类型，整租whole_rent，合租share_rent
	 */
	whole_rent("整租"),
	share_rent("合租");
	
	private String explain;
	
	private ApartmentAppointmentApartmentType(String explain) {
		this.explain = explain;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
}
