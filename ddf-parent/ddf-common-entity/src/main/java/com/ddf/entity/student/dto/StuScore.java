package com.ddf.entity.student.dto;

import java.math.BigDecimal;

import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * stu_score Entity
 * @author robot
 * @version 2017-11-28
 */
@ApiModel(description = "StuScore")
public class StuScore extends DataEntity {
	
	private static final long serialVersionUID = 1L;
				@ApiModelProperty(value = "学生ID")
				private String studentId;
				@ApiModelProperty(value = "分数")
				private BigDecimal score;
	
	public StuScore() {
		super();
	}

	public StuScore(String id){
		super(id);
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
}