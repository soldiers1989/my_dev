package com.ddf.entity.message.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.message.dto.HouseOpinion;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * house_opinion EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class HouseOpinionQuery extends Query {

	private static final long serialVersionUID = 1L;

	public HouseOpinionQuery(){
		this.houseOpinion = new HouseOpinion();
	}
	
	private HouseOpinion houseOpinion;

	public HouseOpinion getHouseOpinion() {
		return houseOpinion;
	}

	public void setHouseOpinion(HouseOpinion houseOpinion) {
		this.houseOpinion = houseOpinion;
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