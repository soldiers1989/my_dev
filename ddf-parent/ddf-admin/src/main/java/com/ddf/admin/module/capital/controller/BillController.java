package com.ddf.admin.module.capital.controller;

import java.util.ArrayList;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.module.capital.service.BillService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.eo.BillOrderType;
import com.ddf.entity.capital.eo.BillStatus;
import com.ddf.entity.capital.query.BillQuery;
import com.ddf.entity.capital.vo.BillVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.capital.BillReference;
import com.ddf.util.ListUtil;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-31 14:02:30
 */
 
@Controller
@RequestMapping("/system/bill")
public class BillController {
	@Autowired
	private BillService billService;
	@Autowired
	private BillReference billReference;
	
	@GetMapping()
	@RequiresPermissions("system:bill:bill")
	String Bill(Model model){
		model.addAttribute("statusArray", BillStatus.values());
		model.addAttribute("billOrderTypeArray", BillOrderType.values());
	    return "capital/bill/bill";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:bill:bill")
	public PageUtils list(@ModelAttribute BillQuery billQuery){
		ApiResponse<Page<BillVo>> result=billReference.pagequery(billQuery);
		if(result!=null&&result.getResult().equals(ApiResponseResult.SUCCESS.name())) {
			Page<BillVo> page=result.getData();
			if(page!=null&&!ListUtil.isEmpty(page.getList())) {
				PageUtils pageUtils = new PageUtils(page.getList(), (int)page.getTotalCount());
				return pageUtils;
			}
		}
		return new PageUtils(new ArrayList<BillVo>(), 0);
	}
	
	
}
