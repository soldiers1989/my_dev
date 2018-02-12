package com.ddf.entity.rent.dto;

import java.math.BigDecimal;
import java.util.Date;
import com.ddf.entity.rent.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * apartment Entity
 * @author robot
 * @version 2018-02-02
 */
@ApiModel(description = "Apartment")
public class Apartment extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户ID")
	private String userId;
	@ApiModelProperty(value = "负责人")
	private String assistantId;
	@ApiModelProperty(value = "枚举类型，非转租yes，转租no")
	private ApartmentOwnType ownType;
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
	@ApiModelProperty(value = "详情照片(最多8张)")
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
	@ApiModelProperty(value = "室")
	private Long room;
	@ApiModelProperty(value = "厅")
	private Long hall;
	@ApiModelProperty(value = "卫")
	private Long bathRoom;
	@ApiModelProperty(value = "枚举类型，nothing（毛坯），simple（简装），fine（精装））")
	private ApartmentZhuangxiu zhuangxiu;
	@ApiModelProperty(value = "枚举类型， east（东） south（南）,west（西）north（北））")
	private ApartmentChaoxiang chaoxiang;
	@ApiModelProperty(value = "面积（单位平方）")
	private BigDecimal size;
	@ApiModelProperty(value = "详细描述")
	private String summary;
	@ApiModelProperty(value = "房屋设施（字典）")
	private String facilitys;
	@ApiModelProperty(value = "房间特色（字典）")
	private String features;
	@ApiModelProperty(value = "租金")
	private BigDecimal amout;
	@ApiModelProperty(value = "租客要求（字典）")
	private String lodgerLabels;
	@ApiModelProperty(value = "枚举类型，招租状态（open,close)")
	private ApartmentMatchStatus matchStatus;
	@ApiModelProperty(value = "枚举类型，定金状态(open,close)")
	private ApartmentDepositStatus depositStatus;
	@ApiModelProperty(value = "枚举类型，待审核wait_review，已审核review_pass，已驳回review_reject")
	private ApartmentStatus status;
	@ApiModelProperty(value = "审核通过时间")
	private Date reviewSuccessDate;
	@ApiModelProperty(value = "隐藏状态(0-正常，1-隐藏)")
	private Boolean hideFlag;
	
	public Apartment() {
		super();
	}

	public Apartment(String id){
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
	
	public ApartmentOwnType getOwnType() {
		return ownType;
	}

	public void setOwnType(ApartmentOwnType ownType) {
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
	
	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getFacilitys() {
		return facilitys;
	}

	public void setFacilitys(String facilitys) {
		this.facilitys = facilitys;
	}
	
	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}
	
	public BigDecimal getAmout() {
		return amout;
	}

	public void setAmout(BigDecimal amout) {
		this.amout = amout;
	}
	
	public String getLodgerLabels() {
		return lodgerLabels;
	}

	public void setLodgerLabels(String lodgerLabels) {
		this.lodgerLabels = lodgerLabels;
	}
	
	public ApartmentMatchStatus getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(ApartmentMatchStatus matchStatus) {
		this.matchStatus = matchStatus;
	}
	
	public ApartmentDepositStatus getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(ApartmentDepositStatus depositStatus) {
		this.depositStatus = depositStatus;
	}
	
	public ApartmentStatus getStatus() {
		return status;
	}

	public void setStatus(ApartmentStatus status) {
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