package com.ddf.entity.rent.dto;

import java.math.BigDecimal;
import java.util.Date;
import com.ddf.entity.rent.eo.*;
import com.ddf.entity.base.dto.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * share_apartment Entity
 * @author robot
 * @version 2018-02-02
 */
@ApiModel(description = "ShareApartment")
public class ShareApartment extends DataEntity {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "合租房源id")
	private String houseId;
	@ApiModelProperty(value = "发布人id")
	private String userId;
	@ApiModelProperty(value = "房间名")
	private String name;
	@ApiModelProperty(value = "封面照片")
	private String coverImg;
	@ApiModelProperty(value = "详情照片(最多8张)")
	private String detailImgs;
	@ApiModelProperty(value = "枚举类型，主卧first、次卧second、客卧guest")
	private ShareApartmentBedroomType bedroomType;
	@ApiModelProperty(value = "枚举类型，nothing（毛坯），simple（简装），fine（精装）")
	private ShareApartmentZhuangxiu zhuangxiu;
	@ApiModelProperty(value = "枚举类型，east（东） south（南）,west（西）north（北）")
	private ShareApartmentChaoxiang chaoxiang;
	@ApiModelProperty(value = "面积（单位平方）")
	private BigDecimal size;
	@ApiModelProperty(value = "详细描述")
	private String summary;
	@ApiModelProperty(value = "独用设施（字典）")
	private String privateFacilitys;
	@ApiModelProperty(value = "房间特色（字典）")
	private String features;
	@ApiModelProperty(value = "枚举类型，招租状态（open,close)")
	private ShareApartmentMatchStatus matchStatus;
	@ApiModelProperty(value = "租金")
	private BigDecimal amout;
	@ApiModelProperty(value = "房客要求（字典）")
	private String lodgerLabels;
	@ApiModelProperty(value = "枚举类型，定金状态(open,close)")
	private ShareApartmentDepositStatus depositStatus;
	@ApiModelProperty(value = "枚举类型，待完善create_review，待审核wait_review，已审核review_pass，已驳回review_reject")
	private ShareApartmentStatus status;
	@ApiModelProperty(value = "审核通过时间")
	private Date reviewSuccessDate;
	@ApiModelProperty(value = "隐藏状态(0-正常，1-隐藏)")
	private Boolean hideFlag;
	
	public ShareApartment() {
		super();
	}

	public ShareApartment(String id){
		super(id);
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public ShareApartmentBedroomType getBedroomType() {
		return bedroomType;
	}

	public void setBedroomType(ShareApartmentBedroomType bedroomType) {
		this.bedroomType = bedroomType;
	}
	
	public ShareApartmentZhuangxiu getZhuangxiu() {
		return zhuangxiu;
	}

	public void setZhuangxiu(ShareApartmentZhuangxiu zhuangxiu) {
		this.zhuangxiu = zhuangxiu;
	}
	
	public ShareApartmentChaoxiang getChaoxiang() {
		return chaoxiang;
	}

	public void setChaoxiang(ShareApartmentChaoxiang chaoxiang) {
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
	
	public ShareApartmentMatchStatus getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(ShareApartmentMatchStatus matchStatus) {
		this.matchStatus = matchStatus;
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
	
	public ShareApartmentDepositStatus getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(ShareApartmentDepositStatus depositStatus) {
		this.depositStatus = depositStatus;
	}
	
	public ShareApartmentStatus getStatus() {
		return status;
	}

	public void setStatus(ShareApartmentStatus status) {
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