package com.ddf.entity.capital.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.capital.dto.BondOrder;

/**
 * bond_order EntityQuery
 * @author robot
 * @version 2018-01-22
 */
public class BondOrderQuery extends Query {

	private static final long serialVersionUID = 1L;

	
	public BondOrderQuery(){
		this.bondOrder = new BondOrder();
	}
	
	private BondOrder bondOrder;

	public BondOrder getBondOrder() {
		return bondOrder;
	}

	public void setBondOrder(BondOrder bondOrder) {
		this.bondOrder = bondOrder;
	}


}