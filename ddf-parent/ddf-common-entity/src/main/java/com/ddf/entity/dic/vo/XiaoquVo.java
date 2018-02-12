package com.ddf.entity.dic.vo;

import com.ddf.entity.dic.dto.Xiaoqu;
import org.springframework.util.StringUtils;

/**
 * xiaoqu EntityVo
 * @author robot
 * @version 2018-01-10
 */
public class XiaoquVo extends Xiaoqu {

	private static final long serialVersionUID = 1L;
	private String position;//小区位置（市-区域-板块）

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}