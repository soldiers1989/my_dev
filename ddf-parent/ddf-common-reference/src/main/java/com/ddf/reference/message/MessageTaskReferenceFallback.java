package com.ddf.reference.message;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.MessageTask;
import com.ddf.entity.message.query.MessageTaskQuery;
import com.ddf.entity.message.vo.MessageTaskVo;
import com.ddf.entity.response.ApiResponse;

@Component
public class MessageTaskReferenceFallback implements MessageTaskReference {

	@Override
	public ApiResponse<MessageTask> query(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> create(MessageTask messageTask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(MessageTask messageTask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Page<MessageTaskVo>> batchquery(
			MessageTaskQuery messageTaskQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
