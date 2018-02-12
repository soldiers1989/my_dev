package com.ddf.entity.rent.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.ShareHouse;

/**
 * share_house EntityQuery
 * @author robot
 * @version 2018-02-02
 */
public class ShareHouseQuery extends Query {

	private static final long serialVersionUID = 1L;
	private ShareHouse shareHouse;
	private Date startCreateDate;	//创建开始时间 
	private Date endCreateDate;		//创建结束时间

	public ShareHouseQuery(){
		this.shareHouse = new ShareHouse();
	}
	

	public ShareHouse getShareHouse() {
		return shareHouse;
	}

	public void setShareHouse(ShareHouse shareHouse) {
		this.shareHouse = shareHouse;
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

}