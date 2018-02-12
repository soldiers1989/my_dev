package com.ddf.entity.member.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.member.dto.RealName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * real_name EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class RealNameQuery extends Query {

	private static final long serialVersionUID = 1L;

	public RealNameQuery(){
		this.realName = new RealName();
	}
	
	private RealName realName;

	public RealName getRealName() {
		return realName;
	}

	public void setRealName(RealName realName) {
		this.realName = realName;
	}

	private Date startCreateDate;
	private Date endCreateDate;
	private String mobile;

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
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}