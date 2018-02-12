package com.ddf.reference.common;

import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateReferenceFallback implements DateReference{

	@Override
	public ApiResponse<Date> queryCurrentDate() {
		return ApiResponse.fail(ApiResponseResult.COMMON_DATE_ERROR);
	}
}
