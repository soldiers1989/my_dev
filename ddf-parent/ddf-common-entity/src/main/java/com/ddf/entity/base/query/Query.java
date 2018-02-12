package com.ddf.entity.base.query;

import com.ddf.entity.base.dto.BaseEntity;

public class Query extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String pageSql;
	private String sortSql;
	
	private int pageSize;
	private int pageNum;
	
	public Query() {
		super();
		this.pageSize = 10;
		this.pageNum = 1;
	}

	public void buildPageSql() {
		long offset = (pageNum-1)*pageSize;
		pageSql =  " limit " + offset +"," + pageSize;
	}
	
	public void buildPageSql(int pageNum , int pageSize) {
		long offset = (pageNum-1)*pageSize;
		pageSql =  " limit " + offset +"," + pageSize;
	}
	
	public void buildSortSql(String sortSql) {
		if(checkSqlinj(sortSql)){
			this.sortSql = null;
		}else{
			this.sortSql = sortSql;
		}
	}
	
	private boolean checkSqlinj(String str){
		if(str==null){
			return false;
		}
		String injStr = "'|and|exec|insert|select|delete|update|drop|*|%|chr|mid|master|truncate|char|declare|;|or |-|+";
		String[] injArr = injStr.split("\\|");
		for (String inj : injArr) {
			if(str.contains(inj)){
				return true;
			}
		}
		return false;
	}
	
	public String getSortSql() {
		return sortSql;
	}

	public String getPageSql(){
		return pageSql;
	}

	public int getPageSize() {
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

	
	
}	
