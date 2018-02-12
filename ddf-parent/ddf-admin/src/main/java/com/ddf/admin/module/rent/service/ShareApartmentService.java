package com.ddf.admin.module.rent.service;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.query.ShareApartmentQuery;
import com.ddf.entity.rent.vo.ShareApartmentVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 合租房间表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:36:03
 */
public interface ShareApartmentService {
	
	ApiResponse<ShareApartment> get(String id);
	
	ApiResponse<Page<ShareApartmentVo>> list(ShareApartmentQuery shareApartmentQuery);
	
//	int count(Map<String, Object> map);
	
	ApiResponse<Boolean> save(ShareApartment shareApartment);
	
	ApiResponse<Boolean> update(ShareApartment shareApartment);
	
	ApiResponse<Boolean> remove(String id);
	
//	int batchRemove(String[] ids);
}
