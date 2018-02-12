package com.ddf.admin.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ddf.admin.system.dao.StudentDao;
import com.ddf.admin.system.domain.StudentDO;
import com.ddf.admin.system.service.StudentService;



@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public StudentDO get(String id){
		return studentDao.get(id);
	}
	
	@Override
	public List<StudentDO> list(Map<String, Object> map){
		return studentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentDao.count(map);
	}
	
	@Override
	public int save(StudentDO student){
		return studentDao.save(student);
	}
	
	@Override
	public int update(StudentDO student){
		return studentDao.update(student);
	}
	
	@Override
	public int remove(String id){
		return studentDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentDao.batchRemove(ids);
	}
	
}
