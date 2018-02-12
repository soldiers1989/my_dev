package com.ddf.rent.service.simple;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.github.pagehelper.PageHelper;
import com.ddf.entity.rent.dto.RentParam;
import com.ddf.entity.rent.query.RentParamQuery;
import com.ddf.entity.rent.vo.RentParamVo;
import com.ddf.rent.dao.RentParamDao;

/**
 * rent_param Service
 * @author robot
 * @version 2018-01-16
 */
@Service
public class RentParamService extends CrudServiceImpl<RentParam,RentParamVo,RentParamQuery>{
	@Autowired
	private RentParamDao rentParamDao;
	
	public Page<RentParamVo> query4page(int pageNum, int pageSize){
		RentParamQuery rentParamQuery = new RentParamQuery();
		rentParamQuery.buildPageSql(pageNum, pageSize);
		List<RentParamVo> list = this.findList(rentParamQuery);
		long totalCount = this.findCount(rentParamQuery);
		Page<RentParamVo> page = new Page<RentParamVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<RentParamVo> query4pagehelper(int pageNum, int pageSize){
		RentParamQuery rentParamQuery = new RentParamQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<RentParamVo> list = this.findList(rentParamQuery);
        Page<RentParamVo> page = new Page<RentParamVo>(list);
        return page;
	}
}