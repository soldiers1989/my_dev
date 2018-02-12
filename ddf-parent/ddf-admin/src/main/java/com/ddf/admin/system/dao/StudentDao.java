package com.ddf.admin.system.dao;

import com.ddf.admin.system.domain.StudentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-01-27 16:11:43
 */
@Mapper
public interface StudentDao {

	StudentDO get(String id);
	
	List<StudentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudentDO student);
	
	int update(StudentDO student);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
