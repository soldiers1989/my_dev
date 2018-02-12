package com.ddf.reference.dic;

import com.ddf.entity.dic.dto.Area;
import com.ddf.entity.dic.eo.AreaType;
import com.ddf.entity.dic.vo.AreaVo;
import com.ddf.entity.dic.vo.XiaoquVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AreaReferenceFallback implements AreaReference {

	@Override
	public ApiResponse<Area> query(String id) {
		return null;
	}

	@Override
	public ApiResponse<List<AreaVo>> queryChilds(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<List<AreaVo>> queryListByType(AreaType type) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

}
