package com.ddf.entity.dic.dto;

import com.ddf.entity.dic.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * area Entity
 * @author robot
 * @version 2018-01-17
 */
@ApiModel(description = "Area")
public class Area extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "父ID")
	private String parentId;
	@ApiModelProperty(value = "所有父级ID")
	private String parentIds;
	@ApiModelProperty(value = "枚举类型，国家，省，城市，大区，板块")
	private String type;//区域类型（1：国家；2：省份、直辖市；3：地市；4：区县；5：板块）
	@ApiModelProperty(value = "排序")
	private Integer sort;
	@ApiModelProperty(value = "经度")
	private String lng;
	@ApiModelProperty(value = "纬度")
	private String lat;
	
	public Area() {
		super();
	}

	public Area(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	
}