package com.ddf.entity.rent.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.MonthRentAmountDistrict;

/**
 * month_rent_amount_district EntityQuery
 * @author robot
 * @version 2018-01-18
 */
public class MonthRentAmountDistrictQuery extends Query {

	private static final long serialVersionUID = 1L;

	public MonthRentAmountDistrictQuery(){
		this.monthRentAmountDistrict = new MonthRentAmountDistrict();
	}
	
	private MonthRentAmountDistrict monthRentAmountDistrict;

	public MonthRentAmountDistrict getMonthRentAmountDistrict() {
		return monthRentAmountDistrict;
	}

	public void setMonthRentAmountDistrict(MonthRentAmountDistrict monthRentAmountDistrict) {
		this.monthRentAmountDistrict = monthRentAmountDistrict;
	}

}