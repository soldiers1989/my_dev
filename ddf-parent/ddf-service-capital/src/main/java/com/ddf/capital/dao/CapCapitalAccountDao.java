package com.ddf.capital.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.query.CapCapitalAccountQuery;
import com.ddf.entity.capital.vo.CapCapitalAccountVo;
import com.ddf.entity.capital.dto.CapCapitalAccount;

/**
 * cap_capital_account DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface CapCapitalAccountDao extends CrudDao<CapCapitalAccount,CapCapitalAccountVo,CapCapitalAccountQuery> {
	
}