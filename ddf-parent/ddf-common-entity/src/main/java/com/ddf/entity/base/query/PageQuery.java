package com.ddf.entity.base.query;

public class PageQuery {
	
	private long offset;
	private int rows;
	
	public PageQuery(long offset , int rows){
		this.offset = offset;
		this.rows = rows;
	}
	
	public long getOffset() {
		return offset;
	}
	public int getRows() {
		return rows;
	}
	
	public String getPageSql(){
		return " limit " + offset +"," + rows;
	}

}
