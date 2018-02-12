package com.ddf.entity.capital.eo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public enum WithdrawApplyStatus {
	/**
	 * 等待审核
	 */
	wait_review("未审核",1),
	/**
	 * 审核成功
	 */
	review_pass("审核通过",2),
	/**
	 * 审核驳回
	 */
	review_reject("审核驳回",3),
	/**
	 * 等待转账
	 */
	trade_wait("等待转账",4),
	/**
	 * 转账成功
	 */
	trade_success("转账成功",5),
	/**
	 * 转账失败
	 */
	trade_failed("转账失败",6);
	
	private String explain;
	private Integer sort;

	private WithdrawApplyStatus(String explain,Integer sort) {
		this.explain = explain;
		this.sort = sort;
	}
	
	public static LinkedHashMap<String,String> toLinkedHashMap(){
    	List<WithdrawApplyStatus> statusList = new ArrayList<WithdrawApplyStatus>();
    	for (WithdrawApplyStatus item : WithdrawApplyStatus.values()) {
    		statusList.add(item);
    	}
    	
    	Collections.sort(statusList, new Comparator<WithdrawApplyStatus>() {
            public int compare(WithdrawApplyStatus arg0, WithdrawApplyStatus arg1) {
                return arg0.getSort().compareTo(arg1.getSort());
            }
        });
    	
    	
    	LinkedHashMap<String,String> treeMap = new LinkedHashMap<String,String>();
    	for(WithdrawApplyStatus status : statusList){
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
