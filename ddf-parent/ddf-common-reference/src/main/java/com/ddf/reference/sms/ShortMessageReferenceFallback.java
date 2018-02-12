package com.ddf.reference.sms;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.sms.dto.ShortMessage;
import com.ddf.entity.sms.query.ShortMessageQuery;
import com.ddf.entity.sms.vo.ShortMessageVo;

@Component
public class ShortMessageReferenceFallback implements ShortMessageReference {

	@Override
	public ApiResponse<ShortMessage> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(ShortMessage shortMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(ShortMessage shortMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> checkImgCodeRequired(String mobile,
			String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> sendCode(String mobile, String txyzm,
			String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> checkCode(String mobile, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<ShortMessageVo>> batchquery(
			ShortMessageQuery shortMessageQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
