package com.ddf.entity.dic.dto;


import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * metro_line Entity
 * @author robot
 * @version 2018-01-10
 */
@ApiModel(description = "MetroLine")
public class MetroLine extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "排序")
	private Integer sort;
	@ApiModelProperty(value = "经度")
	private String lng;
	@ApiModelProperty(value = "纬度")
	private String lat;
	@ApiModelProperty(value = "城市ID")
	private String cityId;
	
	public MetroLine() {
		super();
	}

	public MetroLine(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
}