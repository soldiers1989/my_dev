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
import com.ddf.admin.module.rent.service.ShareApartmentService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.dto.ShareApartment;
import com.ddf.entity.rent.eo.ShareApartmentBedroomType;
import com.ddf.entity.rent.eo.ShareApartmentChaoxiang;
import com.ddf.entity.rent.eo.ShareApartmentDepositStatus;
import com.ddf.entity.rent.eo.ShareApartmentMatchStatus;
import com.ddf.entity.rent.eo.ShareApartmentStatus;
import com.ddf.entity.rent.eo.ShareApartmentZhuangxiu;
import com.ddf.entity.rent.query.ShareApartmentQuery;
import com.ddf.entity.rent.vo.ShareApartmentVo;
import com.ddf.entity.response.ApiResponse;

/**
 * 合租房间表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:36:03
 */
 
@Controller
@RequestMapping("/rent/shareApartment")
public class ShareApartmentController {
	@Autowired
	private ShareApartmentService shareApartmentService;
	private static Logger logger =	LoggerFactory.getLogger(ShareApartmentController.class);
	
	@GetMapping()
	@RequiresPermissions("rent:shareApartment:shareApartment")
	String ShareApartment(Model model){
		model.addAttribute("ShareApartmentBedroomType", ShareApartmentBedroomType.values());
		model.addAttribute("ShareApartmentZhuangxiu", ShareApartmentZhuangxiu.values());
		model.addAttribute("ShareApartmentChaoxiang", ShareApartmentChaoxiang.values());
		model.addAttribute("ShareApartmentMatchStatus", ShareApartmentMatchStatus.values());
		model.addAttribute("ShareApartmentDepositStatus", ShareApartmentDepositStatus.values());
		model.addAttribute("ShareApartmentStatus", ShareApartmentStatus.values());
	    return "rent/shareApartment/shareApartment";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("rent:shareApartment:shareApartment")
	public PageUtils list(ShareApartmentQuery shareApartmentQuery){
		try {
			//查询列表数据
			ApiResponse<Page<ShareApartmentVo>> result = shareApartmentService.list(shareApartmentQuery);
			return new PageUtils(result.getData().getList(), (int)result.getData().getTotalCount());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new PageUtils(new ArrayList<ShareApartmentVo>(), 0);
		}
	}
	
	@GetMapping("/add")
	@RequiresPermissions("rent:shareApartment:add")
	String add(Model model){
		model.addAttribute("ShareApartmentBedroomType", ShareApartmentBedroomType.values());
		model.addAttribute("ShareApartmentZhuangxiu", ShareApartmentZhuangxiu.values());
		model.addAttribute("ShareApartmentChaoxiang", ShareApartmentChaoxiang.values());
		model.addAttribute("ShareApartmentMatchStatus", ShareApartmentMatchStatus.values());
		model.addAttribute("ShareApartmentDepositStatus", ShareApartmentDepositStatus.values());
		model.addAttribute("ShareApartmentStatus", ShareApartmentStatus.values());
	    return "rent/shareApartment/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("rent:shareApartment:edit")
	String edit(@PathVariable("id") String id,Model model){
		ApiResponse<ShareApartment> shareApartment = shareApartmentService.get(id);
		model.addAttribute("shareApartment", shareApartment.getData());
		model.addAttribute("ShareApartmentBedroomType", ShareApartmentBedroomType.values());
		model.addAttribute("ShareApartmentZhuangxiu", ShareApartmentZhuangxiu.values());
		model.addAttribute("ShareApartmentChaoxiang", ShareApartmentChaoxiang.values());
		model.addAttribute("ShareApartmentMatchStatus", ShareApartmentMatchStatus.values());
		model.addAttribute("ShareApartmentDepositStatus", ShareApartmentDepositStatus.values());
		model.addAttribute("ShareApartmentStatus", ShareApartmentStatus.values());
	    return "rent/shareApartment/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("rent:shareApartment:add")
	public R save( ShareApartment shareApartment){
		try {
			if(shareApartmentService.save(shareApartment).getData()){
				return R.ok();
			}else{
				return R.error(shareApartmentService.save(shareApartment).getMessage());
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
	@RequiresPermissions("rent:shareApartment:edit")
	public R update( ShareApartment shareApartment){
		try {
			if(shareApartmentService.update(shareApartment).getData()){
				return R.ok();
			}else{
				return R.error(shareApartmentService.update(shareApartment).getMessage());
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
	@RequiresPermissions("rent:shareApartment:remove")
	public R remove( String id){
		try {
			if(shareApartmentService.remove(id).getData()){
				return R.ok();
			}else{
				return R.error(shareApartmentService.remove(id).getMessage());
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
//	@RequiresPermissions("rent:shareApartment:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		shareApartmentService.batchRemove(ids);
//		return R.ok();
//	}
	
}
