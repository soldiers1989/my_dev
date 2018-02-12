package com.ddf.entity.message.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.message.dto.SysMessage;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * sys_message EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class SysMessageQuery extends Query {

	private static final long serialVersionUID = 1L;

	public SysMessageQuery(){
		this.sysMessage = new SysMessage();
	}
	
	private SysMessage sysMessage;

	public SysMessage getSysMessage() {
		return sysMessage;
	}

	public void setSysMessage(SysMessage sysMessage) {
		this.sysMessage = sysMessage;
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