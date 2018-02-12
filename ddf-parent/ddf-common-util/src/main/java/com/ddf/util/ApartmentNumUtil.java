package com.ddf.util;

public class ApartmentNumUtil {

	private static String[] array = new String[]{"零","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"};
	
	public static String getApartmentName4num(int number){
		String name = array[number];
		return name;
	}
	
	public static void main(String[] args) {
		System.out.println(getApartmentName4num(3));
		for(int i=1;i<=5;i++){
			System.out.println(getApartmentName4num(i));
		}
		
	}
}
