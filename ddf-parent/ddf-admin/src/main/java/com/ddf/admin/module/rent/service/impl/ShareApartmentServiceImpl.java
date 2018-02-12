package com.ddf.admin.module.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.admin.module.rent.service.ShareApartmentService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.query.ShareApartmentQuery;
import com.ddf.entity.rent.vo.ShareApartmentVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.rent.ShareApartmentReference;



@Service
public class ShareApartmentServiceImpl implements ShareApartmentService {
	@Autowired
	private ShareApartmentReference shareApartmentReference;
	
	@Override
	public ApiResponse<ShareApartment> get(String id){
		return shareApartmentReference.query(id);
	}
	
	@Override
	public ApiResponse<Page<ShareApartmentVo>> list(ShareApartmentQuery shareApartmentQuery){
		return shareApartmentReference.list(shareApartmentQuery);
	}
//	
//	@Override
//	public int count(Map<String, Object> map){
//		return shareApartmentReference.count(map);
//	}
//	
	@Override
	public ApiResponse<Boolean> save(ShareApartment shareApartment){
		return shareApartmentReference.create(shareApartment, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> update(ShareApartment shareApartment){
		return shareApartmentReference.modify(shareApartment, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> remove(String id){
		return shareApartmentReference.remove(id);
	}
//	@Override
//	public int batchRemove(String[] ids){
//		return shareApartmentDao.batchRemove(ids);
//	}
	
}
