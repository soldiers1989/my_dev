package com.ddf.entity.rent.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.MonthRentAmountCity;

/**
 * month_rent_amount_city EntityQuery
 * @author robot
 * @version 2018-01-18
 */
public class MonthRentAmountCityQuery extends Query {

	private static final long serialVersionUID = 1L;

	public MonthRentAmountCityQuery(){
		this.monthRentAmountCity = new MonthRentAmountCity();
	}
	
	private MonthRentAmountCity monthRentAmountCity;

	public MonthRentAmountCity getMonthRentAmountCity() {
		return monthRentAmountCity;
	}

	public void setMonthRentAmountCity(MonthRentAmountCity monthRentAmountCity) {
		this.monthRentAmountCity = monthRentAmountCity;
	}

}