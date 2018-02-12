package com.ddf.entity.sms.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.sms.dto.SmsParam;

/**
 * sms_param EntityQuery
 * @author robot
 * @version 2018-01-30
 */
public class SmsParamQuery extends Query {

	private static final long serialVersionUID = 1L;

	public SmsParamQuery(){
		this.smsParam = new SmsParam();
	}
	
	private SmsParam smsParam;

	public SmsParam getSmsParam() {
		return smsParam;
	}

	public void setSmsParam(SmsParam smsParam) {
		this.smsParam = smsParam;
	}

}