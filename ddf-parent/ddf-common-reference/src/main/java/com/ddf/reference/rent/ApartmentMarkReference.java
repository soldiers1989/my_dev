package com.ddf.reference.rent;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentMark;
import com.ddf.entity.rent.query.ApartmentMarkQuery;
import com.ddf.entity.rent.vo.ApartmentMarkVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 收藏
 * @author qwe
 *
 */
@FeignClient(value = "service-rent"/*, fallback = ApartmentMarkReferenceFallback.class*/)
public interface ApartmentMarkReference {

	//	/查询单个ApartmentMark
	@RequestMapping(value = "/apartmentMark/query", method = { RequestMethod.GET })
	public ApiResponse<ApartmentMark> query( @RequestParam(value = "id") String id);

	//创建ApartmentMark
	@RequestMapping(value = "/apartmentMark/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(ApartmentMark apartmentMark,
			@RequestParam(value = "currentUserId") String currentUserId);

	//修改ApartmentMark信息
	@RequestMapping(value = "/apartmentMark/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(ApartmentMark apartmentMark,
			@RequestParam(value = "currentUserId") String currentUserId);

	//删除ApartmentMark信息
	@RequestMapping(value = "/apartmentMark/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);
	
	//收藏列表
	@RequestMapping(value = "/apartmentMark/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentMarkVo>> list(ApartmentMarkQuery apartmentMarkQuery);
}
