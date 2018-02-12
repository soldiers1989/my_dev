package com.ddf.component.jdbc.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.ddf.component.jdbc.dto.reflect.FieldInfo;
import com.ddf.component.jdbc.dto.reflect.PojoInfo;
import com.ddf.entity.student.dto.Student;
import com.ddf.util.StringUtil;

public class ReflectUtil {
	
	private static Map<String,PojoInfo> pojoInfoCacheMap = new HashMap<String,PojoInfo>();
	
	public static void main(String[] args){
		PojoInfo pojoInfo = getPojoInfo(Student.class);
		System.out.println(pojoInfo);
	}
	
	public static PojoInfo getPojoInfo(Class<?> pojoClass){
		if(pojoInfoCacheMap.get(pojoClass.getName())!=null){
			return pojoInfoCacheMap.get(pojoClass.getName());
		}
		
		PojoInfo pojoInfo = new PojoInfo();
		List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();
		
		List<Field> fields = getFields(pojoClass);
		for(Field field : fields){
			if(!"serialVersionUID".equals(field.getName())){
	            FieldInfo fieldInfo = new FieldInfo();
	            fieldInfo.setFieldName(field.getName());
            
            	String annValue = StringUtil.lowerStrToUnderline(field.getName());//得到配置的数据库字段名（通过注解）
            	fieldInfo.setDbFileName(annValue);
            	fieldInfo.setFieldType(field.getType());
                fieldInfos.add(fieldInfo);
            }
        }
		
		pojoInfo.setTableName(StringUtil.lowerStrToUnderline(pojoClass.getSimpleName()).replaceFirst("_", ""));
		pojoInfo.setPojoName(pojoClass.getSimpleName());
		pojoInfo.setFieldInfos(fieldInfos);
		
		pojoInfoCacheMap.put(pojoClass.getName(), pojoInfo);
		return pojoInfo;
	}
	
	private static List<Field> getFields(Class<?> pojoClass){
		List<Field> fieldList = new ArrayList<Field>() ;
		Class<?> tempClass = pojoClass;
		while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
		      fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
		      tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
		}
		return fieldList;
	}
	

}
