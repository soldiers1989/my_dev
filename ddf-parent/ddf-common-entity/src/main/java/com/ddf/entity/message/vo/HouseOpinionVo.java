package com.ddf.entity.message.vo;

import com.ddf.entity.member.dto.User;
import com.ddf.entity.message.dto.HouseOpinion;
import com.ddf.entity.rent.dto.Apartment;

/**
 * house_opinion EntityVo
 * @author robot
 * @version 2018-01-10
 */
public class HouseOpinionVo extends HouseOpinion {
	
	private static final long serialVersionUID = 1L;
	
	private Apartment apartment;//房源
	private User lodgerUser;//房客
	private User landlordUser;//房东
	
	public Apartment getApartment() {
		return apartment;
	}
	
	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	
	public User getLodgerUser() {
		return lodgerUser;
	}
	
	public void setLodgerUser(User lodgerUser) {
		this.lodgerUser = lodgerUser;
	}
	
	public User getLandlordUser() {
		return landlordUser;
	}
	
	public void setLandlordUser(User landlordUser) {
		this.landlordUser = landlordUser;
	}
	
}