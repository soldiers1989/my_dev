package com.ddf.entity.member.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.member.dto.User;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * user EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class UserQuery extends Query {

	private static final long serialVersionUID = 1L;

	public UserQuery(){
		this.user = new User();
	}
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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