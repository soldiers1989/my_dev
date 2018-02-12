package com.ddf.entity.capital.eo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public enum WithdrawApplyBatchStatus {
	
	/**
	 * 待转账
	 */
	WAIT("未转账",1),
	/**
	 * 转账中
	 */
	PROCESSING("转账中",2),
	/**
	 * 转账成功
	 */
	SUCCESS("转账成功",3),
	/**
	 * 转账失败
	 */
	FAILED("转账失败",4),
	/**
	 * 已作废
	 */
	CANCELD("已作废",5);
	
	private String explain;
	private Integer sort;

	private WithdrawApplyBatchStatus(String explain,Integer sort) {
		this.explain = explain;
		this.sort = sort;
	}

	public static LinkedHashMap<String,String> toLinkedHashMap(){
    	List<WithdrawApplyBatchStatus> statusList = new ArrayList<WithdrawApplyBatchStatus>();
    	for (WithdrawApplyBatchStatus item : WithdrawApplyBatchStatus.values()) {
    		statusList.add(item);
    	}
    	
    	Collections.sort(statusList, new Comparator<WithdrawApplyBatchStatus>() {
            public int compare(WithdrawApplyBatchStatus arg0, WithdrawApplyBatchStatus arg1) {
                return arg0.getSort().compareTo(arg1.getSort());
            }
        });
    	
    	
    	LinkedHashMap<String,String> treeMap = new LinkedHashMap<String,String>();
    	for(WithdrawApplyBatchStatus status : statusList){
    		treeMap.put(status.name(), status.getExplain());
    	}
    	return treeMap;
    }
	
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
