package com.ddf.entity.rent.dto;

import java.util.Date;
import com.ddf.entity.rent.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * apartment_appointment Entity
 * @author robot
 * @version 2018-02-02
 */
@ApiModel(description = "ApartmentAppointment")
public class ApartmentAppointment extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "预约发起人id")
	private String lodgerId;
	@ApiModelProperty(value = "房东id（被预约人）")
	private String landlordId;
	@ApiModelProperty(value = "房间Id")
	private String apartmentId;
	@ApiModelProperty(value = "枚举类型，整租whole_rent，合租share_rent")
	private ApartmentAppointmentApartmentType apartmentType;
	@ApiModelProperty(value = "预约时间")
	private Date appointmentDate;
	@ApiModelProperty(value = "枚举类型，等待wait，取消cancel，拒绝reject")
	private ApartmentAppointmentStatus status;
	
	public ApartmentAppointment() {
		super();
	}

	public ApartmentAppointment(String id){
		super(id);
	}

	public String getLodgerId() {
		return lodgerId;
	}

	public void setLodgerId(String lodgerId) {
		this.lodgerId = lodgerId;
	}
	
	public String getLandlordId() {
		return landlordId;
	}

	public void setLandlordId(String landlordId) {
		this.landlordId = landlordId;
	}
	
	public String getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(String apartmentId) {
		this.apartmentId = apartmentId;
	}
	
	public ApartmentAppointmentApartmentType getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(ApartmentAppointmentApartmentType apartmentType) {
		this.apartmentType = apartmentType;
	}
	
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	public ApartmentAppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(ApartmentAppointmentStatus status) {
		this.status = status;
	}
	
}