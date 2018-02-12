package com.ddf.message.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.message.query.CallRecordQuery;
import com.ddf.entity.message.vo.CallRecordVo;
import com.ddf.entity.message.dto.CallRecord;

/**
 * call_record DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface CallRecordDao extends CrudDao<CallRecord,CallRecordVo,CallRecordQuery> {
	
}