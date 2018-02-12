package com.ddf.component.jdbc.dto.query;

import java.io.Serializable;

import com.ddf.component.jdbc.eo.SortType;

public class Sort implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String parameterName;
    public SortType sortType;
    
    public static Sort build(String parameterName,SortType sortType){
    	Sort sort = new Sort();
    	sort.setParameterName(parameterName);
    	sort.setSortType(sortType);
    	return sort;
    }
    
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public SortType getSortType() {
		return sortType;
	}
	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

}
