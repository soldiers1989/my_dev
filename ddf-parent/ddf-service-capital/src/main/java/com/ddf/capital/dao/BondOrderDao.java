package com.ddf.capital.dao;



import org.apache.ibatis.annotations.Param;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.dto.BondOrder;
import com.ddf.entity.capital.query.BondOrderQuery;
import com.ddf.entity.capital.vo.BondOrderVo;

/**
 * bond_order DAO接口
 * @author robot
 * @version 2018-01-22
 */
public interface BondOrderDao extends CrudDao<BondOrder,BondOrderVo,BondOrderQuery> {
}