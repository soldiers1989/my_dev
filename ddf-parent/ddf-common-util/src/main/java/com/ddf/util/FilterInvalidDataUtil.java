package com.ddf.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤无效数据工具类
 * 
 * @author kaifa
 *
 */
public class FilterInvalidDataUtil {
	private static double firstPer=1.5;
	private static double secondPer=0.5;
	private static int length=10;
	public static List<String> getValidData(List<String> data,double downPer,double upPer) {
		if(!ListUtil.isEmpty(data)){
			List<Double> list = new ArrayList<Double>();
			for(String str:data){
				list.add(Double.valueOf(str));
			}
			int num = length < list.size() / 2 ? length : list.size()/2;
			list=sortList(list);
			double total=0;
			for(Double d:list){
				total+=d;
			}
			double avg=total/list.size();
			Double[] firstThresholds=calculateThreshold(avg, firstPer);
			List<Double> firstList=new ArrayList<Double>();
			for(int i=0;i<num;i++){
				if(list.get(i)>firstThresholds[0]){
					firstList.add(list.get(i));
				}
			}
			for(int i=num;i<list.size()-num;i++){
				firstList.add(list.get(i));
			}
			for(int i=list.size()-num;i<list.size();i++){
				if(list.get(i)<firstThresholds[1]){
					firstList.add(list.get(i));
				}
			}
			
			double total1=0;
			for(Double d:firstList){
				total1+=d;
			}
			double avg1=total1/firstList.size();
//			Double[] secondThresholds=calculateThreshold(avg1, secondPer);
			List<Double> secondList=new ArrayList<Double>();
			for(int i=0;i<firstList.size();i++){
				if(firstList.get(i)<avg1&&firstList.get(i)>avg1*downPer){
					secondList.add(firstList.get(i));
				}else if(firstList.get(i)>avg1&&firstList.get(i)<avg1*upPer){
					secondList.add(firstList.get(i));
				}else if(firstList.get(i)==avg1){
					secondList.add(firstList.get(i));
				}
			}
			List<String> result=new ArrayList<String>();
			for(Double d:secondList){
				result.add(String.valueOf(d));
			}
		return result;
		}
		return null;
	}
	private static List<Double> sortList(List<Double> array){
		for (int i = 0; i < array.size() - 1; i++) {
			for (int j = 0; j < array.size() - i - 1; j++) { 
				if (array.get(j) > array.get(j+1)) { // 把小的值交换到后面
					Double temp = array.get(j);
					array.set(j, array.get(j+1));
					array.set(j+1, temp);
				}
			}
		}
		return array;
	}
	
	private static Double[] calculateThreshold(Double d,Double percent){
		Double[] array=new Double[2];
		if(percent<1){
			array[0]=d*(1-percent);
			array[1]=d*(1+percent);
		}else{
			array[0]=d*(1/(1+percent));
			array[1]=d*(1+percent);
		}
		return array;
	}
	
	public static Double[] getValidDataRange(List<String> data,double downPer,double upPer){
		List<String> list=getValidData(data,downPer,upPer);
		if(!ListUtil.isEmpty(list)){
			Double[] result=new Double[2];
			result[0]=Double.valueOf(list.get(0));
			result[1]=Double.valueOf(list.get(list.size()-1));
			return result;
		}
		return null;
	}
	public static void main(String[] args) {
		List<String> data=new ArrayList<String>();
		data.add("1");
		data.add("200");
		data.add("10000");
		data.add("22000");
		data.add("18000");
		data.add("40000");
		data.add("40000");
		data.add("80000");
		data.add("60000");
		data.add("39000");
		data.add("100000");
		List<String> result=getValidData(data,0.5,1);
		for(String s:result){
			System.out.println(s);
		}
		System.out.println("************************************");
		Double[] array=getValidDataRange(data,0.5,1);
		for(Double s:array){
			System.out.println(s);
		}
	}
}
