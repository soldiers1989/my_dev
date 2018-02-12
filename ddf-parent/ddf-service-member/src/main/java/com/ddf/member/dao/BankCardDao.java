package com.ddf.member.dao;

import java.util.List;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.member.dto.BankCard;
import com.ddf.entity.member.query.BankCardQuery;
import com.ddf.entity.member.vo.BankCardVo;

/**
 * bank_card DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface BankCardDao extends CrudDao<BankCard,BankCardVo,BankCardQuery> {
	
	public List<BankCardVo> query(BankCardQuery bankCardQuery);
}