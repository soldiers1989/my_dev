package com.ddf.dic.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.dic.dto.MetroLine;
import com.ddf.entity.dic.query.MetroLineQuery;
import com.ddf.entity.dic.vo.MetroLineVo;

/**
 * metro_line DAO接口
 * @author robot
 * @version 2018-01-08
 */
public interface MetroLineDao extends CrudDao<MetroLine,MetroLineVo,MetroLineQuery> {
	
}