/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fmzg.gen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fmzg.gen.dao.GenDataBaseDictDao;
import com.fmzg.gen.dao.GenTableColumnDao;
import com.fmzg.gen.dao.GenTableDao;
import com.fmzg.gen.entity.GenTable;
import com.fmzg.gen.entity.GenTableColumn;
import com.fmzg.gen.util.GenUtils;
import com.fmzg.gen.util.StringUtils;

/**
 * 业务表Service
 * @author ThinkGem
 * @version 2013-10-15
 */
@Service("genTableService")
@Transactional(readOnly = true)
public class GenTableService {

	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	@Autowired
	private GenDataBaseDictDao genDataBaseDictDao;
	
	public GenTable get(String id) {
		GenTable genTable = genTableDao.get(id);
		GenTableColumn genTableColumn = new GenTableColumn();
		genTableColumn.setGenTable(new GenTable());
		genTable.setColumnList(genTableColumnDao.findList(genTableColumn));
		return genTable;
	}

	public List<GenTable> findAll() {
		return genTableDao.findAllList(new GenTable());
	}
	
	/**
	 * 获取物理数据表列表
	 * @param genTable
	 * @return
	 */
	public List<GenTable> findTableListFormDb(GenTable genTable){
		return genDataBaseDictDao.findTableList(genTable);
	}
	
	/**
	 * 验证表名是否可用，如果已存在，则返回false
	 * @param genTable
	 * @return
	 */
	public boolean checkTableName(String tableName){
		if (StringUtils.isBlank(tableName)){
			return true;
		}
		GenTable genTable = new GenTable();
		genTable.setName(tableName);
		List<GenTable> list = genTableDao.findList(genTable);
		return list.size() == 0;
	}
	
	/**
	 * 获取物理数据表列表
	 * @param genTable
	 * @return
	 */
	public GenTable getTableFormDb(String tableName){
		GenTable genTable = new GenTable();
		genTable.setName(tableName);
			
		List<GenTable> list = genDataBaseDictDao.findTableList(genTable);
		if (list.size() > 0){
			
			genTable = list.get(0);
			// 设置字段说明
			if (StringUtils.isBlank(genTable.getComments())){
				genTable.setComments(genTable.getName());
			}
			genTable.setClassName(StringUtils.toCapitalizeCamelCase(genTable.getName()));		
			
			// 添加新列
			List<GenTableColumn> columnList = genDataBaseDictDao.findTableColumnList(genTable);
			for (GenTableColumn column : columnList){
				boolean b = false;
				for (GenTableColumn e : genTable.getColumnList()){
					if (e.getName().equals(column.getName())){
						b = true;
					}
				}
				if (!b){
					genTable.getColumnList().add(column);
				}
			}
			
			// 获取主键
			genTable.setPkList(genDataBaseDictDao.findTablePK(genTable));
			
			// 初始化列属性字段
			GenUtils.initColumnField(genTable);
			
		}
		
		return genTable;
	}
	
}
