package com.ddf.entity.rent.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.MonthRentAmountTask;

/**
 * month_rent_amount_task EntityQuery
 * @author robot
 * @version 2018-01-25
 */
public class MonthRentAmountTaskQuery extends Query {

	private static final long serialVersionUID = 1L;

	public MonthRentAmountTaskQuery(){
		this.monthRentAmountTask = new MonthRentAmountTask();
	}
	
	private MonthRentAmountTask monthRentAmountTask;

	public MonthRentAmountTask getMonthRentAmountTask() {
		return monthRentAmountTask;
	}

	public void setMonthRentAmountTask(MonthRentAmountTask monthRentAmountTask) {
		this.monthRentAmountTask = monthRentAmountTask;
	}

}