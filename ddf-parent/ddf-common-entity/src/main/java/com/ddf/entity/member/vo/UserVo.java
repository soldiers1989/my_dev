package com.ddf.entity.member.vo;

import com.ddf.entity.member.dto.User;

/**
 * user EntityVo
 * @author robot
 * @version 2018-01-08
 */
public class UserVo extends User {
	
	private static final long serialVersionUID = 1L;
	
	private String sexExplain;
	private String zhimaCreditFlagExplain;
	private String businessTypeExplain;
	private String realNameFlagExplain;
	private String statusExplain;
	
	public String getSexExplain() {
		if(this.getSex()!=null){
			return this.getSex().getExplain();
		}
		return sexExplain;
	}
	
	public String getZhimaCreditFlagExplain() {
		if(this.getZhimaCreditFlag()!=null){
			return this.getZhimaCreditFlag().getExplain();
		}
		return zhimaCreditFlagExplain;
	}
	
	public String getBusinessTypeExplain() {
		if(this.getBusinessType()!=null){
			return this.getBusinessType().getExplain();
		}
		return businessTypeExplain;
	}
	
	public String getRealNameFlagExplain() {
		if(this.getRealNameFlag()!=null){
			return this.getRealNameFlag().getExplain();
		}
		return realNameFlagExplain;
	}
	
	public String getStatusExplain() {
		if(this.getStatus()!=null){
			return this.getStatus().getExplain();
		}
		return statusExplain;
	}
	
	
}