package com.ddf.admin.module.rent.service;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.RentDemand;
import com.ddf.entity.rent.query.RentDemandQuery;
import com.ddf.entity.rent.vo.RentDemandVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 租房需求表
 * 
 * @author chglee
 * @email 1992lcg@163.com	
 * @date 2018-02-07 10:39:42
 */
public interface RentDemandService {
	
	ApiResponse<RentDemand> get(String id);
	
	ApiResponse<Page<RentDemandVo>> list(RentDemandQuery rentDemandQuery);
//	
//	int count(Map<String, Object> map);
//	
	ApiResponse<Boolean> save(RentDemand rentDemand);
	
	ApiResponse<Boolean> update(RentDemand rentDemand);
	
	ApiResponse<Boolean> remove(String id);
//	
//	int batchRemove(String[] ids);
}
