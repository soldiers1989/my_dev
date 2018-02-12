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
import com.ddf.admin.module.rent.service.ApartmentService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.Apartment;
import com.ddf.entity.rent.eo.ApartmentChaoxiang;
import com.ddf.entity.rent.eo.ApartmentDepositStatus;
import com.ddf.entity.rent.eo.ApartmentMatchStatus;
import com.ddf.entity.rent.eo.ApartmentOwnType;
import com.ddf.entity.rent.eo.ApartmentStatus;
import com.ddf.entity.rent.eo.ApartmentZhuangxiu;
import com.ddf.entity.rent.query.ApartmentQuery;
import com.ddf.entity.rent.vo.ApartmentVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 整租房源表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:36:03
 */
 
@Controller
@RequestMapping("/rent/apartment")
public class ApartmentController {
	@Autowired
	private ApartmentService apartmentService;
	
	private static Logger logger =	LoggerFactory.getLogger(ApartmentController.class);
	
	@GetMapping()
	@RequiresPermissions("rent:apartment:apartment")
	String Apartment(Model model){
		model.addAttribute("ApartmentOwnType", ApartmentOwnType.values());
		model.addAttribute("ApartmentZhuangxiu", ApartmentZhuangxiu.values());
		model.addAttribute("ApartmentChaoxiang", ApartmentChaoxiang.values());
		model.addAttribute("ApartmentMatchStatus", ApartmentMatchStatus.values());
		model.addAttribute("ApartmentDepositStatus", ApartmentDepositStatus.values());
		model.addAttribute("ApartmentStatus", ApartmentStatus.values());
	    return "rent/apartment/apartment";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("rent:apartment:apartment")
	public PageUtils list(ApartmentQuery apartmentQuery){
		try {
			//查询列表数据
			ApiResponse<Page<ApartmentVo>> result = apartmentService.list(apartmentQuery);
			return new PageUtils(result.getData().getList(), (int)result.getData().getTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new PageUtils(new ArrayList<ApartmentVo>(), 0);
		}		
	}
	
	@GetMapping("/add")
	@RequiresPermissions("rent:apartment:add")
	String add(Model model){
		model.addAttribute("ApartmentOwnType", ApartmentOwnType.values());
		model.addAttribute("ApartmentZhuangxiu", ApartmentZhuangxiu.values());
		model.addAttribute("ApartmentChaoxiang", ApartmentChaoxiang.values());
		model.addAttribute("ApartmentMatchStatus", ApartmentMatchStatus.values());
		model.addAttribute("ApartmentDepositStatus", ApartmentDepositStatus.values());
		model.addAttribute("ApartmentStatus", ApartmentStatus.values());
	    return "rent/apartment/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("rent:apartment:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<Apartment> apartment = apartmentService.get(id);
		model.addAttribute("apartment", apartment.getData());
		model.addAttribute("ApartmentOwnType", ApartmentOwnType.values());
		model.addAttribute("ApartmentZhuangxiu", ApartmentZhuangxiu.values());
		model.addAttribute("ApartmentChaoxiang", ApartmentChaoxiang.values());
		model.addAttribute("ApartmentMatchStatus", ApartmentMatchStatus.values());
		model.addAttribute("ApartmentDepositStatus", ApartmentDepositStatus.values());
		model.addAttribute("ApartmentStatus", ApartmentStatus.values());
	    return "rent/apartment/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("rent:apartment:add")
	public R save( Apartment apartment){
		try {
			if(apartmentService.save(apartment).getData()){
				return R.ok();
			}else{
				return R.error(apartmentService.save(apartment).getMessage());
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
	@RequiresPermissions("rent:apartment:edit")
	public R update( Apartment apartment){
		try {
			if(apartmentService.update(apartment).getData()){
				return R.ok();
			}else{
				return R.error(apartmentService.update(apartment).getMessage());
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
	@RequiresPermissions("rent:apartment:remove")
	public R remove( String id){
		try {
			if(apartmentService.remove(id).getData()){
				return R.ok();
			}else{
				return R.error(apartmentService.remove(id).getMessage());
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
//	@RequiresPermissions("rent:apartment:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		apartmentService.batchRemove(ids);
//		return R.ok();
//	}
	
}
