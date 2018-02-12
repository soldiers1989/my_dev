package com.ddf.entity.dic.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.dic.dto.Xiaoqu;
import io.swagger.annotations.ApiParam;

/**
 * xiaoqu EntityQuery
 * @author robot
 * @version 2018-01-17
 */
public class XiaoquQuery extends Query {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String address;
	private Boolean displayState;
	public XiaoquQuery(){
		this.xiaoqu = new Xiaoqu();
	}

	private Xiaoqu xiaoqu;

	public Xiaoqu getXiaoqu() {
		return xiaoqu;
	}

	public void setXiaoqu(Xiaoqu xiaoqu) {
		this.xiaoqu = xiaoqu;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Boolean getDisplayState() {
		return displayState;
	}

	public void setDisplayState(Boolean displayState) {
		this.displayState = displayState;
	}
}