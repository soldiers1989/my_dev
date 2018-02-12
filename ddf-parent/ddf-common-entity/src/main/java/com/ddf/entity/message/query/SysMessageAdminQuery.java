package com.ddf.entity.message.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.message.dto.SysMessageAdmin;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * sys_message_admin EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class SysMessageAdminQuery extends Query {

	private static final long serialVersionUID = 1L;

	public SysMessageAdminQuery(){
		this.sysMessageAdmin = new SysMessageAdmin();
	}
	
	private SysMessageAdmin sysMessageAdmin;

	public SysMessageAdmin getSysMessageAdmin() {
		return sysMessageAdmin;
	}

	public void setSysMessageAdmin(SysMessageAdmin sysMessageAdmin) {
		this.sysMessageAdmin = sysMessageAdmin;
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