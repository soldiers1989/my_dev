package com.ddf.component.jdbc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddf.component.jdbc.dto.reflect.FieldInfo;
import com.ddf.component.jdbc.dto.reflect.PojoInfo;
import com.ddf.util.StringUtil;


public class SqlBuildUtil {
    
	public static String buildRemoveSql(Class<?> pojoClass){
		PojoInfo pojoInfo  = ReflectUtil.getPojoInfo(pojoClass);
		String sql = "delete from " + pojoInfo.getTableName() + " where id = :id";
        return sql;
    }
	
	public static String buildQuerySql(Class<?> pojoClass){
		PojoInfo pojoInfo  = ReflectUtil.getPojoInfo(pojoClass);
		String sql = "select * from " + pojoInfo.getTableName() + " where id = :id";
        return sql;
    }
	
	public static String buildSaveSql(Class<?> pojoClass){
		PojoInfo pojoInfo  = ReflectUtil.getPojoInfo(pojoClass);
        String tableName = pojoInfo.getTableName();
        List<FieldInfo> fieldInfos = pojoInfo.getFieldInfos();
        
        StringBuffer colNameBf = new StringBuffer();
        StringBuffer colValueBf = new StringBuffer();
        for(FieldInfo fieldInfo : fieldInfos){
        	colNameBf.append(fieldInfo.getDbFileName()).append(",");
        	colValueBf.append(":").append(fieldInfo.getFieldName()).append(",");
        }
        colNameBf.deleteCharAt(colNameBf.length()-1);
        colValueBf.deleteCharAt(colValueBf.length()-1);
        
        StringBuffer resultSql = new StringBuffer();
        resultSql.append("insert into ");
        resultSql.append(tableName);
        resultSql.append("(");
        resultSql.append(colNameBf);
        resultSql.append(") values (");
        resultSql.append(colValueBf);
        resultSql.append(")");
        return resultSql.toString();
        
    }
	
	public static String buildModifySql(Class<?> pojoClass){
		PojoInfo pojoInfo  = ReflectUtil.getPojoInfo(pojoClass);
		String tableName = pojoInfo.getTableName();
        List<FieldInfo> fieldInfos = pojoInfo.getFieldInfos();
        
        StringBuffer resultSql = new StringBuffer();
        
        resultSql.append("update ").append(tableName).append(" set ");
        for(FieldInfo fieldInfo : fieldInfos){
        	if(!fieldInfo.getDbFileName().equalsIgnoreCase("id")){
        		 resultSql.append(fieldInfo.getDbFileName());
                 resultSql.append("=:").append(fieldInfo.getFieldName()).append(",");
        	}
        }
        resultSql.deleteCharAt(resultSql.length()-1);
        resultSql.append(" where id = :id");
        
        return resultSql.toString();
        
    }

}
