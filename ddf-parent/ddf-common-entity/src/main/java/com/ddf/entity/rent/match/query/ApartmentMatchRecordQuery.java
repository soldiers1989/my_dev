package com.ddf.entity.rent.match.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.match.dto.ApartmentMatchRecord;

/**
 * apartment_match_record EntityQuery
 * @author robot
 * @version 2018-02-05
 */
public class ApartmentMatchRecordQuery extends Query {

	private static final long serialVersionUID = 1L;

	public ApartmentMatchRecordQuery(){
		this.apartmentMatchRecord = new ApartmentMatchRecord();
	}
	
	private ApartmentMatchRecord apartmentMatchRecord;

	public ApartmentMatchRecord getApartmentMatchRecord() {
		return apartmentMatchRecord;
	}

	public void setApartmentMatchRecord(ApartmentMatchRecord apartmentMatchRecord) {
		this.apartmentMatchRecord = apartmentMatchRecord;
	}

}