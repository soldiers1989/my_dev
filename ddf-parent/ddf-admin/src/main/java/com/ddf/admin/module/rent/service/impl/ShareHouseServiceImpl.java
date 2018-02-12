package com.ddf.admin.module.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.admin.module.rent.service.ShareHouseService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ShareHouse;
import com.ddf.entity.rent.query.ShareHouseQuery;
import com.ddf.entity.rent.vo.ShareHouseVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.rent.ShareHouseReference;



@Service
public class ShareHouseServiceImpl implements ShareHouseService {
	@Autowired
	private ShareHouseReference shareHouseReference;
	
	@Override
	public ApiResponse<ShareHouse> get(String id){
		return shareHouseReference.query(id);
	}
	
	@Override
	public ApiResponse<Page<ShareHouseVo>> list(ShareHouseQuery shareHouseQuery){
		return shareHouseReference.list(shareHouseQuery);
	}
//	
//	@Override
//	public int count(Map<String, Object> map){
//		return shareHouseReference.count(map);
//	}
//	
	@Override
	public ApiResponse<Boolean> save(ShareHouse shareHouse){
		return shareHouseReference.create(shareHouse, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> update(ShareHouse shareHouse){
		return shareHouseReference.modify(shareHouse, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> remove(String id){
		return shareHouseReference.remove(id);
	}
	
//	@Override
//	public int batchRemove(String[] ids){
//		return shareHouseReference.batchRemove(ids);
//	}
	
}
