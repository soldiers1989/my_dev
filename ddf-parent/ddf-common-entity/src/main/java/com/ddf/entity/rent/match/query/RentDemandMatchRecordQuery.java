package com.ddf.entity.rent.match.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.match.dto.RentDemandMatchRecord;

/**
 * rent_demand_match_record EntityQuery
 * @author robot
 * @version 2018-01-22
 */
public class RentDemandMatchRecordQuery extends Query {

	private static final long serialVersionUID = 1L;
	private String rentDemandId;
	private String phone;

	public String getRentDemandId() {
		return rentDemandId;
	}

	public void setRentDemandId(String rentDemandId) {
		this.rentDemandId = rentDemandId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public RentDemandMatchRecordQuery(){
		this.rentDemandMatchRecord = new RentDemandMatchRecord();
	}
	
	private RentDemandMatchRecord rentDemandMatchRecord;

	public RentDemandMatchRecord getRentDemandMatchRecord() {
		return rentDemandMatchRecord;
	}

	public void setRentDemandMatchRecord(RentDemandMatchRecord rentDemandMatchRecord) {
		this.rentDemandMatchRecord = rentDemandMatchRecord;
	}

}