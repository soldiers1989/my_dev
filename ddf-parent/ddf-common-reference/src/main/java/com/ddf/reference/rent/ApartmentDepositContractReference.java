package com.ddf.reference.rent;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentDepositContract;
import com.ddf.entity.rent.eo.ApartmentDepositContractStatus;
import com.ddf.entity.rent.query.ApartmentDepositContractQuery;
import com.ddf.entity.rent.vo.ApartmentDepositContractVo;
import com.ddf.entity.response.ApiResponse;
/**
 * 房源合同
 * @author qwe
 *
 */
@FeignClient(value = "service-rent"/*,fallback = ApartmentDepositContractReferenceFallback.class*/)
public interface ApartmentDepositContractReference {
	
	//查询单个ApartmentDepositContract
	@RequestMapping(value = "/apartmentDepositContract/query", method = { RequestMethod.GET })
	public ApiResponse<ApartmentDepositContract> query( @RequestParam(value = "id") String id);

	//创建ApartmentDepositContract
	@RequestMapping(value = "/apartmentDepositContract/create", method = { RequestMethod.POST })
	public ApiResponse<Boolean> create(ApartmentDepositContract apartmentDepositContract, 
			@RequestParam(value = "currentUserId") String currentUserId);

	//修改ApartmentDepositContract信息
	@RequestMapping(value = "/apartmentDepositContract/modify", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify(ApartmentDepositContract apartmentDepositContract, @RequestParam(value = "currentUserId") String currentUserId);

	//删除ApartmentDepositContract信息
	@RequestMapping(value = "/apartmentDepositContract/remove", method = { RequestMethod.POST })
	public ApiResponse<Boolean> remove( @RequestParam(value = "id") String id);
	
	//开启预定/关闭预定
	@RequestMapping(value = "/apartmentDepositContract/review/{status}", method = { RequestMethod.POST })
	public ApiResponse<Boolean> modify4status(@RequestParam(value = "currentUserId") String currentUserId,
			@RequestParam(value = "id") String id,
			@PathVariable(value="status") ApartmentDepositContractStatus status);
	
	//预定列表
	@RequestMapping(value = "/apartmentDepositContract/list", method = { RequestMethod.POST })
	public ApiResponse<Page<ApartmentDepositContractVo>> list(ApartmentDepositContractQuery apartmentDepositContractQuery) ;
}
