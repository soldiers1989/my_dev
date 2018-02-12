package com.ddf.entity.dic.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.dic.dto.Area;

/**
 * area EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class AreaQuery extends Query {

	private static final long serialVersionUID = 1L;

	public AreaQuery(){
		this.area = new Area();
	}
	
	private Area area;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}