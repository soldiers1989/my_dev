package com.ddf.admin.module.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.admin.module.rent.service.RentDemandService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.RentDemand;
import com.ddf.entity.rent.query.RentDemandQuery;
import com.ddf.entity.rent.vo.RentDemandVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.rent.DemandReference;



@Service
public class RentDemandServiceImpl implements RentDemandService {
	
	@Autowired
	private DemandReference rentDemandReference;
	
	@Override
	public ApiResponse<RentDemand> get(String id){
		return rentDemandReference.query(id);
	}
	
	@Override
	public ApiResponse<Page<RentDemandVo>> list(RentDemandQuery rentDemandQuery){
		return rentDemandReference.list(rentDemandQuery);
	}
//	
//	@Override
//	public int count(Map<String, Object> map){
//		return rentDemandReference.count(map);
//	}
//	
	@Override
	public ApiResponse<Boolean> save(RentDemand rentDemand){
		return rentDemandReference.create(rentDemand, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> update(RentDemand rentDemand){
		return rentDemandReference.modify(rentDemand, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> remove(String id){
		return rentDemandReference.remove(id);
	}
//	
//	@Override
//	public int batchRemove(String[] ids){
//		return rentDemandReference.batchRemove(ids);
//	}
	
}
