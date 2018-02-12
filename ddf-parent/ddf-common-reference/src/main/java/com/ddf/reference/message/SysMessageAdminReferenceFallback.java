package com.ddf.reference.message;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.SysMessageAdmin;
import com.ddf.entity.message.eo.SysMessageAdminStatus;
import com.ddf.entity.message.query.SysMessageAdminQuery;
import com.ddf.entity.message.vo.SysMessageAdminVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class SysMessageAdminReferenceFallback implements SysMessageAdminReference {

	@Override
	public ApiResponse<SysMessageAdmin> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(SysMessageAdmin sysMessageAdmin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(SysMessageAdmin sysMessageAdmin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<SysMessageAdminVo>> batchquery(
			SysMessageAdminQuery sysMessageAdminQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> reviewReject(String id,
			SysMessageAdminStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> send(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
