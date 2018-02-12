package com.ddf.reference.dic;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.dto.Xiaoqu;
import com.ddf.entity.dic.query.XiaoquQuery;
import com.ddf.entity.dic.vo.XiaoquVo;
import com.ddf.entity.response.ApiResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "service-dic", fallback = XiaoquReferenceFallback.class)
public interface XiaoquReference {

    //查询单个Xiaoqu
    @RequestMapping(value = "/xiaoqu/query", method = {RequestMethod.GET})
    ApiResponse<Xiaoqu> query(@RequestParam(value = "id") String id);

    //创建Xiaoqu
    @RequestMapping(value = "/xiaoqu/create", method = {RequestMethod.POST})
    ApiResponse<Boolean> create(@RequestBody Xiaoqu xiaoqu);

    //修改Xiaoqu信息
    @RequestMapping(value = "/xiaoqu/modify", method = {RequestMethod.POST})
    ApiResponse<Boolean> modify(@RequestBody Xiaoqu xiaoqu);

    //删除Xiaoqu信息
    @RequestMapping(value = "/xiaoqu/remove", method = {RequestMethod.POST})
    ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);

    //按条件 查询Xiaoqu信息
    @RequestMapping(value = "/xiaoqu/pageQuery", method = {RequestMethod.POST})
    ApiResponse<Page<XiaoquVo>> pageQuery(XiaoquQuery xiaoquQuery);

    //通过经纬度 查到分页小区
    @RequestMapping(value = "/xiaoqu/queryXiaoquByLngLat", method = {RequestMethod.GET})
    ApiResponse<List<XiaoquVo>> queryXiaoquByLngLat(@RequestParam(value = "pageNum") Integer pageNum,
                                                    @RequestParam(value = "pageSize") Integer pageSize,
                                                    @RequestParam(value = "lng") String lng,
                                                    @RequestParam(value = "lat") String lat);

    //查询小区总数")
    @RequestMapping(value = "/xiaoqu/queryXiaoquNum", method = {RequestMethod.GET})
    ApiResponse<Long> queryXiaoquNum();

    //模糊查询 分页小区
    @RequestMapping(value = "/xiaoqu/pageLikeQuery", method = {RequestMethod.GET})
    ApiResponse<List<XiaoquVo>> query4where(@RequestParam(value = "pageNum") Integer pageNum,
                                            @RequestParam(value = "pageSize") Integer pageSize,
                                            @RequestParam(value = "name") String name,
                                            @RequestParam(value = "address") String address);
}
