package com.ddf.entity.dic.vo;

import com.ddf.entity.dic.dto.Label;

/**
 * label EntityVo
 * @author robot
 * @version 2018-01-16
 */
public class LabelVo extends Label {
	
	private static final long serialVersionUID = 1L;
	private String typeStr;

	public String getTypeStr() {
		if (super.getType() ==null) return "";
		return super.getType().getExplain();
	}
}