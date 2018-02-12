package com.ddf.reference.dic;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.dto.Label;
import com.ddf.entity.dic.eo.LabelType;
import com.ddf.entity.dic.query.LabelQuery;
import com.ddf.entity.dic.vo.LabelVo;
import com.ddf.entity.response.ApiResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "service-dic", fallback = LabelReferenceFallback.class)
public interface LabelReference {
    //查询单个Label
    @RequestMapping(value = "/label/query", method = {RequestMethod.GET})
    ApiResponse<Label> query(@RequestParam(value = "id") String id);

    //创建Label
    @RequestMapping(value = "/label/create", method = {RequestMethod.POST})
    ApiResponse<Boolean> create(@RequestBody Label label);

    //修改Label信息
    @RequestMapping(value = "/label/modify", method = {RequestMethod.POST})
    ApiResponse<Boolean> modify(@RequestBody Label label);

    //删除Label信息
    @RequestMapping(value = "/label/remove", method = {RequestMethod.POST})
    ApiResponse<Boolean> remove(@RequestParam(value = "id") String id);

    //按标签类型 查询
    @RequestMapping(value = "/label/type/batchquery", method = {RequestMethod.GET})
    ApiResponse<List<LabelVo>> query4type(@RequestParam(value = "type") LabelType type);

    //分布查询
    @RequestMapping(value = "/label/pagequery", method = {RequestMethod.POST})
    ApiResponse<Page<LabelVo>> pagequery(LabelQuery labelQuery);
}
