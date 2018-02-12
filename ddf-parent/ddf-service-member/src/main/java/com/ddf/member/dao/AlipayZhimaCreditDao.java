package com.ddf.member.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.member.query.AlipayZhimaCreditQuery;
import com.ddf.entity.member.vo.AlipayZhimaCreditVo;
import com.ddf.entity.member.dto.AlipayZhimaCredit;

/**
 * alipay_zhima_credit DAO接口
 * @author robot
 * @version 2018-01-15
 */
public interface AlipayZhimaCreditDao extends CrudDao<AlipayZhimaCredit,AlipayZhimaCreditVo,AlipayZhimaCreditQuery> {
	
}