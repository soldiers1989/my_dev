package com.ddf.reference.rent;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.eo.ShareApartmentMatchStatus;
import com.ddf.entity.rent.eo.ShareApartmentStatus;
import com.ddf.entity.rent.query.ShareApartmentQuery;
import com.ddf.entity.rent.vo.ShareApartmentVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 合租房间
 * 
 * @author qwe
 *
 */
@FeignClient(value = "service-rent"/*, fallback = ShareApartmentReferenceFallback.class*/)
public interface ShareApartmentReference {

	/**
	 * 查询单个ShareApartment
	 */
	@RequestMapping(value = "/shareApartment/query", method = { RequestMethod.GET })
	public ApiResponse<ShareApartment> query( @RequestParam(value = "id") String id) ;
	/**
	 * 创建ShareApartment
	 */
	@RequestMapping(value = "/shareApartment/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(ShareApartment shareApartment,
			@RequestParam(value = "currentUserId") String currentUserId);
	/**
	 * 修改ShareApartment信息
	 */
	@RequestMapping(value = "/shareApartment/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(ShareApartment shareApartment,
			@RequestParam(value = "currentUserId") String currentUserId);
	/**
	 * 删除ShareApartment信息
	 */
	@RequestMapping(value = "/shareApartment/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);
	/**
	 * 合租房间list
	 */
	@RequestMapping(value = "/shareApartment/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ShareApartmentVo>> list(ShareApartmentQuery shareApartmentQuery);
	/**
	 * 合租房间招租/合租房间停租
	 */
	@RequestMapping(value = "/shareApartment/matchStatus/{matchStatus}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> matchStatus(@RequestParam(value = "id") String id,
			@PathVariable("matchStatus") ShareApartmentMatchStatus matchStatus);
	/**
	 * 合租房间审核通过/合租房间审核驳回
	 */
	@RequestMapping(value = "/shareApartment/review/{status}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> review(@RequestParam(value = "id") String id,
			@PathVariable("status") ShareApartmentStatus status);

}
