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
import com.ddf.admin.module.rent.service.ApartmentAppointmentService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentAppointment;
import com.ddf.entity.rent.eo.ApartmentAppointmentApartmentType;
import com.ddf.entity.rent.query.ApartmentAppointmentQuery;
import com.ddf.entity.rent.vo.ApartmentAppointmentVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 房源预约表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:36:03
 */
 
@Controller
@RequestMapping("/rent/apartmentAppointment")
public class ApartmentAppointmentController {
	@Autowired
	private ApartmentAppointmentService apartmentAppointmentService;
	
	private static Logger logger =	LoggerFactory.getLogger(ApartmentAppointmentController.class);
	
	@GetMapping()
	@RequiresPermissions("rent:apartmentAppointment:apartmentAppointment")
	String ApartmentAppointment(Model model){
		model.addAttribute("ApartmentAppointmentApartmentType", ApartmentAppointmentApartmentType.values());
	    return "rent/apartmentAppointment/apartmentAppointment";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("rent:apartmentAppointment:apartmentAppointment")
	public PageUtils list(ApartmentAppointmentQuery apartmentAppointmentQuery){
		try {
			//查询列表数据
			ApiResponse<Page<ApartmentAppointmentVo>> result = apartmentAppointmentService.list(apartmentAppointmentQuery);
			return new PageUtils(result.getData().getList(), (int)result.getData().getTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new PageUtils(new ArrayList<ApartmentAppointmentVo>(), 0);
		}
	}
	
	@GetMapping("/add")
	@RequiresPermissions("rent:apartmentAppointment:add")
	String add(Model model){
		model.addAttribute("ApartmentAppointmentApartmentType", ApartmentAppointmentApartmentType.values());
	    return "rent/apartmentAppointment/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("rent:apartmentAppointment:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<ApartmentAppointment> apartmentAppointment = apartmentAppointmentService.get(id);
		model.addAttribute("apartmentAppointment", apartmentAppointment.getData());
		model.addAttribute("ApartmentAppointmentApartmentType", ApartmentAppointmentApartmentType.values());
	    return "rent/apartmentAppointment/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("rent:apartmentAppointment:add")
	public R save( ApartmentAppointment apartmentAppointment){
		try {
			if(apartmentAppointmentService.save(apartmentAppointment).getData()){
				return R.ok();
			}else{
				return R.error(apartmentAppointmentService.save(apartmentAppointment).getMessage());
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
	@RequiresPermissions("rent:apartmentAppointment:edit")
	public R update( ApartmentAppointment apartmentAppointment){
		try {
			if(apartmentAppointmentService.update(apartmentAppointment).getData()){
				return R.ok();
			}else{
				return R.error(apartmentAppointmentService.update(apartmentAppointment).getMessage());
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
	@RequiresPermissions("rent:apartmentAppointment:remove")
	public R remove( String id){
		try {
			if(apartmentAppointmentService.remove(id).getData()){
				return R.ok();
			}else{
				return R.error(apartmentAppointmentService.remove(id).getMessage());
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
//	@RequiresPermissions("rent:apartmentAppointment:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		apartmentAppointmentService.batchRemove(ids);
//		return R.ok();
//	}
	
}
