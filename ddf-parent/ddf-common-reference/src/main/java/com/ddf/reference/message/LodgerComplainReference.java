package com.ddf.reference.message;

import io.swagger.annotations.ApiOperation;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.message.dto.LodgerComplain;
import com.ddf.entity.message.query.LodgerComplainQuery;
import com.ddf.entity.message.vo.LodgerComplainVo;
import com.ddf.entity.response.ApiResponse;

@FeignClient(value = "service-message"/*,fallback = LodgerComplainReferenceFallback.class*/)
public interface LodgerComplainReference {

	@ApiOperation(value="查询单个 举报房客")
	@RequestMapping(value = "/lodgerComplain/query",method = {RequestMethod.GET})
	public ApiResponse<LodgerComplain> query(@RequestParam(value = "id") String id);

	@ApiOperation(value="创建 举报房客")
	@RequestMapping(value = "/lodgerComplain/create",method = {RequestMethod.POST})
	public ApiResponse<Boolean> create(LodgerComplain lodgerComplain);

	@ApiOperation(value="修改 举报房客 信息")
	@RequestMapping(value = "/lodgerComplain/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(LodgerComplain lodgerComplain);

	@ApiOperation(value="删除 举报房客 信息")
	@RequestMapping(value = "/lodgerComplain/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@RequestParam(value = "id")String id);

	/***↑↑↑↑↑↑↑↑↑↑↑基础接口↑↑↑↑↑↑↑↑↑↓↓↓↓↓↓↓↓↓扩展接口↓↓↓↓↓↓↓↓↓↓↓↓↓↓************/
	@ApiOperation(value="按房客查询 举报房客 列表")
	@RequestMapping(value = "/lodgerComplain/lodger/batchquery",method = {RequestMethod.GET})
	public ApiResponse<Page<LodgerComplainVo>> query4lodgerId(
			@RequestParam(value = "id") String lodgerId,
			@RequestParam(value = "id") int pageNum,
			@RequestParam(value = "id") int pageSize);
	
	@ApiOperation(value="按  举报房客对象 查询 举报房客 列表")
	@RequestMapping(value = "/lodgerComplain/batchquery",method = {RequestMethod.POST})
	public ApiResponse<Page<LodgerComplainVo>> batchquery(LodgerComplainQuery lodgerComplainQuery);
}
