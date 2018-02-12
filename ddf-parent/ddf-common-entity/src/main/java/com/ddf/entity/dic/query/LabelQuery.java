package com.ddf.entity.dic.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.dic.dto.Label;

/**
 * label EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class LabelQuery extends Query {
	private static final long serialVersionUID = 1L;

	public LabelQuery(){
		this.label = new Label();
	}
	
	private Label label;

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}
}