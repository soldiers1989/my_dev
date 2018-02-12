package com.ddf.util;

import java.util.ArrayList;
import java.util.List;

public class SpiderUrlUtil {
	private static void getUrls(String urlTemplate,List<String> list){
		String beginSymbol = "{";
		String endSymbol = "}";
		String splitSymbol = ",";
		if(urlTemplate.contains(beginSymbol)&&urlTemplate.contains(endSymbol)){
			String parameters=urlTemplate.substring(urlTemplate.indexOf(beginSymbol)+1, urlTemplate.indexOf(endSymbol));
			String parametersStr=beginSymbol+parameters+endSymbol;
			String[] paraArray=parameters.split(splitSymbol);
			for(String str:paraArray){
				getUrls(urlTemplate.replace(parametersStr, str),list);
			}
		}else{
			list.add(urlTemplate);
		}
	}
	public static String[] getListUrl(String urlTemplate){
		List<String> list=new ArrayList<String>();
		getUrls(urlTemplate,list);
		String[] urlArray=new String[list.size()];
		for(int i=0;i<list.size();i++){
			urlArray[i]=list.get(i);
		}
		return urlArray;
	}
	public static void main(String[] args) {
		for(String str:getListUrl("http://search.t3.com.cn/queryJsonItem?img=0&pagesize=20&curpage={1,2,3,4,5,6}&ttb=1&order=5&ci={11,20,21,4085,61249}&callback=$.modelParse_search_result_0")){
			System.out.println(str);
		}
	}
}
