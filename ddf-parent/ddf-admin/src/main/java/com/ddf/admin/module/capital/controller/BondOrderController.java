package com.ddf.admin.module.capital.controller;

import java.util.ArrayList;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.R;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.BondOrder;
import com.ddf.entity.capital.eo.BondOrderStatus;
import com.ddf.entity.capital.query.BondOrderQuery;
import com.ddf.entity.capital.vo.BondOrderVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.capital.BondOrderReference;
import com.ddf.reference.common.DateReference;
import com.ddf.util.ListUtil;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-08 15:05:59
 */
 
@Controller
@RequestMapping("/capital/bondOrder")
public class BondOrderController {
	@Autowired
	private BondOrderReference bondOrderReference;
	
	@Autowired
	private DateReference dateReference;
	
	@GetMapping()
	@RequiresPermissions("capital:bondOrder:bondOrder")
	String BondOrder(){
	    return "capital/bondOrder/bondOrder";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("capital:bondOrder:bondOrder")
	public PageUtils list(@ModelAttribute BondOrderQuery bondOrderQuery){
		//查询列表数据
		ApiResponse<Page<BondOrderVo>> result=bondOrderReference.pagequery(bondOrderQuery);
		if(result!=null&&result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<BondOrderVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				PageUtils pageUtils = new PageUtils(page.getList(), (int)page.getTotalCount());
				return pageUtils;
			}
		}
		return new PageUtils(new ArrayList<BondOrderVo>(), 0);
	}
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("capital:bondOrder:edit")
	String edit(@PathVariable("id") String id,Model model){
		BondOrder bondOrder=bondOrderReference.query(id).getData();
		model.addAttribute("bondOrder", bondOrder);
	    return "capital/bondOrder/edit";
	}
	
	/**
	 * 违约
	 */
	@ResponseBody
	@RequestMapping("/breach")
	@RequiresPermissions("capital:bondOrder:edit")
	public R update( @RequestParam("id")String id){
		BondOrder bondOrder=bondOrderReference.query(id).getData();
		bondOrder.setStatus(BondOrderStatus.breach);
		bondOrder.setUpdateDate(dateReference.queryCurrentDate().getData());
		ApiResponse<Boolean> result=bondOrderReference.modify(bondOrder);
		if(result==null||result.getResult().equals(ApiResponseResult.ERROR.name())) {
			return R.error("系统异常");
		}else if(result.getResult().equals(ApiResponseResult.BUSS_ERROR.name())) {
			return R.error(result.getMessage());
		}else {
			return R.ok();
		}
	}
	
	
}
