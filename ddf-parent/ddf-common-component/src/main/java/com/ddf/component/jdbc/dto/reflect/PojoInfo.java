package com.ddf.component.jdbc.dto.reflect;

import java.io.Serializable;
import java.util.List;

public class PojoInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String tableName;
	private String pojoName;
	private List<FieldInfo> fieldInfos;
	
	public List<FieldInfo> getFieldInfos() {
		return fieldInfos;
	}
	public void setFieldInfos(List<FieldInfo> fieldInfos) {
		this.fieldInfos = fieldInfos;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPojoName() {
		return pojoName;
	}
	public void setPojoName(String pojoName) {
		this.pojoName = pojoName;
	}
	

}
