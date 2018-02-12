package com.ddf.admin.module.member.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.Query;
import com.ddf.admin.common.utils.R;

/**
 * 用户银行卡
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-03 14:27:03
 */
 
@Controller
@RequestMapping("/member/bankCard")
public class BankCardController {
//	@Autowired
//	private BankCardService bankCardService;
	
	@GetMapping()
	@RequiresPermissions("member:bankCard:bankCard")
	String BankCard(){
	    return "member/bankCard/bankCard";
	}
	
//	@ResponseBody
//	@GetMapping("/list")
//	@RequiresPermissions("member:bankCard:bankCard")
//	public PageUtils list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//		List<BankCardDO> bankCardList = bankCardService.list(query);
//		int total = bankCardService.count(query);
//		PageUtils pageUtils = new PageUtils(bankCardList, total);
//		return pageUtils;
//	}
//	
//	@GetMapping("/add")
//	@RequiresPermissions("member:bankCard:add")
//	String add(){
//	    return "member/bankCard/add";
//	}
//
//	@GetMapping("/edit/{id}")
//	@RequiresPermissions("member:bankCard:edit")
//	String edit(@PathVariable("id") String id,Model model){
//		BankCardDO bankCard = bankCardService.get(id);
//		model.addAttribute("bankCard", bankCard);
//	    return "member/bankCard/edit";
//	}
//	
//	/**
//	 * 保存
//	 */
//	@ResponseBody
//	@PostMapping("/save")
//	@RequiresPermissions("member:bankCard:add")
//	public R save( BankCardDO bankCard){
//		if(bankCardService.save(bankCard)>0){
//			return R.ok();
//		}
//		return R.error();
//	}
//	/**
//	 * 修改
//	 */
//	@ResponseBody
//	@RequestMapping("/update")
//	@RequiresPermissions("member:bankCard:edit")
//	public R update( BankCardDO bankCard){
//		bankCardService.update(bankCard);
//		return R.ok();
//	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/remove")
//	@ResponseBody
//	@RequiresPermissions("member:bankCard:remove")
//	public R remove( String id){
//		if(bankCardService.remove(id)>0){
//		return R.ok();
//		}
//		return R.error();
//	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("member:bankCard:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		bankCardService.batchRemove(ids);
//		return R.ok();
//	}
//	
}
