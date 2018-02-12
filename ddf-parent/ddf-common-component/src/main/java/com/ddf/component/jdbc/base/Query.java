package com.ddf.component.jdbc.base;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ddf.component.jdbc.dto.query.Condition;
import com.ddf.component.jdbc.dto.query.Conditions;
import com.ddf.component.jdbc.dto.query.Sort;
import com.ddf.component.jdbc.eo.OperatorType;
import com.ddf.component.jdbc.eo.SortType;

public class Query{
	
	private List<Conditions> andConditions;
	
	private List<Conditions> orConditions;
	
	private List<Sort> sorts;

	private long offset;

	private int rows;
	
	private boolean pageFlag;

	private String sourceSql;
	private String finalSql;
	private String finalCountSql;
	
	private Map<String,Object> paramMap;
	
	private boolean finalFlag = false;
	
	private Query(){}
	
	static Query build(String sourceSql){
		Query query = new Query();
		query.andConditions = new ArrayList<Conditions>();
		query.orConditions = new ArrayList<Conditions>();
		query.sorts = new ArrayList<Sort>();
		query.sourceSql = sourceSql;
		query.pageFlag = false;
		query.finalFlag = false;
		return query;
	}
	
	public Query and(Conditions conditions){
		this.finalFlag = false;
		andConditions.add(conditions);
		return this;
	}
	
	public Query or(Conditions conditions){
		this.finalFlag = false;
		orConditions.add(conditions);
		return this;
	}
	
	public Query sort(Sort sort){
		this.finalFlag = false;
		sorts.add(sort);
		return this;
	}
	
	public Query page(long offset,int rows){
		this.finalFlag = false;
		this.offset = offset;
		this.rows = rows;
		this.pageFlag = true;
		return this;
	}
	
	public String getSql(){
		if(finalFlag){
			return finalSql;
		}else{
			finalBuild();
			return finalSql;
		}
	}
	public String getCountSql(){
		if(finalFlag){
			return finalCountSql;
		}else{
			finalBuild();
			return finalCountSql;
		}
	}
	
	public Map<String, Object> getParamMap() {
		if(finalFlag){
			return paramMap;
		}else{
			finalBuild();
			return paramMap;
		}
	}
	
	private void finalBuild(){
		buildSql();
		buildCountSql();
		buildParamMap();
		finalFlag = true;
	}

	private void buildCountSql(){
		String countSql = "select count(1) ";
		int beginIndex = sourceSql.toLowerCase().indexOf("from ");
		int indexSort = finalSql.toLowerCase().indexOf("order by");
		int indexLimit = finalSql.toLowerCase().indexOf("limit ");
		int endIndex = indexSort<indexLimit ? indexLimit : indexSort;
		if(endIndex<0){
			countSql = countSql + finalSql.substring(beginIndex);
		}else{
			countSql = countSql + finalSql.substring(beginIndex, endIndex);
		}
		this.finalCountSql = countSql;
	}
	
	private void buildSql(){
		if(!sourceSql.contains(" where ")){
			sourceSql = sourceSql + " where 1=1 ";
		}
		
		StringBuffer sqlBf = new StringBuffer(sourceSql);
		for(Conditions conditions : andConditions){
			sqlBf.append(" and (");
			conditionsToSql(sqlBf,conditions);
			sqlBf.append(")");
		}
		
		for(Conditions conditions : orConditions){
			sqlBf.append(" or (");
			conditionsToSql(sqlBf,conditions);
			sqlBf.append(")");
		}
		
		if(sorts!=null && sorts.size()>0){
			sortsToSql(sqlBf,sorts);
		}
		
		if(pageFlag){
			sqlBf.append(" limit ").append(offset).append(",").append(rows);
		}
		
		this.finalSql =  sqlBf.toString();
	}
	
	private void buildParamMap(){
		paramMap = new HashMap<String,Object>();
		List<Conditions> allConditions = new ArrayList<Conditions>();
		allConditions.addAll(andConditions);
		allConditions.addAll(orConditions);
		for(Conditions conditions : allConditions){
			Condition[] conditionArray = conditions.getConditionArray();
			String groupName = conditions.getGroupName();
			for(Condition condition : conditionArray){
				String pname = condition.getParameterName();
				Object pvalue = condition.getParameterValue();
				OperatorType operatorType = condition.getOperatorType();
				
				if(!OperatorType.isAllowBlankValue(operatorType)){
					/*String valueStr = "";
					if(pvalue instanceof java.lang.String){
						valueStr = "'"+pvalue+"'";
					}else if(pvalue instanceof java.util.Date){
						valueStr = "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pvalue) + "'";
					}else if(pvalue instanceof java.util.List<?>){
						valueStr = "(" ;
						List<?> listValue = (List<?>) pvalue;
						for(Object sigleValue: listValue){
							if(sigleValue instanceof java.lang.String){
								valueStr = valueStr + "'"+sigleValue+"'" + ",";
							}else if(sigleValue instanceof java.util.Date){
								valueStr = valueStr + "'" +new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sigleValue) + "'" + ",";
							}else{
								valueStr = valueStr + sigleValue.toString() + ",";
							}
						}
						valueStr = valueStr.substring(0, valueStr.length()-1)+")";
						
					}else{
						valueStr = pvalue.toString();
					}
					
					paramMap.put(pname, valueStr);*/
					paramMap.put(groupName+"_"+pname+"_"+operatorType, pvalue);
				}
			}
		}
		
		paramMap.put("offset", offset);
		paramMap.put("rows", rows);
	}
	
	private <T> void conditionsToSql(StringBuffer sqlBf,Conditions conditions){
		Condition[] conditionArray = conditions.getConditionArray();
		String groupName = conditions.getGroupName();
		for(Condition condition : conditionArray){
			String pname = condition.getParameterName();
			OperatorType operatorType = condition.getOperatorType();
			if(conditionArray[0]!=condition){
				sqlBf.append(" and ");
			}
			if(OperatorType.isAllowBlankValue(operatorType)){
				sqlBf.append(pname).append(" ").append(operatorType.getSymbol());
			}else{
				if(OperatorType.in == operatorType || OperatorType.notIn == operatorType){
					sqlBf.append(pname).append(" ").append(operatorType.getSymbol()).append(" (:").append(groupName+"_"+pname+"_"+operatorType).append(")");
				}else if(OperatorType.blike == operatorType){
					sqlBf.append(pname).append(" ").append(operatorType.getSymbol()).append(" '%' :").append(groupName+"_"+pname+"_"+operatorType);
				}else if(OperatorType.elike == operatorType){
					sqlBf.append(pname).append(" ").append(operatorType.getSymbol()).append(" :").append(groupName+"_"+pname+"_"+operatorType).append(" '%'");
				}else if(OperatorType.like == operatorType){
					sqlBf.append(pname).append(" ").append(operatorType.getSymbol()).append(" '%' :").append(groupName+"_"+pname+"_"+operatorType).append(" '%'");
				}else{
					sqlBf.append(pname).append(" ").append(operatorType.getSymbol()).append(" :").append(groupName+"_"+pname+"_"+operatorType);
				}
				
			}
			
		}
	}
	
	private void sortsToSql(StringBuffer sqlBf,List<Sort> sorts){
		sqlBf.append(" order by ");
		String sortSql = "";
		for(Sort sort : sorts){
			String pname = sort.getParameterName();
			SortType sortType = sort.getSortType();
			if(sortType == SortType.ASC){
				sortSql = sortSql + pname + "," ;
			}else if(sortType == SortType.DESC){
				sortSql = sortSql + pname + " desc ,";
			}
		}
		sortSql = sortSql.substring(0, sortSql.length()-1);
		sqlBf.append(sortSql);
	}
	
}
