package com.ddf.component.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ddf.component.jdbc.base.CrudDao;
import com.ddf.component.jdbc.base.Query;
import com.ddf.component.jdbc.dto.query.Condition;
import com.ddf.component.jdbc.dto.query.Conditions;
import com.ddf.component.jdbc.dto.query.Sort;
import com.ddf.component.jdbc.eo.OperatorType;
import com.ddf.component.jdbc.eo.SortType;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.student.dto.Student;
import com.ddf.entity.student.query.StudentQuery;
import com.ddf.entity.student.vo.StudentVo;

@Repository
public class StudentDao extends CrudDao<Student,StudentVo>{
	
	public Page<StudentVo> queryPage(){
		List<Integer> ages = new ArrayList<Integer>();
		ages.add(10);
		ages.add(11);
		ages.add(12);
		
		String sql = "select * from student s";
		Conditions andConditions = Conditions.build("group1",
													Condition.build("name", OperatorType.like, "aa"),
													Condition.build("age", OperatorType.in, ages));
		
		Conditions orConditions = Conditions.build("group2",
													Condition.build("remark", OperatorType.isNull));
		
		Query query = super.buildQuery(sql)
						.and(andConditions)
						.or(orConditions)
						.sort(Sort.build("create_date", SortType.ASC))
						.sort(Sort.build("update_date", SortType.DESC))
						.page(0, 5);

		System.out.println(query.getSql());
		System.out.println(query.getCountSql());
		System.out.println(query.getParamMap());
		
    	Page<StudentVo> page = null;//super.queryPage(query);
    	return page;
    }
	
	
	public List<StudentVo> query(){
		List<StudentVo> students = new ArrayList<StudentVo>();
		
		
		String sql = "select * FRom student s";
		List<Integer> ages = new ArrayList<Integer>();
		ages.add(10);ages.add(11);ages.add(51);
		List<String> names = new ArrayList<String>();
		names.add("aaa");names.add("bbb");names.add("ccc");
		
		Conditions andConditions = Conditions.build("group1",
													Condition.build("name", OperatorType.isNotNull),
													Condition.build("age", OperatorType.gte, 11),
													Condition.build("age", OperatorType.in, ages),
													Condition.build("create_date", OperatorType.lte, new Date()));
													//Condition.build("name", OperatorType.in, names),
													//Condition.build("name", OperatorType.like, "%aaaa%"),
												
		
		Conditions orConditions = Conditions.build("group2",Condition.build("name", OperatorType.eq, "222"),Condition.build("age", OperatorType.gte, 22));
		Conditions orConditions1 = Conditions.build("group3",Condition.build("name", OperatorType.eq, "333"));
		
		
		
		Query query = super.buildQuery(sql).and(andConditions)
						.sort(Sort.build("name", SortType.ASC)).sort(Sort.build("age", SortType.DESC));
						//.page(99, 10);
		System.out.println(query.getSql());
		System.out.println(query.getCountSql());
		System.out.println(query.getParamMap());
		
		//students = super.queryList("select *from student where age in(10,11)");
		students = super.queryList(query);
		return students;
	}
	
	
	
	/*public List<Student> findStudentList() {
        Student studentaa=new Student();
        studentaa.setAge(1);
        //studentaa.setbSno("10");
        studentaa.setId("2");
        studentaa.setName("aa");
        redisApi.put("aa",studentaa);
        System.out.println(redisApi.get("aa",Student.class).getAge());
        jdbcTemplate.update("", new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				
			}
        	
        });
        Student student= jdbcTemplate.queryForObject("select * from student where id=?",new BeanPropertyRowMapper<Student>(Student.class),"1");

        List<Student> list = jdbcTemplate.query("select * from student", new Object[]{}, new BeanPropertyRowMapper<Student>(Student.class));

        Student studentVo=jdbcTemplate.queryForObject("SELECT a.*,b.b_sno from student a, Sc b where a.id=b.student_id and a.id=?"
        ,new BeanPropertyRowMapper<Student>(Student.class),"1");

        List<Student> studentVos=jdbcTemplate.query("SELECT a.*,b.b_sno from student a, Sc b where a.id=b.student_id ",new Object[]{}
                ,new BeanPropertyRowMapper<Student>(Student.class));

        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    	//return null;
    }*/

}
