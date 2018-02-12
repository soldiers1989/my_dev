package com.ddf.reference.rent;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentAppointment;
import com.ddf.entity.rent.eo.ApartmentAppointmentStatus;
import com.ddf.entity.rent.query.ApartmentAppointmentQuery;
import com.ddf.entity.rent.vo.ApartmentAppointmentVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 预约
 * @author qwe
 *
 */
@FeignClient(value = "service-rent"/*, fallback = ApartmentAppointmentReferenceFallback.class*/)
public interface ApartmentAppointmentReference {

	//查询单个ApartmentAppointment
	@RequestMapping(value = "/apartmentAppointment/query", method = { RequestMethod.GET })
	public ApiResponse<ApartmentAppointment> query(@RequestParam(value = "id") String id);

	//	/创建ApartmentAppointment
	@RequestMapping(value = "/apartmentAppointment/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(ApartmentAppointment apartmentAppointment,
			@RequestParam(value = "currentUserId") String currentUserId);

	//修改ApartmentAppointment信息
	@RequestMapping(value = "/apartmentAppointment/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(ApartmentAppointment apartmentAppointment,
			 @RequestParam(value = "currentUserId") String currentUserId) ;

	//删除ApartmentAppointment信息
	@RequestMapping(value = "/apartmentAppointment/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);

	//取消预约/拒绝预约
	@RequestMapping(value = "/apartmentAppointment/review/{status}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> review(@RequestParam(value = "currentUserId") String currentUserId,
			@RequestParam(value = "id") String id,
			@PathVariable(value = "status") ApartmentAppointmentStatus status);
	
	//预约列表
	@RequestMapping(value = "/apartmentAppointment/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentAppointmentVo>> list(ApartmentAppointmentQuery apartmentAppointmentQuery);

}
