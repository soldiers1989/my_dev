package com.ddf.capital.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.query.DepositOrderQuery;
import com.ddf.entity.capital.vo.DepositOrderVo;
import com.ddf.entity.capital.dto.DepositOrder;

/**
 * deposit_order DAO接口
 * @author robot
 * @version 2018-01-22
 */
public interface DepositOrderDao extends CrudDao<DepositOrder,DepositOrderVo,DepositOrderQuery> {
}