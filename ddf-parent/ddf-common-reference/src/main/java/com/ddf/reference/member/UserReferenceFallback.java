package com.ddf.reference.member;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.member.query.UserQuery;
import com.ddf.entity.member.vo.UserVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;

@Component
public class UserReferenceFallback implements UserReference {

	@Override
	public ApiResponse<Page<UserVo>> batchqueryByCreateDateAsc(int pageNum, int pageSize) {
		return ApiResponse.fail(ApiResponseResult.COMMON_USER_BATCHQUERY_ERROR);
	}

	@Override
	public ApiResponse<Boolean> setBondFlag(String currentUserId, boolean bondFlag) {
		// TODO Auto-generated method stub
		return ApiResponse.fail(ApiResponseResult.COMMON_USER_BATCHQUERY_ERROR);
	}

	@Override
	public ApiResponse<User> query(String id) {
		// TODO Auto-generated method stub
		return ApiResponse.fail(ApiResponseResult.COMMON_USER_BATCHQUERY_ERROR);
	}

	@Override
	public ApiResponse<UserVo> queryByMobile(String mobile) {
		// TODO Auto-generated method stub
		return ApiResponse.fail(ApiResponseResult.COMMON_USER_BATCHQUERY_ERROR);
	}

	@Override
	public ApiResponse<Page<UserVo>> batchqueryByUserQuery(UserQuery userQuery) {
		// TODO Auto-generated method stub
		return ApiResponse.fail(ApiResponseResult.COMMON_USER_BATCHQUERY_ERROR);
	}

	@Override
	public ApiResponse<Boolean> create(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> modify(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse<Boolean> setAnswerFlag(String currentUserId,
			boolean answerFlag) {
		// TODO Auto-generated method stub
		return null;
	}


}
