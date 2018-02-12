package com.ddf.reference.common;

import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import org.springframework.stereotype.Component;

@Component
public class IdReferenceFallback implements IdReference{

	@Override
	public ApiResponse<String> createId(TableName tableName) {
		return ApiResponse.fail(ApiResponseResult.COMMON_ID_ERROR);
	}

}
