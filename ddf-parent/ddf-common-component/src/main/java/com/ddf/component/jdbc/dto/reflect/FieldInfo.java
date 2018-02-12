package com.ddf.component.jdbc.dto.reflect;

import java.io.Serializable;

public class FieldInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String dbFileName;
	private String fieldName;
	private Class<?> fieldType;
	
	public Class<?> getFieldType() {
		return fieldType;
	}
	public void setFieldType(Class<?> fieldType) {
		this.fieldType = fieldType;
	}
	public String getDbFileName() {
		return dbFileName;
	}
	public void setDbFileName(String dbFileName) {
		this.dbFileName = dbFileName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	

}
