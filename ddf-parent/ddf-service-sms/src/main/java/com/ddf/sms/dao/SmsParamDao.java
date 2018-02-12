package com.ddf.sms.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.sms.query.SmsParamQuery;
import com.ddf.entity.sms.vo.SmsParamVo;
import com.ddf.entity.sms.dto.SmsParam;

/**
 * sms_param DAO接口
 * @author robot
 * @version 2018-01-30
 */
public interface SmsParamDao extends CrudDao<SmsParam,SmsParamVo,SmsParamQuery> {
	
	public SmsParamVo query(SmsParamQuery smsParamQuery);
}