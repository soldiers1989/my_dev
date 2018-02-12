package com.ddf.entity.message.vo;

import com.ddf.entity.member.dto.User;
import com.ddf.entity.message.dto.LandlordOpinion;

/**
 * landlord_opinion EntityVo
 * @author robot
 * @version 2018-01-10
 */
public class LandlordOpinionVo extends LandlordOpinion {
	
	private static final long serialVersionUID = 1L;
	
	private User lodgerUser;//房客
	private User landlordUser;//房东
	
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