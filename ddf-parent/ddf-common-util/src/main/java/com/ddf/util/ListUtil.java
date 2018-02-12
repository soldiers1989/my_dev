package com.ddf.util;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtil {

	public static <T> void add(T t,List<T> list){
		if(t!=null){
			list.add(t);
		}
	}
	
	public static <T> boolean isEmpty(List<T> list){
		if(list==null || list.size()==0){
			return true;
		}else{
			return false;
		}
	}
	
	public static <T> T getRandom(List<T> list){
		if(isEmpty(list)){
			return null;
		}
		int randomIndex = (int)(Math.random()*list.size());
		return list.get(randomIndex);
	}
	
	public static <T> T getRandom4filterRepeat(List<T> list){
		if(isEmpty(list)){
			return null;
		}
		
		Set<T> set = new HashSet<T>();
		for(T t : list){
			set.add(t);
		}
		
		List<T> filterList = new ArrayList<T>();
		for(T t : set){
			filterList.add(t);
		}
		
		return getRandom(filterList);
		
	}
	public static <T> List<T> getFilterList(List<T> list,int begInIndex,int size){
		List<T> newList=new ArrayList<T>();
		if(!isEmpty(list)){
			if(begInIndex<0||begInIndex>list.size()||size<=0||begInIndex+size>list.size()){
				return list;
			}
			for(int i=begInIndex;i<begInIndex+size;i++){
				newList.add(list.get(i));
			}
		}
		return newList;
	}
	public static List<T> quchong(List<T> list){
		if (list ==null) return null;
		Set<T> set=new HashSet<T>();
		set.addAll(list);
		list.clear();
		list.addAll(set);
		return list;
	}
	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		for(String i:getFilterList(list,0,2)){
			System.out.println(i);
		}
		
	}

}
