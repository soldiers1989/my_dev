package com.ddf.entity.student.query;

import com.ddf.entity.student.dto.User;

/**
 * user EntityQuery
 * @author robot
 * @version 2018-01-02
 */
public class UserQuery extends User {

	private static final long serialVersionUID = 1L;

	private long offset;
	private int rows;

	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

}