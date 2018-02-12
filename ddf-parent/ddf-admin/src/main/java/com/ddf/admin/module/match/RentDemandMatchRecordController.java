package com.ddf.admin.module.match;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.Query;
import com.ddf.admin.common.utils.R;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.vo.XiaoquVo;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.member.vo.UserVo;
import com.ddf.entity.rent.dto.RentDemand;
import com.ddf.entity.rent.match.query.RentDemandMatchRecordQuery;
import com.ddf.entity.rent.match.vo.RentDemandMatchRecordVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.match.RentDemandMatchRecordReference;
import com.ddf.reference.member.UserReference;
import com.ddf.reference.rent.DemandReference;
import com.ddf.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
*
 * 匹配到的需求
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-03 17:14:30
*/


 
@Controller
@RequestMapping("/match/rentDemandMatchRecord")
public class RentDemandMatchRecordController {

	@Autowired
	private RentDemandMatchRecordReference rentDemandMatchRecordReference;
	@Autowired
	private UserReference userReference;
	@Autowired
	private DemandReference demandReference;

	@GetMapping()
	@RequiresPermissions("match:rentDemandMatchRecord:rentDemandMatchRecord")
	String RentDemandMatchRecord(Model model){
//		model.addAttribute("rentDemandMatchRecord.rentDemandId",)
		return "match/rentDemandMatchRecord/rentDemandMatchRecord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("match:rentDemandMatchRecord:rentDemandMatchRecord")
	public PageUtils list(RentDemandMatchRecordQuery query){
		query.getRentDemandMatchRecord().setRentDemandId(query !=null ? "": query.getRentDemandId());
		if (query !=null && StringUtil.isNotEmpty(query.getPhone())){
			ApiResponse<UserVo> response = userReference.queryByMobile(query.getPhone());
			if (response !=null && response.getData() !=null){
				query.getRentDemandMatchRecord().setLodgerId(response.getData().getId());
			}
		}
		//查询列表数据
		ApiResponse<Page<RentDemandMatchRecordVo>> response = rentDemandMatchRecordReference.pagequery(query);
		List<RentDemandMatchRecordVo> list = response.getData().getList();
		for (RentDemandMatchRecordVo recordVo : list) {
			String lodgerId = recordVo.getLodgerId();
			String demandId = recordVo.getRentDemandId();
			ApiResponse<RentDemand> query2 = demandReference.query(demandId);
			if (query2 !=null && query2.getData() !=null){
				recordVo.setPubTime(query2.getData().getCreateDate());
			}
			ApiResponse<User> query1 = userReference.query(lodgerId);
			if (query1 !=null && query1.getData() !=null){
				recordVo.setLodger(query1.getData().getNickName()+"-"+query1.getData().getMobile());
			}
			if (StringUtil.isNotEmpty(recordVo.getAreaIds())){
				String areaIds = recordVo.getAreaIds();
				if (areaIds.contains(",")){
					String[] split = areaIds.split("\\,");
					//Todo 等确认 areaIds
				}

			}
			recordVo.setAreaIdsStr("怎么存");

		}
		PageUtils pageUtils = new PageUtils(list, (int) response.getData().getTotalCount());
		return pageUtils;
	}
	@PostMapping( "/forward")
	String forward(@RequestBody RentDemandMatchRecordQuery query){

		return "match/rentDemandMatchRecord/rentDemandMatchRecord";
	}
	/**
	 * 删除
	 * */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("match:rentDemandMatchRecord:remove")
	public R remove( String id){
		if(rentDemandMatchRecordReference.remove(id).getData()){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除*/
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("match:rentDemandMatchRecord:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		for (int i = 0; i < ids.length; i++) {
			if(rentDemandMatchRecordReference.remove(ids[i]).getData()){
				return R.ok();
			}
		}
		return R.error();
	}
	
}
