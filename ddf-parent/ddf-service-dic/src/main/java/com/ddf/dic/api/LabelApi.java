/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.dic.api;

import com.ddf.dic.service.simple.LabelService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.dto.Label;
import com.ddf.entity.dic.eo.LabelType;
import com.ddf.entity.dic.query.LabelQuery;
import com.ddf.entity.dic.vo.LabelVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * label Api
 *
 * @author robot
 * @version 2018-01-16
 */
@Api(value = "LabelApi", tags = "标签")
@RestController
public class LabelApi extends BaseApi {

    @Autowired
    private LabelService labelService;

    @ApiOperation(value = "查询单个Label")
    @RequestMapping(value = "/label/query", method = {RequestMethod.GET})
    public ApiResponse<Label> query(@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
        try {
            return ApiResponse.success(labelService.query4id(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.fail(ApiResponseResult.ERROR);
        }
    }

    @ApiOperation(value = "创建Label")
    @RequestMapping(value = "/label/create", method = {RequestMethod.POST})
    public ApiResponse<Boolean> create(@RequestBody Label label) {
        try {
            return ApiResponse.success(labelService.create(label));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.fail(ApiResponseResult.ERROR);
        }
    }

    @ApiOperation(value = "修改Label信息")
    @RequestMapping(value = "/label/modify", method = {RequestMethod.POST})
    public ApiResponse<Boolean> modify(@RequestBody Label label) {
        try {
            return ApiResponse.success(labelService.modify(label));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.fail(ApiResponseResult.ERROR);
        }
    }

    @ApiOperation(value = "删除Label信息")
    @RequestMapping(value = "/label/remove", method = {RequestMethod.POST})
    public ApiResponse<Boolean> remove(@ApiParam(value = "id", required = true) @RequestParam(required = true) String id) {
        try {
            return ApiResponse.success(labelService.remove(id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.fail(ApiResponseResult.ERROR);
        }
    }

    /***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
    @ApiOperation(value = "按标签类型 查询")
    @RequestMapping(value = "/label/type/batchquery", method = {RequestMethod.GET})
    public ApiResponse<List<LabelVo>> query4type(@ApiParam(value = "标签类型 house(房源）,house_facility(房源设施)," +
            "house_by_opinion(评论房源),landlord_by_opinion(评论房东),lodger_by_opinion(评论租客),life_style(生活方式)," +
            "about_individuals(关于个人),hobby(兴趣爱好),tenant_requirements(租客要求)", required = true) @RequestParam("type") LabelType type) {
        List<LabelVo> labelVos = null;
        try {
            labelVos = labelService.findListByType(type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.fail(ApiResponseResult.ERROR);
        }
        return ApiResponse.success(labelVos);
    }
    @ApiOperation(value = "分页查询")
    @RequestMapping(value = "/label/pagequery", method = {RequestMethod.POST})
    public ApiResponse<Page<LabelVo>> pagequery(@RequestBody LabelQuery labelQuery) {
        try {
             return ApiResponse.success(labelService.query4pagehelper(labelQuery));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.fail(ApiResponseResult.ERROR);
        }
    }
}