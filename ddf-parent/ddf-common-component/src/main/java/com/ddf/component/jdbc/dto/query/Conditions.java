package com.ddf.component.jdbc.dto.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conditions implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Condition[] conditionArray;
	
	private String groupName;

	public static Conditions build(String groupName ,Condition... conditions){
		Conditions self = new Conditions();
		self.groupName = groupName;
		self.conditionArray = conditions;
		return self;
	}

	public Condition[] getConditionArray() {
		return conditionArray;
	}
	public String getGroupName() {
		return groupName;
	}
	

	

}
