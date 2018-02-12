package com.ddf.reference.cache;

import java.util.Set;

import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;

import org.springframework.stereotype.Component;

@Component
public class CacheReferenceFallback implements CacheReference{

	@Override
	public ApiResponse<String> get(String key) {
		return ApiResponse.fail(ApiResponseResult.FILE_GET_ERROR);
	}

	@Override
	public ApiResponse<Boolean> put(String key, String value) {
		// TODO Auto-generated method stub
		return ApiResponse.fail(ApiResponseResult.FILE_PUT_ERROR);
	}

	@Override
	public ApiResponse<Boolean> put4time(String key, String value, int seconds) {
		// TODO Auto-generated method stub
		return ApiResponse.fail(ApiResponseResult.FILE_PUT_ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove(String key) {
		// TODO Auto-generated method stub
		return ApiResponse.fail(ApiResponseResult.FILE_REMOVE_ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove4begin(String key) {
		// TODO Auto-generated method stub
		return ApiResponse.fail(ApiResponseResult.FILE_REMOVE_ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove4end(String key) {
		// TODO Auto-generated method stub
		return ApiResponse.fail(ApiResponseResult.FILE_REMOVE_ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove4like(String key) {
		// TODO Auto-generated method stub
		return ApiResponse.fail(ApiResponseResult.FILE_REMOVE_ERROR);
	}

	@Override
	public ApiResponse<Set<String>> getKeys4begin(String key) {
		// TODO Auto-generated method stub
		return ApiResponse.fail(ApiResponseResult.FILE_REMOVE_ERROR);
	}

}
