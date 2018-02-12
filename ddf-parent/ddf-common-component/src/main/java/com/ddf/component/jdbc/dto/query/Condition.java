package com.ddf.component.jdbc.dto.query;

import java.io.Serializable;

import com.ddf.component.jdbc.eo.OperatorType;

public class Condition implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String parameterName;
    public Object parameterValue;
    public OperatorType operatorType;
    
    
    public static Condition build(String parameterName,OperatorType operatorType){
    	Condition condition = new Condition();
    	condition.setParameterName(parameterName);
    	condition.setOperatorType(operatorType);
    	return condition;
    }
    
    public static Condition build(String parameterName,OperatorType operatorType,Object parameterValue){
    	Condition condition = new Condition();
    	condition.setParameterName(parameterName);
    	condition.setOperatorType(operatorType);
    	condition.setParameterValue(parameterValue);
    	return condition;
    }
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public Object getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(Object parameterValue) {
		this.parameterValue = parameterValue;
	}
	public OperatorType getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}

}
