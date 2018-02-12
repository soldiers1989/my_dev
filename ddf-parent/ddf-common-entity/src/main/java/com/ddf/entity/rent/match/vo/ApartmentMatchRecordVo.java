package com.ddf.entity.rent.match.vo;

import com.ddf.entity.rent.match.dto.ApartmentMatchRecord;

/**
 * apartment_match_record EntityVo
 * @author robot
 * @version 2018-02-05
 */
public class ApartmentMatchRecordVo extends ApartmentMatchRecord {
	
	private static final long serialVersionUID = 1L;

	private String position;//位置
	private String houseTitle;//
	private String ownTypeStr;//
	private String landlord;//
	private String hideFlagStr;//

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHouseTitle() {
		return houseTitle;
	}

	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
	}

	public String getOwnTypeStr() {
		if (super.getOwnType() !=null){

			return super.getOwnType().getExplain();
		}
		return "";
	}

	public void setOwnTypeStr(String ownTypeStr) {
		this.ownTypeStr = ownTypeStr;
	}

	public String getLodger() {
		return landlord;
	}

	public void setLodger(String lodger) {
		this.landlord = lodger;
	}

	public String getHideFlagStr() {
		if (super.getHideFlag() !=null){
			return super.getHideFlag() ? "否":"是";
		}
		return "";
	}

	public void setHideFlagStr(String hideFlagStr) {
		this.hideFlagStr = hideFlagStr;
	}
}