package com.ddf.reference.rent;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.eo.ApartmentMatchStatus;
import com.ddf.entity.rent.eo.ApartmentStatus;
import com.ddf.entity.rent.query.ApartmentQuery;
import com.ddf.entity.rent.vo.ApartmentVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 整租房源
 * 
 * @author qwe
 *
 */
@FeignClient(value = "service-rent"/*, fallback = ApartmentReferenceFallback.class*/)
public interface ApartmentReference {

	/**
	 * 查询单个Apartment
	 */
	@RequestMapping(value = "/apartment/query", method = { RequestMethod.GET })
	ApiResponse<Apartment> query(@RequestParam(value = "id") String id);

	/**
	 * 创建Apartment
	 */
	@RequestMapping(value = "/apartment/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(Apartment apartment,
			@RequestParam(value = "currentUserId") String currentUserId);

	/**
	 * 修改Apartment信息
	 */
	@RequestMapping(value = "/apartment/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(Apartment apartment,
			@RequestParam(value = "currentUserId") String currentUserId);

	/**
	 * 删除Apartment信息
	 */
	@RequestMapping(value = "/apartment/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);

	/**
	 * 招租/停租
	 */
	@RequestMapping(value = "/apartment/matchStatus/{matchStatus}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> matchStatus(@RequestParam(value = "id") String id,
			@PathVariable("matchStatus") ApartmentMatchStatus matchStatus);

	/**
	 * 整租房源列表
	 */
	@RequestMapping(value = "/apartment/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentVo>> list(ApartmentQuery apartmentQuery);

	/**
	 * 整租房源审核通过/整租房源审核驳回
	 */
	@RequestMapping(value = "/apartment/review/{status}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> review(@RequestParam(value = "id") String id,
			@PathVariable("status") ApartmentStatus status);
}
