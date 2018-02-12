package com.ddf.entity.dic.dto;


import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * metro_station Entity
 * @author robot
 * @version 2018-01-10
 */
@ApiModel(description = "MetroStation")
public class MetroStation extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "地铁线ID")
	private String metroLineId;
	@ApiModelProperty(value = "排序")
	private Integer sort;
	
	public MetroStation() {
		super();
	}

	public MetroStation(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getMetroLineId() {
		return metroLineId;
	}

	public void setMetroLineId(String metroLineId) {
		this.metroLineId = metroLineId;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}