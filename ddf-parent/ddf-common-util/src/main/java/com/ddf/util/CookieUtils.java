package com.ddf.util;

import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

public class CookieUtils {

	public static void setCookie(HttpServletResponse response,String name,String value,int seconds){
		setCookie(response,null,name,value,seconds);
	}
	
	public static void setCookie(HttpServletResponse response,String name,String value){
		setCookie(response,null,name,value,null);
	}
	
	public static void setCookie(HttpServletResponse response,String domain,String name,String value,Integer seconds){
		try {
			if(StringUtil.isNotEmpty(value)){
				value = URLEncoder.encode(value,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cookie cookie=new Cookie(name,value);
		if(seconds!=null){
			cookie.setMaxAge(seconds); //60*60*24*15(15天) 
		}
		if(StringUtil.isNotEmpty(domain)){
			cookie.setDomain(domain);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public static String getCookieValue(HttpServletRequest request,String name){
		String value = null;
		Cookie [] cs=request.getCookies();
		if(cs!=null){  
		   for(int i=0;i<cs.length;i++){
		       Cookie c=cs[i];  
		       if(c.getName().equals(name)){
		    	   try {
		    		   value = URLDecoder.decode(c.getValue(),"UTF-8");
		    	   } catch (UnsupportedEncodingException e) {
						e.printStackTrace();
		    	   }
		    	   break;
		       }
		   }
		}
		return value;
	}
	
	public static  void removeCookie(HttpServletRequest request,HttpServletResponse response,String cookieName){
		
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
		     for(int i=0;i<cookies.length;i++)   
		     { 
		    	 if(cookieName.equals(cookies[i].getName())){
				      Cookie cookie = new Cookie(cookieName,null); 
				      cookie.setPath("/");
				      cookie.setMaxAge(0); 
				      response.addCookie(cookie); 
		    	 }
		     } 
		}
		
	}
}
