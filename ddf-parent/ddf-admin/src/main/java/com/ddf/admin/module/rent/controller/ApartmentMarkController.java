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
import com.ddf.admin.module.rent.service.ApartmentMarkService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ApartmentMark;
import com.ddf.entity.rent.eo.ApartmentMarkApartmentType;
import com.ddf.entity.rent.query.ApartmentMarkQuery;
import com.ddf.entity.rent.vo.ApartmentMarkVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 房源收藏表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:36:03
 */
 
@Controller
@RequestMapping("/rent/apartmentMark")
public class ApartmentMarkController {
	
	@Autowired
	private ApartmentMarkService apartmentMarkService;
	
	private static Logger logger =	LoggerFactory.getLogger(ApartmentMarkController.class);
	
	@GetMapping()
	@RequiresPermissions("rent:apartmentMark:apartmentMark")
	String ApartmentMark(Model model){
		model.addAttribute("ApartmentMarkApartmentType", ApartmentMarkApartmentType.values());
	    return "rent/apartmentMark/apartmentMark";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("rent:apartmentMark:apartmentMark")
	public PageUtils list(ApartmentMarkQuery apartmentMarkQuery){
		try {
			//查询列表数据
			ApiResponse<Page<ApartmentMarkVo>> result = apartmentMarkService.list(apartmentMarkQuery);
			return new PageUtils(result.getData().getList(), (int)result.getData().getTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new PageUtils(new ArrayList<ApartmentMarkVo>(), 0);
		}
	}
	
	@GetMapping("/add")
	@RequiresPermissions("rent:apartmentMark:add")
	String add(Model model){
		model.addAttribute("ApartmentMarkApartmentType", ApartmentMarkApartmentType.values());
	    return "rent/apartmentMark/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("rent:apartmentMark:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<ApartmentMark> apartmentMark = apartmentMarkService.get(id);
		model.addAttribute("apartmentMark", apartmentMark.getData());
		model.addAttribute("ApartmentMarkApartmentType", ApartmentMarkApartmentType.values());
	    return "rent/apartmentMark/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("rent:apartmentMark:add")
	public R save( ApartmentMark apartmentMark){
		try {
			if(apartmentMarkService.save(apartmentMark).getData()){
				return R.ok();
			}else{
				return R.error(apartmentMarkService.save(apartmentMark).getMessage());
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
	@RequiresPermissions("rent:apartmentMark:edit")
	public R update( ApartmentMark apartmentMark){
		try {
			if(apartmentMarkService.update(apartmentMark).getData()){
				return R.ok();
			}else{
				return R.error(apartmentMarkService.update(apartmentMark).getMessage());
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
	@RequiresPermissions("rent:apartmentMark:remove")
	public R remove( String id){
		try {
			if(apartmentMarkService.remove(id).getData()){
				return R.ok();
			}else{
				return R.error(apartmentMarkService.remove(id).getMessage());
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
//	@RequiresPermissions("rent:apartmentMark:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		apartmentMarkService.batchRemove(ids);
//		return R.ok();
//	}
	
}
