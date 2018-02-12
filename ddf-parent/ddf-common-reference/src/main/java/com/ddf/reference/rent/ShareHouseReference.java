package com.ddf.reference.rent;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ShareHouse;
import com.ddf.entity.rent.eo.ShareHouseStatus;
import com.ddf.entity.rent.query.ShareHouseQuery;
import com.ddf.entity.rent.vo.ShareHouseVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 合租房源
 * @author qwe
 *
 */
@FeignClient(value = "service-rent"/*, fallback = ShareHouseReferenceFallback.class*/)
public interface ShareHouseReference {
	/**
	 * 查询单个ShareHouse
	 */
	@RequestMapping(value = "/shareHouse/query", method = { RequestMethod.GET })
	public ApiResponse<ShareHouse> query(@RequestParam(value="id") String id);

	/**
	 * 创建ShareHouse
	 */
	@RequestMapping(value = "/shareHouse/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(ShareHouse shareHouse,
			@RequestParam(value="currentUserId") String currentUserId);

	/**
	 * 修改ShareHouse信息
	 */
	@RequestMapping(value = "/shareHouse/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(ShareHouse shareHouse,
			@RequestParam(value="currentUserId") String currentUserId);

	/**
	 * 删除ShareHouse信息--级联删除合租房间
	 */
	@RequestMapping(value = "/shareHouse/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove(@RequestParam(value="id") String id);

	/**
	 * 合租房源列表
	 */
	@RequestMapping(value = "/shareHouse/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ShareHouseVo>> list(ShareHouseQuery shareHouseQuery);

	/**
	 * 合租房源审核通过/合租房源审核驳回
	 */
	@RequestMapping(value = "/shareHouse/review/{status}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> review(@RequestParam(value="id") String id,
			@PathVariable("status") ShareHouseStatus status);
}
