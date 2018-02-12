package com.ddf.capital.service.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.CapCapitalAccount;
import com.ddf.entity.capital.query.CapCapitalAccountQuery;
import com.ddf.entity.capital.vo.CapCapitalAccountVo;
import com.ddf.capital.dao.CapCapitalAccountDao;

/**
 * cap_capital_account Service
 * @author robot
 * @version 2018-01-10
 */
@Service
public class CapCapitalAccountService extends CrudServiceImpl<CapCapitalAccount,CapCapitalAccountVo,CapCapitalAccountQuery>{
	@Autowired
	private CapCapitalAccountDao capCapitalAccountDao;
}