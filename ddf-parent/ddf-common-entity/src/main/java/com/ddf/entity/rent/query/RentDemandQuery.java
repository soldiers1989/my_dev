package com.ddf.entity.rent.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.RentDemand;


/**
 * rent_demand EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class RentDemandQuery extends Query {

	private static final long serialVersionUID = 1L;
	private RentDemand rentDemand;
	private Date startCreateDate;	//创建开始时间 
	private Date endCreateDate;		//创建结束时间
	/*private String pageNum;		// 分页
	private String pageSize;	//	分页
*/	
	public RentDemandQuery(){
		this.rentDemand = new RentDemand();
	}

	public RentDemand getRentDemand() {
		return rentDemand;
	}

	public void setRentDemand(RentDemand rentDemand) {
		this.rentDemand = rentDemand;
	}


	public Date getStartCreateDate() {
		return startCreateDate;
	}


	public void setStartCreateDate(Date startCreateDate) {
		this.startCreateDate = startCreateDate;
	}


	public Date getEndCreateDate() {
		return endCreateDate;
	}


	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	/*public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}*/

}