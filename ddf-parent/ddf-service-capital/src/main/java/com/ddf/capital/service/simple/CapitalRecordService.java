package com.ddf.capital.service.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.CapitalRecord;
import com.ddf.entity.capital.query.CapitalRecordQuery;
import com.ddf.entity.capital.vo.CapitalRecordVo;
import com.ddf.capital.dao.CapitalRecordDao;

/**
 * capital_record Service
 * @author robot
 * @version 2018-01-10
 */
@Service
public class CapitalRecordService extends CrudServiceImpl<CapitalRecord,CapitalRecordVo,CapitalRecordQuery>{
	@Autowired
	private CapitalRecordDao capitalRecordDao;
}