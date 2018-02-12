package com.ddf.entity.base.query;

public class SortQuery {
	private String sortSql;
	
	
	public void setSortSql(String sortSql) {
		if(checkSqlinj(sortSql)){
			this.sortSql = null;
		}else{
			this.sortSql = sortSql;
		}
	}
	
	public String getSortSql() {
		return sortSql;
	}


	public boolean checkSqlinj(String str){
		if(str==null){
			return false;
		}
		String injStr = "'|and|exec|insert|select|delete|update|drop|*|%|chr|mid|master|truncate|char|declare|;|or |-|+";
		String[] injArr = injStr.split("\\|");
		for (String inj : injArr) {
			if(str.contains(inj)){
				return true;
			}
		}
		return false;
	}
	

}
