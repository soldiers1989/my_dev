package com.ddf.admin.module.rent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.admin.module.rent.service.ApartmentAppointmentService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentAppointment;
import com.ddf.entity.rent.query.ApartmentAppointmentQuery;
import com.ddf.entity.rent.vo.ApartmentAppointmentVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.rent.ApartmentAppointmentReference;



@Service
public class ApartmentAppointmentServiceImpl implements ApartmentAppointmentService {
	@Autowired
	private ApartmentAppointmentReference apartmentAppointmentReference;
	
	@Override
	public ApiResponse<ApartmentAppointment> get(String id){
		return apartmentAppointmentReference.query(id);
	}
	
	@Override
	public ApiResponse<Page<ApartmentAppointmentVo>> list(ApartmentAppointmentQuery apartmentAppointmentQuery){
		return apartmentAppointmentReference.list(apartmentAppointmentQuery);
	}
	
//	@Override
//	public int count(Map<String, Object> map){
//		return apartmentAppointmentReference.count(map);
//	}
//	
	@Override
	public ApiResponse<Boolean> save(ApartmentAppointment apartmentAppointment){
		return apartmentAppointmentReference.create(apartmentAppointment, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> update(ApartmentAppointment apartmentAppointment){
		return apartmentAppointmentReference.modify(apartmentAppointment, "currentUserId");
	}
	
	@Override
	public ApiResponse<Boolean> remove(String id){
		return apartmentAppointmentReference.remove(id);
	}
	
//	@Override
//	public int batchRemove(String[] ids){
//		return apartmentAppointmentDao.batchRemove(ids);
//	}
	
}
