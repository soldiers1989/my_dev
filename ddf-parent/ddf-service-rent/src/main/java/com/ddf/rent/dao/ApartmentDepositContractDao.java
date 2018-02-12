package com.ddf.rent.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.ApartmentDepositContractQuery;
import com.ddf.entity.rent.vo.ApartmentDepositContractVo;
import com.ddf.entity.rent.dto.ApartmentDepositContract;

/**
 * apartment_deposit_contract DAO接口
 * @author robot
 * @version 2018-02-02
 */
public interface ApartmentDepositContractDao extends CrudDao<ApartmentDepositContract,ApartmentDepositContractVo,ApartmentDepositContractQuery> {
	
}