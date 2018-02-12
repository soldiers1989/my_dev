package com.ddf.entity.rent.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.RentParam;

/**
 * rent_param EntityQuery
 * @author robot
 * @version 2018-01-16
 */
public class RentParamQuery extends Query {

	private static final long serialVersionUID = 1L;

	private RentParam rentParam;

	public RentParam getRentParam() {
		return rentParam;
	}

	public void setRentParam(RentParam rentParam) {
		this.rentParam = rentParam;
	}

}