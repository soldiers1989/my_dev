package com.ddf.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil	 {
	private static int MAX_TOTAL = 200;									//最大总连接数
	private static int DEFAULT_MAX_PERROUTE = 20;						//默认最大连接数
	private static int HOST = 80;										//最大连接主机
	private static int MAX_PERROUTE = 50;								//主机的最大连接数
	private static int CONNECTION_REQUEST_TIMEOUT = 6000;				//请求超时
	private static int CONNECT_TIMEOUT = 6000;							//连接超时
	private static int SOCKET_TIMEOUT = 6000;
	private static PoolingHttpClientConnectionManager cm = null;
	static{
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(MAX_TOTAL);
		cm.setDefaultMaxPerRoute(DEFAULT_MAX_PERROUTE);
		HttpHost localhost = new HttpHost("locahost", HOST);
		cm.setMaxPerRoute(new HttpRoute(localhost), MAX_PERROUTE);
	}
	public static String executeGet(String urlWithParams,ContentType contentType){
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		HttpGet httpget = new HttpGet(urlWithParams);
		try {
			// 配置请求的超时设置
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
					.setConnectTimeout(CONNECT_TIMEOUT)
					.setSocketTimeout(SOCKET_TIMEOUT).build();
			httpget.setConfig(requestConfig);
			String jsonStr = null;
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			jsonStr = EntityUtils.toString(entity, contentType.getCharset());
			return jsonStr;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			httpget.releaseConnection();
		}
		
	}
	
	public static String executePost(String url,Map<String,String> map,ContentType contentType){
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Content-Type", contentType.getMimeType());
		try {
			if (map != null) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				Set<String> set = map.keySet();
				for (String key : set) {
					params.add(new BasicNameValuePair(key, map.get(key)));
				}
				httppost.setEntity(new UrlEncodedFormEntity(params));
			}
			String jsonStr = null;
				CloseableHttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				jsonStr = EntityUtils.toString(entity, contentType.getCharset().toString());
				return StringUtil.decodeUTF(jsonStr);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			httppost.releaseConnection();
		}
	}
	/**
	 * 通过流的方式发post请求
	 * @param url
	 * @param param
	 * @param contentType
	 * @return
	 */
	public static String post4byte(String url,String param,Charset charset){
		return post4byte(url,param,null,charset);
	}
	
	public static String post4byte(String url,String param,Map<String,String> headerMap,Charset charset){
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		HttpPost httppost = new HttpPost(url);
		if(headerMap!=null){
			buildHeader(httppost,headerMap);
		}
		try {
			if (StringUtil.isNotEmpty(param)) {
				ByteArrayEntity byteArrayEntity=new ByteArrayEntity(param.getBytes());
				httppost.setEntity(byteArrayEntity);
			}
			String jsonStr = null;
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			jsonStr = EntityUtils.toString(entity, charset);
			return StringUtil.decodeUTF(jsonStr);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			httppost.releaseConnection();
		}
	}
	
	public static String put4byte(String url,String param,Map<String,String> headerMap,Charset charset){
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		HttpPut httpPut = new HttpPut(url);
		if(headerMap!=null){
			buildHeader(httpPut,headerMap);
		}
		try {
			if (StringUtil.isNotEmpty(param)) {
				ByteArrayEntity byteArrayEntity=new ByteArrayEntity(param.getBytes());
				httpPut.setEntity(byteArrayEntity);
			}
			String jsonStr = null;
			CloseableHttpResponse response = httpclient.execute(httpPut);
			HttpEntity entity = response.getEntity();
			jsonStr = EntityUtils.toString(entity, charset);
			return StringUtil.decodeUTF(jsonStr);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			httpPut.releaseConnection();
		}
	}
	
	public static String delete4byte(String urlWithParams,Map<String,String> headerMap,Charset charset){
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		HttpDelete httpDelete = new HttpDelete(urlWithParams);
		if(headerMap!=null){
			buildHeader(httpDelete,headerMap);
		}
		try {
			// 配置请求的超时设置
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
					.setConnectTimeout(CONNECT_TIMEOUT)
					.setSocketTimeout(SOCKET_TIMEOUT).build();
			httpDelete.setConfig(requestConfig);
			String jsonStr = null;
			CloseableHttpResponse response = httpclient.execute(httpDelete);
			HttpEntity entity = response.getEntity();
			jsonStr = EntityUtils.toString(entity, charset);
			return jsonStr;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			httpDelete.releaseConnection();
		}
		
	}
	
	public static String get4byte(String urlWithParams,Map<String,String> headerMap,Charset charset){
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		HttpGet httpget = new HttpGet(urlWithParams);
		if(headerMap!=null){
			buildHeader(httpget,headerMap);
		}
		CloseableHttpResponse response = null;
		try {
			// 配置请求的超时设置
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
					.setConnectTimeout(CONNECT_TIMEOUT)
					.setSocketTimeout(SOCKET_TIMEOUT).build();
			httpget.setConfig(requestConfig);
			String jsonStr = null;
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			jsonStr = EntityUtils.toString(entity, charset);
			return jsonStr;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			httpget.releaseConnection();
		}
		
	}
	
	/*public static void buildHeader(HttpPost httppost,Map<String,String> headerMap){
		for (Map.Entry<String, String> header :headerMap.entrySet()){
			httppost.setHeader(header.getKey(),header.getValue());
		}
	}*/
	
	public static void buildHeader(HttpRequestBase httpRequestBase,Map<String,String> headerMap){
		for (Map.Entry<String, String> header :headerMap.entrySet()){
			httpRequestBase.setHeader(header.getKey(),header.getValue());
		}
	}
	
	public static void main(String[] args) {
		/*String baseUrl="https://a1.easemob.com/1193170219178738/test1/token";
		Map<String ,String> params=new HashMap<String,String>();
		String paramStr="{\"grant_type\":\"client_credentials\",\"client_id\":\"YXA6aVmHsA6kEeevx-9OakqNuw\",\"client_secret\":\"YXA65QuzToANjnRlpync8_fnmU0KBfI\"}";
		params.put("Request Body", paramStr);
		//executePostUseByte(baseUrl, paramStr,ContentType.APPLICATION_JSON);	
		String baseUrl="https://a1.easemob.com/1193170219178738/test1/users/xiaohu4";
		Map<String, String> headerMap = new HashMap<String,String>();
		String token ="YWMtulRhbhBuEeeRxAnhNEB3_gAAAAAAAAAAAAAAAAAAAAFpWYewDqQR56_H705qSo27AgMAAAFa_4Ey3gBPGgBmNLYZoCrH698eSzBAHRFoqZHk5wGpb3KSYTbNcordJw";
		headerMap.put("Authorization", "Bearer "+token);
		System.out.println(delete4byte(baseUrl, headerMap, Charset.forName("utf-8")));*/
		String baseUrl = "http://10.0.20.211:9200/spider_data/_mapping/second_house_data";
		String paramStr = "{\"properties\":{\"city\":{\"type\":\"text\",\"fielddata\":true}}}";
		System.out.println(post4byte(baseUrl, paramStr, Charset.forName("utf-8")));
	}
}
