package com.ddf.reference.message;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.SysMessage;
import com.ddf.entity.message.query.SysMessageQuery;
import com.ddf.entity.message.vo.SysMessageVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class SysMessageReferenceFallback implements SysMessageReference {

	@Override
	public ApiResponse<SysMessage> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(SysMessage sysMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(SysMessage sysMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<SysMessageVo>> query4user(String currentUserId,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<SysMessageVo>> batchquery(
			SysMessageQuery sysMessageQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Long> queryAllCount4user(String currentUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Long> queryUnreadCount4user(String currentUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> setRead(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
