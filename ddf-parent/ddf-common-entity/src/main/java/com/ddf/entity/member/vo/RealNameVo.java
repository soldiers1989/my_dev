package com.ddf.entity.member.vo;

import com.ddf.entity.member.dto.RealName;
import com.ddf.entity.member.dto.User;

/**
 * real_name EntityVo
 * @author robot
 * @version 2018-01-10
 */
public class RealNameVo extends RealName {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private String statusExplain;
	private String typeExplain;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setStatusExplain(String statusExplain) {
		this.statusExplain = statusExplain;
	}

	public void setTypeExplain(String typeExplain) {
		this.typeExplain = typeExplain;
	}

	public String getStatusExplain() {
		if(this.getStatus()!=null){
			return this.getStatus().getExplain();
		}
		return statusExplain;
	}

	public String getTypeExplain() {
		if(this.getType()!=null){
			return this.getType().getExplain();
		}
		return typeExplain;
	}
}