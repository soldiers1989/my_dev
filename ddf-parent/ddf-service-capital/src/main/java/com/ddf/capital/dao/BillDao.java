package com.ddf.capital.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.query.BillQuery;
import com.ddf.entity.capital.vo.BillVo;
import com.ddf.entity.capital.dto.Bill;

/**
 * bill DAO接口
 * @author robot
 * @version 2018-01-22
 */
public interface BillDao extends CrudDao<Bill,BillVo,BillQuery> {
	
}