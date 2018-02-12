package com.ddf.entity.dic.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.dic.dto.MetroLine;

/**
 * metro_line EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class MetroLineQuery extends Query {

	private static final long serialVersionUID = 1L;

	public MetroLineQuery(){
		this.metroLine = new MetroLine();
	}
	
	private MetroLine metroLine;

	public MetroLine getMetroLine() {
		return metroLine;
	}

	public void setMetroLine(MetroLine metroLine) {
		this.metroLine = metroLine;
	}

}