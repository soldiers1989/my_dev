package com.ddf.entity.rent.vo;

import java.math.BigDecimal;

import com.ddf.entity.rent.dto.ShareHouse;

/**
 * share_house EntityVo
 * @author robot
 * @version 2018-02-02
 */
public class ShareHouseVo extends ShareHouse {
	
	private static final long serialVersionUID = 1L;
	
	private Long matchStatusOpenNum;	// 正在招租数量
	private BigDecimal minAmout;;	// 正在招租房间的最低价

	public Long getMatchStatusOpenNum() {
		return matchStatusOpenNum;
	}

	public void setMatchStatusOpenNum(Long matchStatusOpenNum) {
		this.matchStatusOpenNum = matchStatusOpenNum;
	}

	public BigDecimal getMinAmout() {
		return minAmout;
	}

	public void setMinAmout(BigDecimal minAmout) {
		this.minAmout = minAmout;
	}

}