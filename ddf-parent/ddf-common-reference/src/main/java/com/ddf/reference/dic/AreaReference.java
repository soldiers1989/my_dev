package com.ddf.reference.dic;

import com.ddf.entity.dic.dto.Area;
import com.ddf.entity.dic.eo.AreaType;
import com.ddf.entity.dic.vo.AreaVo;
import com.ddf.entity.response.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "service-dic", fallback = AreaReferenceFallback.class)
public interface AreaReference {

    //查询单个Area")
    @RequestMapping(value = "/area/query",method = {RequestMethod.GET})
    public ApiResponse<Area> query(@RequestParam(value = "id") String id);
    //查询Area子列表
    @RequestMapping(value = "/area/child/batchquery", method = {RequestMethod.GET})
    ApiResponse<List<AreaVo>> queryChilds(@RequestParam(value = "parentId") String parentId);

    //按类型 查询Area列表
    @RequestMapping(value = "/area/list/batchquery", method = {RequestMethod.GET})
    ApiResponse<List<AreaVo>> queryListByType(@RequestParam(value = "type") AreaType type);
}
