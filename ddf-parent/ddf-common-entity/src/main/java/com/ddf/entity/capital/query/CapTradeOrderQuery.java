package com.ddf.entity.capital.query;

import com.ddf.entity.capital.dto.CapTradeOrder;

/**
 * cap_trade_order EntityQuery
 * @author robot
 * @version 2018-01-10
 */
public class CapTradeOrderQuery extends CapTradeOrder {

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