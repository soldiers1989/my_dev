package com.ddf.entity.rent.vo;

import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.dto.ApartmentAppointment;
import com.ddf.entity.rent.dto.ShareApartment;

/**
 * apartment_appointment EntityVo
 * @author robot
 * @version 2018-02-02
 */
public class ApartmentAppointmentVo extends ApartmentAppointment {
	
	private static final long serialVersionUID = 1L;
	private Apartment apartment;		// 整租房源
	private ShareApartment shareApartment;	// 合租房间
	
	
	public Apartment getApartment() {
		return apartment;
	}
	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	public ShareApartment getShareApartment() {
		return shareApartment;
	}
	public void setShareApartment(ShareApartment shareApartment) {
		this.shareApartment = shareApartment;
	}
	
	
}