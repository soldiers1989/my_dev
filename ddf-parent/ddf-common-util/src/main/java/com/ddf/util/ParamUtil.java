package com.ddf.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 该类是一个记住参数的工具类。
 * 
 * 
 * 
 * 框架核心：
 * 1，参数保存。
 * 该工具把需要记住的页面url存入cookie，需要时从cookie中取出。
 * 实际上不必把需要记住每个页面url都这样放入cookie：CookieUtils.setCookie(pagename, params)，
 * 这样的话有两个缺点：1，每个业务方法都要写入该代码。2，cookie中数据量会变得很庞大。
 * 所以这里可以做成面向切面的方法：我们把所有需要记住参数的url中加入标注：“/list_rmb”，然后过滤器中判断url中是否包含“/list_rmb”，
 * 如果包含，那么CookieUtils.setCookie("list_rmb", params)。
 * 这样的话有两个优点：1，每个业务方法都不用写记住代码。2，cookie中只有一个：“list_rmb”（因为cookie的实现name相同则覆盖关系）。
 * 这样的缺点是cookie只能记住一个页面，如果我需要多记住几个无法实现。比如列表页a，中有子列表b，子列表页有编辑页c，当c编辑后回到b，b返回回到a，此时b会覆盖a的参数。
 * 为了解决此问题，我们按照面对层次的方式来实现。即url的名字有多层写法:/list_rmb,/list_rmb_1,/list_rmb_2,/list_rmb_3...，其中/list_rmb和/list_rmb_1意义相同。
 * 同层之间的不会覆盖，如上例a的url可以写成：a/list_rmb_1；b的url可以写成：a/list_rmb_2；
 * 
 * 总结：只要url有包含“/list_rmb”，该url就会被过滤器拦截，然后保存参数到cookie中。
 * 
 * 2，参数读取。
 * 当我们访问带“/list_rmb”页面时，我们有时需要的参数就是当前参数（request），有时候需要的是记住的参数（cookie），
 * 到底是从request取，还是从cookie中取，根据request中是否有参数：“p_rmb=任意不为空字符串”。如果有，表示从cookie中读。
 * p_rmb=任意不为空字符串还支持更新参数的功能：reuqest中的参数会新增到cookie中，如果有重复，则覆盖cookie中的参数。
 * 总结举例：
 * cookieParams = "age=12&name=虎君"
 * requestParams = "p_rmb=true&age=18&color=red"
 * 最终重定向的url为："age=18&color=red&name=虎君"
 * 
 * 
 * 具体代码：
 * 
 * 1，其中框架代码处于过滤器中：
 * 
 *  拦截具有记住参数标记的url
  	if(requestPage.indexOf("/list_rmb")>=0){
        如果request中读取cookie中的参数。
		if(StringUtil.isNotEmpty(request.getParameter("p_rmb"))){
			根据request中的参数和cookie中的参数，生成一个新的url。
			String remeberRequestURL = ParamUtil.getRequestURL4requestParams4cookieParams(request);
			重定向到新的url中
			response.sendRedirect(remeberRequestURL);
			return;
		}
		记住参数
		ParamUtil.saveRequestParams2Cookie(request);
	}
 * 
 * 2，业务代码（******************程序员请注意*********************************）；
 * 一：保存参数的页面。
 * 需要拥有记住的url中必须包含"/list_rmb"，"/list_rmb_1,/list_rmb_2,/list_rmb_3"表示页面层次（不覆盖）。（/list_rmb是/list_rmb_1的简写，意义一样） 
 * 例如：
 * 1,@RequestMapping(value="/book/list_rmb")
 * 2,@RequestMapping(value="/animal/list_rmb_1")
 * 3,@RequestMapping(value="/student/list_rmb_2")
 * 这里1,2,3处的页面参数都会存入cookie，其中1,2是参数会覆盖，2,3是不覆盖
 * 
 * 二：读取参数。
 * 当需要读取cookie记住参数的时候，在访问页面时需要加上：“p_rmb=任意不为空字符串”。没有则表示参数从新的request中读取。
 * 例如：
 * href = "/book/list_rmb?p_rmb=true"	//从cookie中读取
 * href = "/book/list_rmb" 	//从request读取。
 * 
 * 当需要从cookie中读取，并且需要更新参数可以这么写：
 * href = "/book/list_rmb?p_rmb=true&age=12"
 * 这时如果cookie参数中有age，则会把age的值更新为12；如果没有，则会增加age=12的参数。
 */
public class ParamUtil {

	/**
	 * 根据rquest中参数和cookie中参数，返回新的url。
	 * reuqest中的参数会新增到cookie中，如果有重复，则覆盖cookie中的参数。
	 * @param request
	 * @return
	 */
	public static String getRequestURL4requestParams4cookieParams(HttpServletRequest request){
//		String implUrlRmbMark = ParamUtil.getImplUrlRmbMark(request);
		String requestPage=getRequestPage(request);
		String requestURI = getURL4request(request);
		String requestParams = ParamUtil.getParams4request(request);
		String cookieParams = ParamUtil.getParams4cookie(request,requestPage);
		
		Map<String, String> oldMap = getUrlParams(cookieParams);
		Map<String, String> newMap = getUrlParams(requestParams);
		
		for (Map.Entry<String, String> entry : newMap.entrySet()) {
			String newKey = entry.getKey();
			String newVal = entry.getValue();
			oldMap.put(newKey, newVal);
		}
		oldMap.remove("p_rmb");
		String newParams= getUrlParamsByMap(oldMap);
		if(StringUtil.isNotEmpty(newParams)){
			return requestURI + "?" + newParams;
		}else{
			return requestURI;
		}
		
	}
	
	public static void saveRequestParams2Cookie(HttpServletRequest request,HttpServletResponse response){
//		String implUrlRmbMark = ParamUtil.getImplUrlRmbMark(request);
		String requestPage=getRequestPage(request);
		String requestParams = ParamUtil.getParams4request(request);
		ParamUtil.setParams2cookie(response,requestPage,requestParams);
	}
	private static String getRequestPage(HttpServletRequest request){
		String requestURI = request.getRequestURI().toString();
		String contextURL = request.getContextPath().toString();
		String requestPage = requestURI.replace(contextURL, "");
		return requestPage;
	}
	/*private static String getImplUrlRmbMark(HttpServletRequest request){
		String implUrlRmbMark = "";
		
		String requestURI = request.getRequestURI().toString();
		int beginIndex = requestURI.indexOf("/list_rmb");
		int endIndex = requestURI.indexOf("/", beginIndex+1);
		if(endIndex==-1){
			endIndex = requestURI.length();
		}
		String urlRmbMark = requestURI.substring(beginIndex,endIndex);
		String[] arr = urlRmbMark.split("_");
		if(arr.length==2){
			implUrlRmbMark =  urlRmbMark+"_1";
		}else{
			implUrlRmbMark =  urlRmbMark;
		}
		return implUrlRmbMark;
	}*/
	/**
	 * 从request中获得请求url
	 * @param request
	 * @return
	 */
	private static String getURL4request(HttpServletRequest request){
		String requestURL = request.getRequestURL().toString();
		return requestURL;
	}
	
	/**
	 * 从request中获得请求参数
	 * @param request
	 * @return
	 */
	private static String getParams4request(HttpServletRequest request){
		String params = request.getQueryString();
		return params;
	}
	
	/**
	 * 从cookie中获得请求参数
	 * @return
	 */
	private static String getParams4cookie(HttpServletRequest request,String implUrlRmbMark){
		String params = CookieUtils.getCookieValue(request,implUrlRmbMark);
		return params;
	}
	
	/**
	 * 设置参数到cookie中
	 */
	private static void setParams2cookie(HttpServletResponse response,String implUrlRmbMark,String params){
		CookieUtils.setCookie(response,implUrlRmbMark, params);
	}
	
	
	public static void main(String[] args){
		
	}
	
	
    /**
	 * 将url参数转换成map
	 * @param param aa=11&bb=22&cc=33
	 * @return
	 */
	private static Map<String, String> getUrlParams(String param) {
		Map<String, String> map = new HashMap<String, String>(0);
		if (StringUtil.isEmpty(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 1){
				map.put(p[0],"");
			}else if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 将map转换成url
	 * @param map
	 * @return
	 */
	private static String getUrlParamsByMap(Map<String, String> map) {
		if (map == null || map.size()==0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}

}
