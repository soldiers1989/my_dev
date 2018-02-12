package com.ddf.reference.message;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.CallRecord;
import com.ddf.entity.message.query.CallRecordQuery;
import com.ddf.entity.message.vo.CallRecordVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class CallRecordReferenceFallback implements CallRecordReference {

	@Override
	public ApiResponse<CallRecord> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(CallRecord callRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<CallRecordVo>> query4dstUserId(
			String currentUserId, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<CallRecordVo>> query4srcUserId(
			String currentUserId, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<CallRecordVo>> query4dstMobile(String dstMobile,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<CallRecordVo>> query4srcMobile(String srcMobile,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<CallRecordVo>> batchquery(
			CallRecordQuery callRecordQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
