package com.ddf.entity.rent.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.ApartmentAppointment;

/**
 * apartment_appointment EntityQuery
 * @author robot
 * @version 2018-02-02
 */
public class ApartmentAppointmentQuery extends Query {

	private static final long serialVersionUID = 1L;
	private ApartmentAppointment apartmentAppointment;
//	private String pageNum;		// 分页
//	private String pageSize;	// 分页

	public ApartmentAppointmentQuery(){
		this.apartmentAppointment = new ApartmentAppointment();
	}
	

	public ApartmentAppointment getApartmentAppointment() {
		return apartmentAppointment;
	}

	public void setApartmentAppointment(ApartmentAppointment apartmentAppointment) {
		this.apartmentAppointment = apartmentAppointment;
	}


//	public String getPageNum() {
//		return pageNum;
//	}
//
//
//	public void setPageNum(String pageNum) {
//		this.pageNum = pageNum;
//	}
//
//
//	public String getPageSize() {
//		return pageSize;
//	}
//
//
//	public void setPageSize(String pageSize) {
//		this.pageSize = pageSize;
//	}

}