package com.ddf.entity.message.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.message.dto.CallRecord;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * call_record EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class CallRecordQuery extends Query {

	private static final long serialVersionUID = 1L;

	public CallRecordQuery(){
		this.callRecord = new CallRecord();
	}
	
	private CallRecord callRecord;

	public CallRecord getCallRecord() {
		return callRecord;
	}

	public void setCallRecord(CallRecord callRecord) {
		this.callRecord = callRecord;
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