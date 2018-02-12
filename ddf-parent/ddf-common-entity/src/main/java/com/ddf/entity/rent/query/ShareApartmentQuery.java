package com.ddf.entity.rent.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.ShareApartment;

/**
 * share_apartment EntityQuery
 * @author robot
 * @version 2018-02-02
 */
public class ShareApartmentQuery extends Query {

	private static final long serialVersionUID = 1L;
	private ShareApartment shareApartment;
	private Date startCreateDate;	//创建开始时间 
	private Date endCreateDate;		//创建结束时间

	public ShareApartmentQuery(){
		this.shareApartment = new ShareApartment();
	}
	

	public ShareApartment getShareApartment() {
		return shareApartment;
	}

	public void setShareApartment(ShareApartment shareApartment) {
		this.shareApartment = shareApartment;
	}


	public Date getStartCreateDate() {
		return startCreateDate;
	}


	public void setStartCreateDate(Date startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}


	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

}