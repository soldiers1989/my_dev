package com.ddf.entity.message.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.message.dto.MobileMessage;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * mobile_message EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class MobileMessageQuery extends Query {

	private static final long serialVersionUID = 1L;

	public MobileMessageQuery(){
		this.mobileMessage = new MobileMessage();
	}
	
	private MobileMessage mobileMessage;

	public MobileMessage getMobileMessage() {
		return mobileMessage;
	}

	public void setMobileMessage(MobileMessage mobileMessage) {
		this.mobileMessage = mobileMessage;
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