package com.ddf.admin.module.rent.service;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ShareHouse;
import com.ddf.entity.rent.query.ShareHouseQuery;
import com.ddf.entity.rent.vo.ShareHouseVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 合租房源表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:39:26
 */
public interface ShareHouseService {
	
	ApiResponse<ShareHouse> get(String id);
	
	ApiResponse<Page<ShareHouseVo>> list(ShareHouseQuery shareHouseQuery);
//	
//	int count(Map<String, Object> map);
//	
	ApiResponse<Boolean> save(ShareHouse shareHouse);
	
	ApiResponse<Boolean> update(ShareHouse shareHouse);
	
	ApiResponse<Boolean> remove(String id);
	
//	int batchRemove(String[] ids);
}
