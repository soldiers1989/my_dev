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
import com.ddf.admin.module.rent.service.RentDemandService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.RentDemand;
import com.ddf.entity.rent.eo.RentDemandApartmentType;
import com.ddf.entity.rent.eo.RentDemandMatchStatus;
import com.ddf.entity.rent.query.RentDemandQuery;
import com.ddf.entity.rent.vo.RentDemandVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 租房需求表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:39:42
 */
 
@Controller
@RequestMapping("/rent/rentDemand")
public class RentDemandController {
	@Autowired
	private RentDemandService rentDemandService;
	
	private static Logger logger =	LoggerFactory.getLogger(RentDemandController.class);
	
	@GetMapping()
	@RequiresPermissions("rent:rentDemand:rentDemand")
	String RentDemand(Model model){
		model.addAttribute("RentDemandApartmentType", RentDemandApartmentType.values());
		model.addAttribute("RentDemandMatchStatus", RentDemandMatchStatus.values());
	    return "rent/rentDemand/rentDemand";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("rent:rentDemand:rentDemand")
	public PageUtils list(RentDemandQuery rentDemandQuery){
		try {
			//查询列表数据
			ApiResponse<Page<RentDemandVo>> result = rentDemandService.list(rentDemandQuery);
			return new PageUtils(result.getData().getList(), (int)result.getData().getTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new PageUtils(new ArrayList<RentDemandVo>(), 0);
		}
	}
	
	@GetMapping("/add")
	@RequiresPermissions("rent:rentDemand:add")
	String add(Model model){
		model.addAttribute("RentDemandApartmentType", RentDemandApartmentType.values());
		model.addAttribute("RentDemandMatchStatus", RentDemandMatchStatus.values());
	    return "rent/rentDemand/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("rent:rentDemand:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<RentDemand> rentDemand = rentDemandService.get(id);
		model.addAttribute("rentDemand", rentDemand.getData());
		model.addAttribute("RentDemandApartmentType", RentDemandApartmentType.values());
		model.addAttribute("RentDemandMatchStatus", RentDemandMatchStatus.values());
	    return "rent/rentDemand/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("rent:rentDemand:add")
	public R save( RentDemand rentDemand){
		try {
			if(rentDemandService.save(rentDemand).getData()){
				return R.ok();
			}else{
				return R.error(rentDemandService.save(rentDemand).getMessage());
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
	@RequiresPermissions("rent:rentDemand:edit")
	public R update( RentDemand rentDemand){
		try {
			if(rentDemandService.update(rentDemand).getData()){
				return R.ok();
			}else{
				return R.error(rentDemandService.update(rentDemand).getMessage());
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
	@RequiresPermissions("rent:rentDemand:remove")
	public R remove( String id){
		try {
			if(rentDemandService.remove(id).getData()){
				return R.ok();
			}else{
				return R.error(rentDemandService.remove(id).getMessage());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return R.error();
		}
	}
	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("rent:rentDemand:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		rentDemandService.batchRemove(ids);
//		return R.ok();
//	}
//	
}
