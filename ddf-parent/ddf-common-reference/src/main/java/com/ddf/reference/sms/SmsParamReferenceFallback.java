package com.ddf.reference.sms;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.sms.dto.SmsParam;
import com.ddf.entity.sms.query.SmsParamQuery;
import com.ddf.entity.sms.vo.SmsParamVo;

@Component
public class SmsParamReferenceFallback implements SmsParamReference {

	@Override
	public ApiResponse<SmsParam> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(SmsParam smsParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(SmsParam smsParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<SmsParamVo>> batchquery(SmsParamQuery smsParamQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
