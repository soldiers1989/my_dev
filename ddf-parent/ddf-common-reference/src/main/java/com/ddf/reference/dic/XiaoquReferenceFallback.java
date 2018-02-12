package com.ddf.reference.dic;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.dto.Xiaoqu;
import com.ddf.entity.dic.query.XiaoquQuery;
import com.ddf.entity.dic.vo.XiaoquVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class XiaoquReferenceFallback implements XiaoquReference {

	@Override
	public ApiResponse<Xiaoqu> query(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> create(Xiaoqu xiaoqu) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> modify(Xiaoqu xiaoqu) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Page<XiaoquVo>> pageQuery(XiaoquQuery xiaoquQuery) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<List<XiaoquVo>> queryXiaoquByLngLat(Integer pageNum, Integer pageSize, String lng, String lat) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Long> queryXiaoquNum() {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<List<XiaoquVo>> query4where(Integer pageNum, Integer pageSize, String name, String address) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}
}
