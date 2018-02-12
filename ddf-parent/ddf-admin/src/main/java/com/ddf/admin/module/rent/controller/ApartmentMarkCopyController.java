package com.ddf.admin.module.rent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 房源收藏表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-07 10:36:03
 */
 
@Controller
@RequestMapping("/rent/apartmentMarkCopy")
public class ApartmentMarkCopyController {
//	@Autowired
//	private ApartmentMarkCopyService apartmentMarkCopyService;
//	
//	@GetMapping()
//	@RequiresPermissions("rent:apartmentMarkCopy:apartmentMarkCopy")
//	String ApartmentMarkCopy(){
//	    return "rent/apartmentMarkCopy/apartmentMarkCopy";
//	}
//	
//	@ResponseBody
//	@GetMapping("/list")
//	@RequiresPermissions("rent:apartmentMarkCopy:apartmentMarkCopy")
//	public PageUtils list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//		List<ApartmentMarkCopyDO> apartmentMarkCopyList = apartmentMarkCopyService.list(query);
//		int total = apartmentMarkCopyService.count(query);
//		PageUtils pageUtils = new PageUtils(apartmentMarkCopyList, total);
//		return pageUtils;
//	}
//	
//	@GetMapping("/add")
//	@RequiresPermissions("rent:apartmentMarkCopy:add")
//	String add(){
//	    return "rent/apartmentMarkCopy/add";
//	}
//
//	@GetMapping("/edit/{id}")
//	@RequiresPermissions("rent:apartmentMarkCopy:edit")
//	String edit(@PathVariable("id") String id,Model model){
//		ApartmentMarkCopyDO apartmentMarkCopy = apartmentMarkCopyService.get(id);
//		model.addAttribute("apartmentMarkCopy", apartmentMarkCopy);
//	    return "rent/apartmentMarkCopy/edit";
//	}
//	
//	/**
//	 * 保存
//	 */
//	@ResponseBody
//	@PostMapping("/save")
//	@RequiresPermissions("rent:apartmentMarkCopy:add")
//	public R save( ApartmentMarkCopyDO apartmentMarkCopy){
//		if(apartmentMarkCopyService.save(apartmentMarkCopy)>0){
//			return R.ok();
//		}
//		return R.error();
//	}
//	/**
//	 * 修改
//	 */
//	@ResponseBody
//	@RequestMapping("/update")
//	@RequiresPermissions("rent:apartmentMarkCopy:edit")
//	public R update( ApartmentMarkCopyDO apartmentMarkCopy){
//		apartmentMarkCopyService.update(apartmentMarkCopy);
//		return R.ok();
//	}
//	
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/remove")
//	@ResponseBody
//	@RequiresPermissions("rent:apartmentMarkCopy:remove")
//	public R remove( String id){
//		if(apartmentMarkCopyService.remove(id)>0){
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
//	@RequiresPermissions("rent:apartmentMarkCopy:batchRemove")
//	public R remove(@RequestParam("ids[]") String[] ids){
//		apartmentMarkCopyService.batchRemove(ids);
//		return R.ok();
//	}
	
}
