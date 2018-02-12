package com.ddf.entity.dic.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.dic.dto.MetroStation;

/**
 * metro_station EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class MetroStationQuery extends Query {

	private static final long serialVersionUID = 1L;

	public MetroStationQuery(){
		this.metroStation = new MetroStation();
	}
	
	private MetroStation metroStation;

	public MetroStation getMetroStation() {
		return metroStation;
	}

	public void setMetroStation(MetroStation metroStation) {
		this.metroStation = metroStation;
	}

}