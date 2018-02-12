package com.ddf.entity.rent.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.rent.dto.SpiderHouse;

/**
 * spider_house EntityQuery
 * @author robot
 * @version 2018-01-19
 */
public class SpiderHouseQuery extends Query {

	private static final long serialVersionUID = 1L;

	public SpiderHouseQuery(){
		this.spiderHouse = new SpiderHouse();
	}
	
	private SpiderHouse spiderHouse;

	public SpiderHouse getSpiderHouse() {
		return spiderHouse;
	}

	public void setSpiderHouse(SpiderHouse spiderHouse) {
		this.spiderHouse = spiderHouse;
	}

}