package com.ddf.reference.rent;

import org.springframework.stereotype.Component;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentAppointment;
import com.ddf.entity.rent.eo.ApartmentAppointmentStatus;
import com.ddf.entity.rent.query.ApartmentAppointmentQuery;
import com.ddf.entity.rent.vo.ApartmentAppointmentVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;

@Component
public class ApartmentAppointmentReferenceFallback implements ApartmentAppointmentReference {

	@Override
	public ApiResponse<ApartmentAppointment> query(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> create(ApartmentAppointment apartmentAppointment, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> modify(ApartmentAppointment apartmentAppointment, String currentUserId) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> remove(String id) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Boolean> review(String currentUserId, String id, ApartmentAppointmentStatus status) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

	@Override
	public ApiResponse<Page<ApartmentAppointmentVo>> list(ApartmentAppointmentQuery apartmentAppointmentQuery) {
		return ApiResponse.fail(ApiResponseResult.ERROR);
	}

}
