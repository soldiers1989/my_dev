package com.ddf.entity.dic.dto;

import java.util.Date;
import java.math.BigDecimal;
import com.ddf.entity.dic.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * xiaoqu Entity
 * @author robot
 * @version 2018-01-31
 */
@ApiModel(description = "Xiaoqu")
public class Xiaoqu extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "省份")
	private String provinceId;
	@ApiModelProperty(value = "城市")
	private String cityId;
	@ApiModelProperty(value = "大区")
	private String districtId;
	@ApiModelProperty(value = "商圈")
	private String circleId;
	@ApiModelProperty(value = "小区名称")
	private String name;
	@ApiModelProperty(value = "地址")
	private String address;
	@ApiModelProperty(value = "评论等级")
	private Integer opinionLevel;
	@ApiModelProperty(value = "评论内容")
	private String opinionContent;
	@ApiModelProperty(value = "经度")
	private String lng;
	@ApiModelProperty(value = "纬度")
	private String lat;
	@ApiModelProperty(value = "小区照片")
	private String imgs;
	@ApiModelProperty(value = "建造年代")
	private Date buildAge;
	@ApiModelProperty(value = "枚举类型 环线（inner_ring.内环内、inner_middle_ring.内环至中环、middle_outer_ring.中环至外环、outer_ring.外环外）")
	private XiaoquLoopLine loopLine;
	@ApiModelProperty(value = "物业 类型")
	private String propertyType;
	@ApiModelProperty(value = "物业公司")
	private String propertyCompany;
	@ApiModelProperty(value = "物业费")
	private BigDecimal propertyAmount;
	@ApiModelProperty(value = "开发商")
	private String developers;
	@ApiModelProperty(value = "展示状态(1.对外 0.对内)")
	private Boolean displayState;

	public Xiaoqu() {
		super();
	}

	public Xiaoqu(String id){
		super(id);
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	public String getCircleId() {
		return circleId;
	}

	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getOpinionLevel() {
		return opinionLevel;
	}

	public void setOpinionLevel(Integer opinionLevel) {
		this.opinionLevel = opinionLevel;
	}
	
	public String getOpinionContent() {
		return opinionContent;
	}

	public void setOpinionContent(String opinionContent) {
		this.opinionContent = opinionContent;
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
	
	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	public Date getBuildAge() {
		return buildAge;
	}

	public void setBuildAge(Date buildAge) {
		this.buildAge = buildAge;
	}
	
	public XiaoquLoopLine getLoopLine() {
		return loopLine;
	}

	public void setLoopLine(XiaoquLoopLine loopLine) {
		this.loopLine = loopLine;
	}
	
	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	
	public String getPropertyCompany() {
		return propertyCompany;
	}

	public void setPropertyCompany(String propertyCompany) {
		this.propertyCompany = propertyCompany;
	}
	
	public BigDecimal getPropertyAmount() {
		return propertyAmount;
	}

	public void setPropertyAmount(BigDecimal propertyAmount) {
		this.propertyAmount = propertyAmount;
	}
	
	public String getDevelopers() {
		return developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers;
	}
	
	public Boolean getDisplayState() {
		return displayState;
	}

	public void setDisplayState(Boolean displayState) {
		this.displayState = displayState;
	}
	
}