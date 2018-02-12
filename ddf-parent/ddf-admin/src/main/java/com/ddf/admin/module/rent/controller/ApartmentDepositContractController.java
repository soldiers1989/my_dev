package com.ddf.admin.module.rent.controller;

import java.util.ArrayList;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.R;
import com.ddf.admin.module.rent.service.ApartmentDepositContractService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentDepositContract;
import com.ddf.entity.rent.eo.ApartmentDepositContractApartmentType;
import com.ddf.entity.rent.query.ApartmentDepositContractQuery;
import com.ddf.entity.rent.vo.ApartmentDepositContractVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 房源合同表
 * 
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:36:03
 */
 
@Controller
@RequestMapping("/rent/apartmentDepositContract")
public class ApartmentDepositContractController {
	@Autowired
	private ApartmentDepositContractService apartmentDepositContractService;
	
	private static Logger logger =	LoggerFactory.getLogger(ApartmentDepositContractController.class);
	
	@GetMapping()
	@RequiresPermissions("rent:apartmentDepositContract:apartmentDepositContract")
	String ApartmentDepositContract(Model model){
		model.addAttribute("ApartmentDepositContractApartmentType", ApartmentDepositContractApartmentType.values());
	    return "rent/apartmentDepositContract/apartmentDepositContract";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("rent:apartmentDepositContract:apartmentDepositContract")
	public PageUtils list(ApartmentDepositContractQuery apartmentDepositContractQuery){
		try {
			//查询列表数据
			ApiResponse<Page<ApartmentDepositContractVo>> result = apartmentDepositContractService.list(apartmentDepositContractQuery);
			return new PageUtils(result.getData().getList(), (int)result.getData().getTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new PageUtils(new ArrayList<ApartmentDepositContractVo>(), 0);
		}
	}
	
	@GetMapping("/add")
	@RequiresPermissions("rent:apartmentDepositContract:add")
	String add(Model model){
		model.addAttribute("ApartmentDepositContractApartmentType", ApartmentDepositContractApartmentType.values());
	    return "rent/apartmentDepositContract/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("rent:apartmentDepositContract:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<ApartmentDepositContract> apartmentDepositContract = apartmentDepositContractService.get(id);
		model.addAttribute("apartmentDepositContract", apartmentDepositContract.getData());
		model.addAttribute("ApartmentDepositContractApartmentType", ApartmentDepositContractApartmentType.values());
	    return "rent/apartmentDepositContract/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("rent:apartmentDepositContract:add")
	public R save( ApartmentDepositContract apartmentDepositContract){
		try {
			if(apartmentDepositContractService.save(apartmentDepositContract).getData()){
				return R.ok();
			}else{
				return R.error(apartmentDepositContractService.save(apartmentDepositContract).getMessage());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return R.error();
		}
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("rent:apartmentDepositContract:edit")
	public R update( ApartmentDepositContract apartmentDepositContract){
		try {
			if(apartmentDepositContractService.update(apartmentDepositContract).getData()){
				return R.ok();
			}else{
				return R.error(apartmentDepositContractService.update(apartmentDepositContract).getMessage());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return R.error();
		}
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("rent:apartmentDepositContract:remove")
	public R remove( String id){
		try {
			if(apartmentDepositContractService.remove(id).getData()){
				return R.ok();
			}else{
				return R.error(apartmentDepositContractService.remove(id).getMessage());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return R.error();
		}
	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("rent:apartmentDepositContract:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		apartmentDepositContractService.batchRemove(ids);
//		return R.ok();
//	}
	
}
