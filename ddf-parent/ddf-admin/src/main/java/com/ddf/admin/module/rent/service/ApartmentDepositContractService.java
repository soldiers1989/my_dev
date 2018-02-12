package com.ddf.admin.module.rent.service;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentDepositContract;
import com.ddf.entity.rent.query.ApartmentDepositContractQuery;
import com.ddf.entity.rent.vo.ApartmentDepositContractVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 房源合同表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:36:03
 */
public interface ApartmentDepositContractService {
	
	ApiResponse<ApartmentDepositContract> get(String id);
	
	ApiResponse<Page<ApartmentDepositContractVo>> list(ApartmentDepositContractQuery apartmentDepositContractQuery);
	
//	int count(Map<String, Object> map);
//	
	ApiResponse<Boolean> save(ApartmentDepositContract apartmentDepositContract);
	
	ApiResponse<Boolean> update(ApartmentDepositContract apartmentDepositContract);
	
	ApiResponse<Boolean> remove(String id);
		
//	int batchRemove(String[] ids);
}
