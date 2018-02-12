package com.ddf.admin.module.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.admin.module.rent.service.ApartmentDepositContractService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentDepositContract;
import com.ddf.entity.rent.query.ApartmentDepositContractQuery;
import com.ddf.entity.rent.vo.ApartmentDepositContractVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.rent.ApartmentDepositContractReference;



@Service
public class ApartmentDepositContractServiceImpl implements ApartmentDepositContractService {
	@Autowired
	private ApartmentDepositContractReference apartmentDepositContractReference;
	
	@Override
	public ApiResponse<ApartmentDepositContract> get(String id){
		return apartmentDepositContractReference.query(id);
	}
	
	@Override
	public ApiResponse<Page<ApartmentDepositContractVo>> list(ApartmentDepositContractQuery apartmentDepositContractQuery){
		return apartmentDepositContractReference.list(apartmentDepositContractQuery);
	}
	
//	@Override
//	public int count(Map<String, Object> map){
//		return apartmentDepositContractReference.count(map);
//	}
//	
	@Override
	public ApiResponse<Boolean> save(ApartmentDepositContract apartmentDepositContract){
		return apartmentDepositContractReference.create(apartmentDepositContract, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> update(ApartmentDepositContract apartmentDepositContract){
		return apartmentDepositContractReference.modify(apartmentDepositContract, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> remove(String id){
		return apartmentDepositContractReference.remove(id);
	}
	
//	
//	@Override
//	public int batchRemove(String[] ids){
//		return apartmentDepositContractDao.batchRemove(ids);
//	}
	
}
