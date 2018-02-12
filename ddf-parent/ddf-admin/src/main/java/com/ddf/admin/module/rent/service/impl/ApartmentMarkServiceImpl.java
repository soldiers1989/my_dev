package com.ddf.admin.module.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.admin.module.rent.service.ApartmentMarkService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentMark;
import com.ddf.entity.rent.query.ApartmentMarkQuery;
import com.ddf.entity.rent.vo.ApartmentMarkVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.rent.ApartmentMarkReference;



@Service
public class ApartmentMarkServiceImpl implements ApartmentMarkService {
	@Autowired
	private ApartmentMarkReference apartmentMarkReference;
	
	@Override
	public ApiResponse<ApartmentMark> get(String id){
		return apartmentMarkReference.query(id);
	}
	
	@Override
	public ApiResponse<Page<ApartmentMarkVo>> list(ApartmentMarkQuery apartmentMarkQuery){
		return apartmentMarkReference.list(apartmentMarkQuery);
	}
//	
//	@Override
//	public int count(Map<String, Object> map){
//		return apartmentMarkReference.count(map);
//	}
//	
	@Override
	public ApiResponse<Boolean> save(ApartmentMark apartmentMark){
		return apartmentMarkReference.create(apartmentMark, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> update(ApartmentMark apartmentMark){
		return apartmentMarkReference.modify(apartmentMark, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> remove(String id){
		return apartmentMarkReference.remove(id);
	}

//	@Override
//	public int batchRemove(String[] ids){
//		return apartmentMarkDao.batchRemove(ids);
//	}
	
}
