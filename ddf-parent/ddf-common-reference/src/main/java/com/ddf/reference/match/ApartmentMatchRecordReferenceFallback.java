package com.ddf.reference.match;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.match.dto.ApartmentMatchRecord;
import com.ddf.entity.rent.match.query.ApartmentMatchRecordQuery;
import com.ddf.entity.rent.match.vo.ApartmentMatchRecordVo;
import com.ddf.entity.response.ApiResponse;

public class ApartmentMatchRecordReferenceFallback implements ApartmentMatchRecordReference {

    @Override
    public ApiResponse<ApartmentMatchRecord> query(String id) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> create(ApartmentMatchRecord houseMatchRecord) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> modify(ApartmentMatchRecord houseMatchRecord) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> remove(String id) {
        return null;
    }

    @Override
    public ApiResponse<Page<ApartmentMatchRecordVo>> pagequery(ApartmentMatchRecordQuery query) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> hide(String id) {
        return null;
    }
}
