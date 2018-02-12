package com.ddf.admin.module.rent.service;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentMark;
import com.ddf.entity.rent.query.ApartmentMarkQuery;
import com.ddf.entity.rent.vo.ApartmentMarkVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 房源收藏表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:36:03
 */
public interface ApartmentMarkService {
	
	ApiResponse<ApartmentMark> get(String id);
	
	ApiResponse<Page<ApartmentMarkVo>> list(ApartmentMarkQuery apartmentMarkQuery);
//	
//	int count(Map<String, Object> map);
//	
	ApiResponse<Boolean> save(ApartmentMark apartmentMark);
	
	ApiResponse<Boolean> update(ApartmentMark apartmentMark);
	
	ApiResponse<Boolean> remove(String id);
	
//	int batchRemove(String[] ids);
}
