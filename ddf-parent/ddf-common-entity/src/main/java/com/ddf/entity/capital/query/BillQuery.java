package com.ddf.entity.capital.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.capital.dto.Bill;

/**
 * bill EntityQuery
 * @author robot
 * @version 2018-01-22
 */
public class BillQuery extends Query {

	private static final long serialVersionUID = 1L;

	/*private int pageSize;
	
	private int pageNum;
	
	private String orderBySql;*/
	
	public BillQuery(){
		this.bill = new Bill();
	}
	
	private Bill bill;

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	/*public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getOrderBySql() {
		return orderBySql;
	}

	public void setOrderBySql(String orderBySql) {
		this.orderBySql = orderBySql;
	}*/

}