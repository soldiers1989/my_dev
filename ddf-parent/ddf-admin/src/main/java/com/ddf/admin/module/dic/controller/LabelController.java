package com.ddf.admin.module.dic.controller;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.R;
import com.ddf.admin.module.dic.service.LabelService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.dto.Label;
import com.ddf.entity.dic.eo.LabelType;
import com.ddf.entity.dic.query.LabelQuery;
import com.ddf.entity.dic.vo.LabelVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.dic.LabelReference;
import com.ddf.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 房屋标签
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-02 15:01:23
 */
 
@Controller
@RequestMapping("/dic/label")
public class LabelController {
	@Autowired
	private LabelService labelService;
	@Autowired
	private LabelReference labelReference;
	@GetMapping()
	@RequiresPermissions("dic:label:label")
	String Label(Model model){
		model.addAttribute("labelList",LabelType.values());
	    return "dic/label/label";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("dic:label:label")
	public PageUtils list(@ModelAttribute LabelQuery query){
		//查询列表数据
		ApiResponse<Page<LabelVo>> response = labelReference.pagequery(query);
		Page<LabelVo> data  = response.getData();
		PageUtils pageUtils = new PageUtils(data.getList(), (int) data.getTotalCount());
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("dic:label:add")
	String add(Model model){
		model.addAttribute("labelList",LabelType.values());
	    return "dic/label/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("dic:label:edit")
	String edit(@PathVariable("id") String id,Model model){
		model.addAttribute("labelList",LabelType.values());
		model.addAttribute("label", labelReference.query(id).getData());
	    return "dic/label/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("dic:label:add")
	public R save( Label label){
		String result = labelReference.create(label).getResult();
		if(StringUtil.isNotEmpty(result)){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("dic:label:edit")
	public R update( Label label){
		if(labelReference.modify(label).getData()){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("dic:label:remove")
	public R remove( String id){
		if(labelReference.remove(id).getData()){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("dic:label:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		for (int i = 0; i < ids.length; i++) {
			if(labelReference.remove(ids[i]).getData()){
				return R.ok();
			}
		}
		return R.error();
	}
	
}
