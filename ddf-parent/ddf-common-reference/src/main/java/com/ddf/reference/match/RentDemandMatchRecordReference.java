package com.ddf.reference.match;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.match.dto.RentDemandMatchRecord;
import com.ddf.entity.rent.match.query.RentDemandMatchRecordQuery;
import com.ddf.entity.rent.match.vo.RentDemandMatchRecordVo;
import com.ddf.entity.response.ApiResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "service-rent-match"/*, fallback = RentDemandMatchRecordReferenceFallback.class*/)
public interface RentDemandMatchRecordReference {

    //查询单个RentDemandMatchRecord
    @RequestMapping(value = "/rentDemandMatchRecord/query", method = {RequestMethod.GET})
    public ApiResponse<RentDemandMatchRecord> query(@RequestParam(value = "id") String id);

    //创建RentDemandMatchRecord")
    @RequestMapping(value = "/rentDemandMatchRecord/create", method = {RequestMethod.POST})
    public ApiResponse<Boolean> create(RentDemandMatchRecord rentDemandMatchRecord);

    //修改RentDemandMatchRecord信息")
    @RequestMapping(value = "/rentDemandMatchRecord/modify", method = {RequestMethod.POST})
    public ApiResponse<Boolean> modify(RentDemandMatchRecord rentDemandMatchRecord);

    //删除RentDemandMatchRecord信息")
    @RequestMapping(value = "/rentDemandMatchRecord/remove", method = {RequestMethod.POST})
    public ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);

    /***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
    //查询符房子的的RentDemandMatchRecord列表")
    @RequestMapping(value = "/rentDemandMatchRecord/house/pagequery", method = {RequestMethod.POST})
    public ApiResponse<Page<RentDemandMatchRecordVo>> pagequery(RentDemandMatchRecordQuery query);

    //隐藏RentDemandMatchRecord信息")
    @RequestMapping(value = "/rentDemandMatchRecord/hide", method = {RequestMethod.POST})
    public ApiResponse<Boolean> hide(@RequestParam(value = "id") String id);

    //查询符合房子的的RentDemandMatchRecord列表，以租客聚合")
    @RequestMapping(value = "/rentDemandMatchRecord/house/groupbylodgerId/pagequery", method = {RequestMethod.GET})
    public ApiResponse<Page<RentDemandMatchRecordVo>> query4houseId4groupbylodgerId(@RequestParam(value = "pageNum") int pageNum,
                                                                                    @RequestParam(value = "pageSize") int pageSize,
                                                                                    @RequestParam(value = "houseId") String houseId);

    //隐藏RentDemandMatchRecord信息，按租客隐藏")
    @RequestMapping(value = "/rentDemandMatchRecord/lodger/hide", method = {RequestMethod.GET})
    public ApiResponse<Boolean> hide4lodgerId(@RequestParam(value = "lodgerId") String lodgerId);
}
