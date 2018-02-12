package com.ddf.entity.member.query;

import java.util.Date;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.member.dto.AlipayZhimaCredit;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * alipay_zhima_credit EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class AlipayZhimaCreditQuery extends Query {

	private static final long serialVersionUID = 1L;

	public AlipayZhimaCreditQuery(){
		this.alipayZhimaCredit = new AlipayZhimaCredit();
	}
	
	private AlipayZhimaCredit alipayZhimaCredit;

	public AlipayZhimaCredit getAlipayZhimaCredit() {
		return alipayZhimaCredit;
	}

	public void setAlipayZhimaCredit(AlipayZhimaCredit alipayZhimaCredit) {
		this.alipayZhimaCredit = alipayZhimaCredit;
	}

	
	private Date startCreateDate;
	private Date endCreateDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartCreateDate() {
		return startCreateDate;
	}

	public void setStartCreateDate(Date startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

}