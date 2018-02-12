package com.ddf.component.jdbc.util;

import java.util.HashMap;
import java.util.Map;

public class SqlCacheUtil {
	
	private static Map<String,String> sqlCacheMap = new HashMap<String,String>();
	
	
	
	static String get4save(String tableName){
		return sqlCacheMap.get(opt.save+tableName);
	}
	static String get4remove(String tableName){
		return sqlCacheMap.get(opt.remove+tableName);
	}
	static String get4query(String tableName){
		return sqlCacheMap.get(opt.query+tableName);
	}
	static String get4modify(String tableName){
		return sqlCacheMap.get(opt.modify+tableName);
	}
	
	

	static String put4save(String tableName,String sql){
		return sqlCacheMap.put(opt.save+tableName,sql);
	}
	static String put4remove(String tableName,String sql){
		return sqlCacheMap.put(opt.remove+tableName,sql);
	}
	static String put4query(String tableName,String sql){
		return sqlCacheMap.put(opt.query+tableName,sql);
	}
	static String put4modify(String tableName,String sql){
		return sqlCacheMap.put(opt.modify+tableName,sql);
	}
}

enum opt{
	save,remove,query,modify;
}