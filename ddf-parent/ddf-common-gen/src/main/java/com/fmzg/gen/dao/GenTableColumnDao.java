/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fmzg.gen.dao;

import org.springframework.stereotype.Component;

import com.fmzg.gen.entity.GenScheme;
import com.fmzg.gen.entity.GenTable;
import com.fmzg.gen.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * @author ThinkGem
 * @version 2013-10-15
 */

public interface GenTableColumnDao extends CrudDao<GenTableColumn> {
	
	public void deleteByGenTableId(String genTableId);
}
