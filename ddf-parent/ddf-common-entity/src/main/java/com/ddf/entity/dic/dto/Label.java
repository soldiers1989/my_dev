package com.ddf.entity.dic.dto;

import com.ddf.entity.dic.eo.LabelType;
import com.ddf.entity.rent.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * label Entity
 * @author robot
 * @version 2018-01-17
 */
@ApiModel(description = "Label")
public class Label extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "枚举类型，房东，房源，房源设施等")
	private LabelType type;
	@ApiModelProperty(value = "排序")
	private Integer sort;
	
	public Label() {
		super();
	}

	public Label(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LabelType getType() {
		return type;
	}

	public void setType(LabelType type) {
		this.type = type;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}