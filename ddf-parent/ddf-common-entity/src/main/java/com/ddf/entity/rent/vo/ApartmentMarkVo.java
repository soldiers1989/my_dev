package com.ddf.entity.rent.vo;

import com.ddf.entity.member.dto.User;
import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.dto.ApartmentMark;
import com.ddf.entity.rent.dto.ShareApartment;

/**
 * apartment_mark EntityVo
 * @author robot
 * @version 2018-02-02
 */
public class ApartmentMarkVo extends ApartmentMark {
	
	private static final long serialVersionUID = 1L;
	
	private Apartment apartment;		// 整租房源
	private ShareApartment shareApartment;	// 合租房间
	private User user;		// 用户

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}