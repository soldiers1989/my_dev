package com.ddf.capital.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.query.CapitalRecordQuery;
import com.ddf.entity.capital.vo.CapitalRecordVo;
import com.ddf.entity.capital.dto.CapitalRecord;

/**
 * capital_record DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface CapitalRecordDao extends CrudDao<CapitalRecord,CapitalRecordVo,CapitalRecordQuery> {
	
}