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
import com.ddf.admin.module.rent.service.ShareHouseService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ShareHouse;
import com.ddf.entity.rent.eo.ShareHouseOwnType;
import com.ddf.entity.rent.eo.ShareHouseStatus;
import com.ddf.entity.rent.query.ShareHouseQuery;
import com.ddf.entity.rent.vo.ShareHouseVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 合租房源表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:39:26
 */
 
@Controller
@RequestMapping("/rent/shareHouse")
public class ShareHouseController {
	
	@Autowired
	private ShareHouseService shareHouseService;
	private static Logger logger =	LoggerFactory.getLogger(ShareHouseController.class);
	
	
	@GetMapping()
	@RequiresPermissions("rent:shareHouse:shareHouse")
	String ShareHouse(Model model){
		model.addAttribute("ShareHouseOwnType", ShareHouseOwnType.values());
		model.addAttribute("ShareHouseStatus", ShareHouseStatus.values());
	    return "rent/shareHouse/shareHouse";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("rent:shareHouse:shareHouse")
	public PageUtils list(ShareHouseQuery shareHouseQuery){
		try {
			//查询列表数据
			ApiResponse<Page<ShareHouseVo>> result = shareHouseService.list(shareHouseQuery);
			return new PageUtils(result.getData().getList(), (int)result.getData().getTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new PageUtils(new ArrayList<ShareHouseVo>(), 0);
		}
	}
	
	@GetMapping("/add")
	@RequiresPermissions("rent:shareHouse:add")
	String add(Model model){
		model.addAttribute("ShareHouseOwnType", ShareHouseOwnType.values());
		model.addAttribute("ShareHouseStatus", ShareHouseStatus.values());
	    return "rent/shareHouse/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("rent:shareHouse:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<ShareHouse> shareHouse = shareHouseService.get(id);
		model.addAttribute("shareHouse", shareHouse.getData());
		model.addAttribute("ShareHouseOwnType", ShareHouseOwnType.values());
		model.addAttribute("ShareHouseStatus", ShareHouseStatus.values());
	    return "rent/shareHouse/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("rent:shareHouse:add")
	public R save( ShareHouse shareHouse){
		try {
			if(shareHouseService.save(shareHouse).getData()){
				return R.ok();
			}else{
				return R.error(shareHouseService.save(shareHouse).getMessage());
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
	@RequiresPermissions("rent:shareHouse:edit")
	public R update( ShareHouse shareHouse){
		try {
			if(shareHouseService.update(shareHouse).getData()){
				return R.ok();
			}else{
				return R.error(shareHouseService.update(shareHouse).getMessage());
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
	@RequiresPermissions("rent:shareHouse:remove")
	public R remove( String id){
		try {
			if(shareHouseService.remove(id).getData()){
				return R.ok();
			}else{
				return R.error(shareHouseService.remove(id).getMessage());
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
//	@RequiresPermissions("rent:shareHouse:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		shareHouseService.batchRemove(ids);
//		return R.ok();
//	}
	
}
