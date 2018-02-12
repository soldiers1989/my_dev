package com.ddf.entity.rent.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.Apartment;

/**
 * apartment EntityQuery
 * @author robot
 * @version 2018-02-02
 */
public class ApartmentQuery extends Query {

	private static final long serialVersionUID = 1L;
	private Apartment apartment;
	private Date startCreateDate;	//创建开始时间 
	private Date endCreateDate;		//创建结束时间
	
	
	public ApartmentQuery(){
		this.apartment = new Apartment();
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
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