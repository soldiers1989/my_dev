package com.ddf.dic.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.dic.query.LabelQuery;
import com.ddf.entity.dic.vo.LabelVo;
import com.ddf.entity.dic.dto.Label;

/**
 * label DAO接口
 * @author robot
 * @version 2018-01-16
 */
public interface LabelDao extends CrudDao<Label,LabelVo,LabelQuery> {
	
}