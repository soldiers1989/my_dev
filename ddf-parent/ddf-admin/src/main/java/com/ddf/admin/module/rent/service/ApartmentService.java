package com.ddf.admin.module.rent.service;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.query.ApartmentQuery;
import com.ddf.entity.rent.vo.ApartmentVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 整租房源表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:36:03
 */
public interface ApartmentService {
	
	ApiResponse<Apartment> get(String id);
	
	ApiResponse<Page<ApartmentVo>> list(ApartmentQuery apartmentQuery);
//	
//	int count(Map<String, Object> map);
//	
	ApiResponse<Boolean> save(Apartment apartment);
	
	ApiResponse<Boolean> update(Apartment apartment);
	
	ApiResponse<Boolean> remove(String id);
	
//	int batchRemove(String[] ids);
}
