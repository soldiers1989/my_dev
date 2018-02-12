package com.ddf.admin.module.rent.service;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentAppointment;
import com.ddf.entity.rent.query.ApartmentAppointmentQuery;
import com.ddf.entity.rent.vo.ApartmentAppointmentVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 房源预约表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:36:03
 */
public interface ApartmentAppointmentService {
	
	ApiResponse<ApartmentAppointment> get(String id);
	
	ApiResponse<Page<ApartmentAppointmentVo>> list(ApartmentAppointmentQuery apartmentAppointmentQuery);
	
//	int count(Map<String, Object> map);
//	
	ApiResponse<Boolean> save(ApartmentAppointment apartmentAppointment);
	
	ApiResponse<Boolean> update(ApartmentAppointment apartmentAppointment);
	
	ApiResponse<Boolean> remove(String id);
	
//	int batchRemove(String[] ids);
}
