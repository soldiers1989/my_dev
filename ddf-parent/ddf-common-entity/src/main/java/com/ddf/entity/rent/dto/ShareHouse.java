package com.ddf.entity.rent.dto;

import java.util.Date;
import com.ddf.entity.rent.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * share_house Entity
 * @author robot
 * @version 2018-02-02
 */
@ApiModel(description = "ShareHouse")
public class ShareHouse extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户ID")
	private String userId;
	@ApiModelProperty(value = "负责人")
	private String assistantId;
	@ApiModelProperty(value = "室友（租客信息：性别+职业,性别+职业,性别+职业）")
	private String lodgerInfo;
	@ApiModelProperty(value = "枚举类型，非转租yes，转租no")
	private ShareHouseOwnType ownType;
	@ApiModelProperty(value = "省")
	private String provinceId;
	@ApiModelProperty(value = "市")
	private String cityId;
	@ApiModelProperty(value = "大区")
	private String districtId;
	@ApiModelProperty(value = "板块")
	private String circleId;
	@ApiModelProperty(value = "小区名")
	private String xiaoquName;
	@ApiModelProperty(value = "小区ID")
	private String xiaoquId;
	@ApiModelProperty(value = "小区地址")
	private String xiaoquAddress;
	@ApiModelProperty(value = "封面照片")
	private String coverImg;
	@ApiModelProperty(value = "详情图片")
	private String detailImgs;
	@ApiModelProperty(value = "楼栋号,包括（独栋）")
	private String loudonghao;
	@ApiModelProperty(value = "单元号")
	private String danyuanhao;
	@ApiModelProperty(value = "室号,包括（别墅）")
	private String shihao;
	@ApiModelProperty(value = "总层数")
	private Long totalFloor;
	@ApiModelProperty(value = "当前层数")
	private Long currentFloor;
	@ApiModelProperty(value = "房间数")
	private Long apartmentNum;
	@ApiModelProperty(value = "公用设施")
	private String publicFacilitys;
	@ApiModelProperty(value = "枚举类型，待审核wait_review，已审核review_pass，已驳回review_reject")
	private ShareHouseStatus status;
	@ApiModelProperty(value = "审核通过时间")
	private Date reviewSuccessDate;
	@ApiModelProperty(value = "隐藏状态(0-正常，1-隐藏)")
	private Boolean hideFlag;
	
	public ShareHouse() {
		super();
	}

	public ShareHouse(String id){
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getAssistantId() {
		return assistantId;
	}

	public void setAssistantId(String assistantId) {
		this.assistantId = assistantId;
	}
	
	public String getLodgerInfo() {
		return lodgerInfo;
	}

	public void setLodgerInfo(String lodgerInfo) {
		this.lodgerInfo = lodgerInfo;
	}
	
	public ShareHouseOwnType getOwnType() {
		return ownType;
	}

	public void setOwnType(ShareHouseOwnType ownType) {
		this.ownType = ownType;
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
	
	public String getXiaoquName() {
		return xiaoquName;
	}

	public void setXiaoquName(String xiaoquName) {
		this.xiaoquName = xiaoquName;
	}
	
	public String getXiaoquId() {
		return xiaoquId;
	}

	public void setXiaoquId(String xiaoquId) {
		this.xiaoquId = xiaoquId;
	}
	
	public String getXiaoquAddress() {
		return xiaoquAddress;
	}

	public void setXiaoquAddress(String xiaoquAddress) {
		this.xiaoquAddress = xiaoquAddress;
	}
	
	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	
	public String getDetailImgs() {
		return detailImgs;
	}

	public void setDetailImgs(String detailImgs) {
		this.detailImgs = detailImgs;
	}
	
	public String getLoudonghao() {
		return loudonghao;
	}

	public void setLoudonghao(String loudonghao) {
		this.loudonghao = loudonghao;
	}
	
	public String getDanyuanhao() {
		return danyuanhao;
	}

	public void setDanyuanhao(String danyuanhao) {
		this.danyuanhao = danyuanhao;
	}
	
	public String getShihao() {
		return shihao;
	}

	public void setShihao(String shihao) {
		this.shihao = shihao;
	}
	
	public Long getTotalFloor() {
		return totalFloor;
	}

	public void setTotalFloor(Long totalFloor) {
		this.totalFloor = totalFloor;
	}
	
	public Long getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(Long currentFloor) {
		this.currentFloor = currentFloor;
	}
	
	public Long getApartmentNum() {
		return apartmentNum;
	}

	public void setApartmentNum(Long apartmentNum) {
		this.apartmentNum = apartmentNum;
	}
	
	public String getPublicFacilitys() {
		return publicFacilitys;
	}

	public void setPublicFacilitys(String publicFacilitys) {
		this.publicFacilitys = publicFacilitys;
	}
	
	public ShareHouseStatus getStatus() {
		return status;
	}

	public void setStatus(ShareHouseStatus status) {
		this.status = status;
	}
	
	public Date getReviewSuccessDate() {
		return reviewSuccessDate;
	}

	public void setReviewSuccessDate(Date reviewSuccessDate) {
		this.reviewSuccessDate = reviewSuccessDate;
	}
	
	public Boolean getHideFlag() {
		return hideFlag;
	}

	public void setHideFlag(Boolean hideFlag) {
		this.hideFlag = hideFlag;
	}
	
}