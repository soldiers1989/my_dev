package com.ddf.common.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.response.ApiResponse;
import com.ddf.util.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "IdApi", tags = "统一ID的API" )
@RestController
public class IdApi extends BaseApi{

	private Logger logger =Logger.getLogger(IdApi.class);
	
	@ApiOperation(value="获取统一ID")
	@RequestMapping(value = "/id/create",method = {RequestMethod.GET})
	public ApiResponse<String> createId(@ApiParam(value = "tableName",required = true) @RequestParam(required = true) TableName tableName){
		return ApiResponse.success(getUniqueString(tableName));
	}
	
	
	
	private static Map<String,Integer> map = new HashMap<String,Integer>();
	public static synchronized String getUniqueString(TableName tableName){
		Integer currentCount = map.get(tableName.toString());
		if(currentCount==null){
			currentCount = 0;
		}
		if(currentCount > 99999){
			currentCount = 0;
		}
		SimpleDateFormat simple = new SimpleDateFormat("yyMMddHHmm");
		String dateStr = simple.format(new Date());
		String suppleNum = StringUtil.replenishLength(currentCount.toString(),4);
		String uniqueNumber = dateStr + suppleNum;
		
		int nextCount = currentCount.intValue();
		nextCount = nextCount + 1;
		map.put(tableName.toString(), Integer.valueOf(nextCount));
		return tableName.getShortName()+uniqueNumber;
    }
    
}
