package com.ddf.entity.message.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.message.dto.LandlordComplain;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * landlord_complain EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class LandlordComplainQuery extends Query {

	private static final long serialVersionUID = 1L;

	public LandlordComplainQuery(){
		this.landlordComplain = new LandlordComplain();
	}
	
	private LandlordComplain landlordComplain;

	public LandlordComplain getLandlordComplain() {
		return landlordComplain;
	}

	public void setLandlordComplain(LandlordComplain landlordComplain) {
		this.landlordComplain = landlordComplain;
	}

	private Date startCreateDate;
	private Date endCreateDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartCreateDate() {
		return startCreateDate;
	}

	public void setStartCreateDate(Date startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

}