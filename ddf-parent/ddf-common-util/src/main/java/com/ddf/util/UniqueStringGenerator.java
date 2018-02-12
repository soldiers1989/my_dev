package com.ddf.util;

import java.text.SimpleDateFormat;
import java.util.Date;



public class UniqueStringGenerator {
	private static final int MAX_GENERATE_COUNT = 99999;
    private static int generateCount = 0;
    
    public static synchronized String getUniqueString(){
        return getUniqueString(false);
    }
    
    
    public static synchronized String getTimeUniqueString(){
    	return getUniqueString(true);
    }
    
    public static synchronized String getUniqueString(boolean isDateFromat){
    	if(generateCount > MAX_GENERATE_COUNT){
            generateCount = 0;
        }
        String uniqueNumber = "";
        if(isDateFromat){
        	SimpleDateFormat dateFromat = new SimpleDateFormat();
    		dateFromat.applyPattern("yyMMddHHmmssSSS");
            uniqueNumber = dateFromat.format(new Date())+ Integer.toString(generateCount);
    	}else{
    		uniqueNumber = Long.toString(System.currentTimeMillis()) + Integer.toString(generateCount);
    	}
        generateCount++;
        return uniqueNumber;
    }

    public static void main(String[] args){
    	for(int i=0;i<10;i++){
    		System.out.println(getUniqueString());
    		System.out.println(getTimeUniqueString());
    	}
    }
    
}
