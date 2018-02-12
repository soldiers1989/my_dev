package com.ddf.entity.rent.match.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.ddf.entity.rent.eo.*;
import com.ddf.entity.rent.match.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * apartment_match_record Entity
 * @author robot
 * @version 2018-02-05
 */
@ApiModel(description = "ApartmentMatchRecord")
public class ApartmentMatchRecord extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "需求ID")
	private String rentDemandId;
	@ApiModelProperty(value = "房东id")
	private String landlordId;
	@ApiModelProperty(value = "需求匹配到的房源id")
	private String apartmentId;
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
	@ApiModelProperty(value = "楼栋号")
	private String loudonghao;
	@ApiModelProperty(value = "单元号")
	private String danyuanhao;
	@ApiModelProperty(value = "室号")
	private String shihao;
	@ApiModelProperty(value = "租金")
	private BigDecimal amout;
	@ApiModelProperty(value = "室")
	private Long room;
	@ApiModelProperty(value = "厅")
	private Long hall;
	@ApiModelProperty(value = "卫")
	private Long bathRoom;
	@ApiModelProperty(value = "面积（单位平方）")
	private BigDecimal size;
	@ApiModelProperty(value = "总层数")
	private Long totalFloor;
	@ApiModelProperty(value = "当前层数")
	private Long currentFloor;
	@ApiModelProperty(value = "枚举类型，装修(毛坯、简装、精装）")
	private ApartmentZhuangxiu zhuangxiu;
	@ApiModelProperty(value = "枚举类型，朝向（朝东、朝南、朝西、朝北）")
	private ApartmentChaoxiang chaoxiang;
	@ApiModelProperty(value = "房源标签")
	private String houseLabels;
	@ApiModelProperty(value = "房屋设施")
	private String houseFacilityIds;
	@ApiModelProperty(value = "房产证")
	private String houseCertificateImg;
	@ApiModelProperty(value = "房屋图片")
	private String houseImgs;
	@ApiModelProperty(value = "枚举类型，匹配状态（open,close)")
	private ApartmentMatchStatus matchStatus;
	@ApiModelProperty(value = "租客要求")
	private String lodgerLabels;
	@ApiModelProperty(value = "枚举类型，整租whole_rent，合租share_rent")
	private ApartmentRentType rentType;
	@ApiModelProperty(value = "房源简介")
	private String summary;
	@ApiModelProperty(value = "枚举类型，待审核wait_review，已审核review_pass，已驳回review_reject")
	private ApartmentStatus status;
	@ApiModelProperty(value = "隐藏状态(1-正常，0-隐藏)")
	private Boolean hideFlag;
	@ApiModelProperty(value = "枚举类型，定金状态(open,close)")
	private ApartmentDepositStatus depositStatus;
	@ApiModelProperty(value = "审核通过时间")
	private Date reviewSuccessDate;
	@ApiModelProperty(value = "房屋设施（字典）")
	private String facilitys;
	@ApiModelProperty(value = "封面照片")
	private String coverImg;
	@ApiModelProperty(value = "详情照片(最多8张)")
	private String detailImgs;
	@ApiModelProperty(value = "负责人")
	private String assistantId;
	@ApiModelProperty(value = "枚举类型，主卧first、次卧second、客卧guest")
	private ShareApartmentBedroomType bedroomType;
	@ApiModelProperty(value = "独用设施（字典）")
	private String privateFacilitys;
	@ApiModelProperty(value = "房间特色（字典）")
	private String features;
	@ApiModelProperty(value = "枚举类型，非转租yes，转租no")
	private ShareHouseOwnType ownType;
	@ApiModelProperty(value = "匹配时间")
	private Date matchDate;
	
	public ApartmentMatchRecord() {
		super();
	}

	public ApartmentMatchRecord(String id){
		super(id);
	}

	public String getRentDemandId() {
		return rentDemandId;
	}

	public void setRentDemandId(String rentDemandId) {
		this.rentDemandId = rentDemandId;
	}

	public String getLandlordId() {
		return landlordId;
	}

	public void setLandlordId(String landlordId) {
		this.landlordId = landlordId;
	}

	public String getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(String apartmentId) {
		this.apartmentId = apartmentId;
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

	public BigDecimal getAmout() {
		return amout;
	}

	public void setAmout(BigDecimal amout) {
		this.amout = amout;
	}

	public Long getRoom() {
		return room;
	}

	public void setRoom(Long room) {
		this.room = room;
	}

	public Long getHall() {
		return hall;
	}

	public void setHall(Long hall) {
		this.hall = hall;
	}

	public Long getBathRoom() {
		return bathRoom;
	}

	public void setBathRoom(Long bathRoom) {
		this.bathRoom = bathRoom;
	}

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
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

	public ApartmentZhuangxiu getZhuangxiu() {
		return zhuangxiu;
	}

	public void setZhuangxiu(ApartmentZhuangxiu zhuangxiu) {
		this.zhuangxiu = zhuangxiu;
	}

	public ApartmentChaoxiang getChaoxiang() {
		return chaoxiang;
	}

	public void setChaoxiang(ApartmentChaoxiang chaoxiang) {
		this.chaoxiang = chaoxiang;
	}

	public String getHouseLabels() {
		return houseLabels;
	}

	public void setHouseLabels(String houseLabels) {
		this.houseLabels = houseLabels;
	}

	public String getHouseFacilityIds() {
		return houseFacilityIds;
	}

	public void setHouseFacilityIds(String houseFacilityIds) {
		this.houseFacilityIds = houseFacilityIds;
	}

	public String getHouseCertificateImg() {
		return houseCertificateImg;
	}

	public void setHouseCertificateImg(String houseCertificateImg) {
		this.houseCertificateImg = houseCertificateImg;
	}

	public String getHouseImgs() {
		return houseImgs;
	}

	public void setHouseImgs(String houseImgs) {
		this.houseImgs = houseImgs;
	}

	public ApartmentMatchStatus getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(ApartmentMatchStatus matchStatus) {
		this.matchStatus = matchStatus;
	}

	public String getLodgerLabels() {
		return lodgerLabels;
	}

	public void setLodgerLabels(String lodgerLabels) {
		this.lodgerLabels = lodgerLabels;
	}

	public ApartmentRentType getRentType() {
		return rentType;
	}

	public void setRentType(ApartmentRentType rentType) {
		this.rentType = rentType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public ApartmentStatus getStatus() {
		return status;
	}

	public void setStatus(ApartmentStatus status) {
		this.status = status;
	}

	public Boolean getHideFlag() {
		return hideFlag;
	}

	public void setHideFlag(Boolean hideFlag) {
		this.hideFlag = hideFlag;
	}

	public ApartmentDepositStatus getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(ApartmentDepositStatus depositStatus) {
		this.depositStatus = depositStatus;
	}

	public Date getReviewSuccessDate() {
		return reviewSuccessDate;
	}

	public void setReviewSuccessDate(Date reviewSuccessDate) {
		this.reviewSuccessDate = reviewSuccessDate;
	}

	public String getFacilitys() {
		return facilitys;
	}

	public void setFacilitys(String facilitys) {
		this.facilitys = facilitys;
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

	public String getAssistantId() {
		return assistantId;
	}

	public void setAssistantId(String assistantId) {
		this.assistantId = assistantId;
	}

	public ShareApartmentBedroomType getBedroomType() {
		return bedroomType;
	}

	public void setBedroomType(ShareApartmentBedroomType bedroomType) {
		this.bedroomType = bedroomType;
	}

	public String getPrivateFacilitys() {
		return privateFacilitys;
	}

	public void setPrivateFacilitys(String privateFacilitys) {
		this.privateFacilitys = privateFacilitys;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public ShareHouseOwnType getOwnType() {
		return ownType;
	}

	public void setOwnType(ShareHouseOwnType ownType) {
		this.ownType = ownType;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
}