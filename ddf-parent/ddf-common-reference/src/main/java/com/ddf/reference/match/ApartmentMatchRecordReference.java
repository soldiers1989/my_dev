package com.ddf.reference.match;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.rent.match.dto.ApartmentMatchRecord;
import com.ddf.entity.rent.match.query.ApartmentMatchRecordQuery;
import com.ddf.entity.rent.match.vo.ApartmentMatchRecordVo;
import com.ddf.entity.response.ApiResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "service-rent-match", fallback = ApartmentMatchRecordReferenceFallback.class)
public interface ApartmentMatchRecordReference {

    //查询单个HouseMatchRecord")
    @RequestMapping(value = "/apartmentMatchRecord/query",method = {RequestMethod.GET})
    public ApiResponse<ApartmentMatchRecord> query(@RequestParam(value = "id") String id);

    //创建HouseMatchRecord")
    @RequestMapping(value = "/apartmentMatchRecord/create",method = {RequestMethod.POST})
    public ApiResponse<Boolean> create(ApartmentMatchRecord houseMatchRecord);

    //修改HouseMatchRecord信息")
    @RequestMapping(value = "/apartmentMatchRecord/modify",method = {RequestMethod.POST})
    public ApiResponse<Boolean> modify(ApartmentMatchRecord houseMatchRecord);

    //删除HouseMatchRecord信息")
    @RequestMapping(value = "/apartmentMatchRecord/remove",method = {RequestMethod.POST})
    public ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);

    /***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
    //查询符合租房需求的HouseMatchRecord列表")
    @RequestMapping(value = "/apartmentMatchRecord/rentDemand/pagequery",method = {RequestMethod.POST})
    public ApiResponse<Page<ApartmentMatchRecordVo>> pagequery(ApartmentMatchRecordQuery query);

    //隐藏HouseMatchRecord信息")
    @RequestMapping(value = "/apartmentMatchRecord/hide",method = {RequestMethod.POST})
    public ApiResponse<Boolean> hide(@RequestParam(value = "id") String id);
}
