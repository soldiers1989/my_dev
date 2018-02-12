package com.ddf.entity.student.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * student Entity
 * @author robot
 * @version 2017-11-29
 */
@ApiModel(description = "Student")
public class Student extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "name")
	@NotNull(message="姓名不能为空")
	@Size(max=4,min=2,message="姓名长度只能在2和4之间")
	private String name;
	@ApiModelProperty(value = "age")
	@Min(value=18,message="必须大于18")
	private Integer age;
	
	public Student() {
		super();
	}

	public Student(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}