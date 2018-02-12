package com.ddf.entity.rent.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.MonthRentAmountXiaoqu;

/**
 * month_rent_amount_xiaoqu EntityQuery
 * @author robot
 * @version 2018-01-18
 */
public class MonthRentAmountXiaoquQuery extends Query {

	private static final long serialVersionUID = 1L;

	public MonthRentAmountXiaoquQuery(){
		this.monthRentAmountXiaoqu = new MonthRentAmountXiaoqu();
	}
	
	private MonthRentAmountXiaoqu monthRentAmountXiaoqu;

	public MonthRentAmountXiaoqu getMonthRentAmountXiaoqu() {
		return monthRentAmountXiaoqu;
	}

	public void setMonthRentAmountXiaoqu(MonthRentAmountXiaoqu monthRentAmountXiaoqu) {
		this.monthRentAmountXiaoqu = monthRentAmountXiaoqu;
	}

}