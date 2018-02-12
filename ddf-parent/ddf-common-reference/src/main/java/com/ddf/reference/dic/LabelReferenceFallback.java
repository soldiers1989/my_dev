package com.ddf.reference.dic;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.dto.Label;
import com.ddf.entity.dic.eo.LabelType;
import com.ddf.entity.dic.query.LabelQuery;
import com.ddf.entity.dic.vo.LabelVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LabelReferenceFallback implements LabelReference {
    @Override
    public ApiResponse<Page<LabelVo>> pagequery(LabelQuery labelQuery) {
        return ApiResponse.fail(ApiResponseResult.ERROR);
    }

    @Override
    public ApiResponse<Label> query(String id) {
        return ApiResponse.fail(ApiResponseResult.ERROR);
    }

    @Override
    public ApiResponse<Boolean> create(Label label) {
        return ApiResponse.fail(ApiResponseResult.ERROR);
    }

    @Override
    public ApiResponse<Boolean> modify(Label label) {
        return ApiResponse.fail(ApiResponseResult.ERROR);
    }

    @Override
    public ApiResponse<Boolean> remove(String id) {
        return ApiResponse.fail(ApiResponseResult.ERROR);
    }

    @Override
    public ApiResponse<List<LabelVo>> query4type(LabelType type) {
        return ApiResponse.fail(ApiResponseResult.ERROR);
    }
}
