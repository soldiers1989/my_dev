package com.ddf.entity.message.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.message.dto.LandlordOpinion;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * landlord_opinion EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class LandlordOpinionQuery extends Query {

	private static final long serialVersionUID = 1L;

	public LandlordOpinionQuery(){
		this.landlordOpinion = new LandlordOpinion();
	}
	
	private LandlordOpinion landlordOpinion;

	public LandlordOpinion getLandlordOpinion() {
		return landlordOpinion;
	}

	public void setLandlordOpinion(LandlordOpinion landlordOpinion) {
		this.landlordOpinion = landlordOpinion;
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