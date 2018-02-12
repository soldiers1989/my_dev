package com.ddf.file.service.complex.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ddf.entity.constant.Constant;
import com.ddf.file.service.complex.api.ReadService;
import com.ddf.util.ListUtil;
import com.ddf.util.StringUtil;

@Service
public class ReadServiceImpl implements ReadService {
	
	/*****************************************************************重写Fastdfs_client部分***************************************************/

	@Value("${file.server.url}")
	private String fileServerUrl;
	@Value("${file.wp_server.url}")
	private String fileWpServerUrl;
    
	
    @Override
    public String getHttpUrl(String filePath){
    	return getHttpUrl(filePath,false);
    }
    
    @Override
    public List<String> getHttpUrls(List<String> filePaths){
    	return getHttpUrls(filePaths,false);
    }
    @Override
    public String getWpHttpUrl(String filePath){
    	return getHttpUrl(filePath,true);
    }
    
    @Override
    public List<String> getWpHttpUrls(List<String> filePaths){
    	return getHttpUrls(filePaths,true);
    }
  
    
    private String getHttpUrl(String filePath,boolean needWt){
    	String serverUrl = "";
    	if(needWt){
    		serverUrl = fileWpServerUrl;
    	}else{
    		serverUrl = fileServerUrl;
    	}
    	if(!StringUtil.getLastChar(serverUrl).equals("/")){
    		serverUrl = serverUrl + "/" ;
    	}
    	return serverUrl + filePath;
    }
    
    
    private List<String> getHttpUrls(List<String> filePaths,boolean needWt){
    	List<String> serverUrls = new ArrayList<String>();
    	if(!ListUtil.isEmpty(filePaths)){
    		for(String filePath : filePaths){
    			serverUrls.add(getHttpUrl(filePath,needWt));
    		}
    	}
    	return serverUrls;
    }
    
   
	@Override
	public String getHttpUrl4m(String filePath, String width, String height) {
		String httpUrl=getHttpUrl(filePath);
		String httpUrl4m=httpUrl.substring(0, httpUrl.lastIndexOf('.'))+".m"+width+"x"+height+
				httpUrl.substring(httpUrl.lastIndexOf('.'),httpUrl.length());
		return httpUrl4m;
	}
	@Override
	public String getHttpUrl4t(String filePath, String width, String height) {
		String httpUrl=getHttpUrl(filePath);
		String httpUrl4t=httpUrl.substring(0, httpUrl.lastIndexOf('.'))+".t"+width+"x"+height+
				httpUrl.substring(httpUrl.lastIndexOf('.'),httpUrl.length());
		return httpUrl4t;
	}
	@Override
	public String getWpHttpUrl4m(String filePath, String width, String height) {
		String wapHttpUrl=getWpHttpUrl(filePath);
		String wapHttpUrl4m=wapHttpUrl.substring(0, wapHttpUrl.lastIndexOf('.'))+".m"+width+"x"+height+
				wapHttpUrl.substring(wapHttpUrl.lastIndexOf('.'),wapHttpUrl.length());
		return wapHttpUrl4m;
	}
	@Override
	public String getWpHttpUrl4t(String filePath, String width, String height) {
		String wapHttpUrl=getWpHttpUrl(filePath);
		String wapHttpUrl4t=wapHttpUrl.substring(0, wapHttpUrl.lastIndexOf('.'))+".t"+width+"x"+height+
				wapHttpUrl.substring(wapHttpUrl.lastIndexOf('.'),wapHttpUrl.length());
		return wapHttpUrl4t;
	}
	
}
