package com.ddf.admin.module.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.admin.module.rent.service.ApartmentService;
import com.ddf.admin.system.dao.UserDao;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.query.ApartmentQuery;
import com.ddf.entity.rent.vo.ApartmentVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.rent.ApartmentReference;



@Service
public class ApartmentServiceImpl implements ApartmentService {
	@Autowired
	UserDao userMapper;
	@Autowired
	private ApartmentReference apartmentReference;
	
	@Override
	public ApiResponse<Apartment> get(String id){
		return apartmentReference.query(id);
	}
	
	@Override
	public ApiResponse<Page<ApartmentVo>> list(ApartmentQuery apartmentQuery){
		return apartmentReference.list(apartmentQuery);
	}
//	
//	@Override
//	public int count(Map<String, Object> map){
//		return apartmentReference.count(map);
//	}
//	
	@Override
	public ApiResponse<Boolean> save(Apartment apartment){
		return apartmentReference.create(apartment, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> update(Apartment apartment){
		return apartmentReference.modify(apartment, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> remove(String id){
		return apartmentReference.remove(id);
	}
	
//	@Override
//	public int batchRemove(String[] ids){
//		return apartmentReference.batchRemove(ids);
//	}
	
}
