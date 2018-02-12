package com.ddf.entity.rent.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.MonthRentAmountCircle;

/**
 * month_rent_amount_circle EntityQuery
 * @author robot
 * @version 2018-01-18
 */
public class MonthRentAmountCircleQuery extends Query {

	private static final long serialVersionUID = 1L;

	public MonthRentAmountCircleQuery(){
		this.monthRentAmountCircle = new MonthRentAmountCircle();
	}
	
	private MonthRentAmountCircle monthRentAmountCircle;

	public MonthRentAmountCircle getMonthRentAmountCircle() {
		return monthRentAmountCircle;
	}

	public void setMonthRentAmountCircle(MonthRentAmountCircle monthRentAmountCircle) {
		this.monthRentAmountCircle = monthRentAmountCircle;
	}

}