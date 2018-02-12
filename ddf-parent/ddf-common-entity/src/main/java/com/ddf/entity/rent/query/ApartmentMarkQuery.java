package com.ddf.entity.rent.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.ApartmentMark;

/**
 * apartment_mark EntityQuery
 * @author robot
 * @version 2018-02-02
 */
public class ApartmentMarkQuery extends Query {

	private static final long serialVersionUID = 1L;
	private ApartmentMark apartmentMark;
/*	private String pageNum;		// 分页
	private String pageSize;	// 分页
*/	private String mobile;		// 手机号

	public ApartmentMarkQuery(){
		this.apartmentMark = new ApartmentMark();
	}
	
	public ApartmentMark getApartmentMark() {
		return apartmentMark;
	}

	public void setApartmentMark(ApartmentMark apartmentMark) {
		this.apartmentMark = apartmentMark;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
}