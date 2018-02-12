package com.ddf.component.jdbc.base;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.ddf.component.jdbc.util.SqlBuildUtil;
import com.ddf.entity.base.dto.Page;


public class CrudDao<T,TV> {

	private static Logger logger = LoggerFactory.getLogger(CrudDao.class);
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
    @SuppressWarnings("unchecked")
	private  Class<T> getClz(){  
		Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 
	    return entityClass;  
	}
    @SuppressWarnings("unchecked")
	private  Class<TV> getQueryClz(){  
		Class <TV> entityClass = (Class <TV>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]; 
	    return entityClass;  
	}
	
    protected Query buildQuery(String sourceSql){
		return Query.build(sourceSql);
	}
    
    public boolean execute(String sql){
        return this.namedParameterJdbcTemplate.update(sql, EmptySqlParameterSource.INSTANCE)>0? true:false;
    }
    
    public List<TV> queryList(String sql){
    	List<TV> list = namedParameterJdbcTemplate.query(sql, EmptySqlParameterSource.INSTANCE ,new BeanPropertyRowMapper<TV>(getQueryClz()));
    	return list;
    }
    
    public String querySingleColumn(String sql){
    	String columnValue = null;
		try{
			columnValue = namedParameterJdbcTemplate.queryForObject(sql, EmptySqlParameterSource.INSTANCE, String.class);
		}catch(EmptyResultDataAccessException ex){
			logger.info("query querySingleColumn get null");
		}
    	return columnValue;
    }
    
    public List<TV> queryList(Query query){
    	String sql = query.getSql();
    	Map<String,Object> paramMap = query.getParamMap();
    	List<TV> list = namedParameterJdbcTemplate.query(sql, paramMap,new BeanPropertyRowMapper<TV>(getQueryClz()));
    	return list;
    }
    
    public long queryCount(Query query){
    	String sql = query.getCountSql();
    	Map<String,Object> paramMap = query.getParamMap();
    	long count = 0;
		try{
			count = namedParameterJdbcTemplate.queryForObject(sql, paramMap, Long.class);
		}catch(EmptyResultDataAccessException ex){
			logger.info("query count get null");
		}
    	return count;
    }
    
   /* public Page<TV> queryPage(Query query){
    	List<TV> list = this.queryList(query);
    	long totalCount = this.queryCount(query);
    	return new Page<TV>(list,totalCount);
    }*/
    
	public T query4id(String id){
		String sql = SqlBuildUtil.buildQuerySql(getClz());
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("id", id);
		T t = null;
		try{
			t = namedParameterJdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<T>(getClz()));
		}catch(EmptyResultDataAccessException ex){
			logger.info("query by id : ["+id+"] get null");
		}
		return t;
	}
	
	public boolean create(T entity){
		String sql = SqlBuildUtil.buildSaveSql(getClz());
		SqlParameterSource sps = new BeanPropertySqlParameterSource(entity);
        return this.namedParameterJdbcTemplate.update(sql, sps)>0? true:false;
	}
	
	
	public boolean modify(T entity){
		String sql = SqlBuildUtil.buildModifySql(getClz());
		SqlParameterSource sps = new BeanPropertySqlParameterSource(entity);
        return this.namedParameterJdbcTemplate.update(sql, sps)>0? true:false;
	}
	
	
	public boolean remove(String id){
		String sql = SqlBuildUtil.buildRemoveSql(getClz());
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("id", id);
        return this.namedParameterJdbcTemplate.update(sql, paramMap)>0? true:false;
	}
	
	
	
}
