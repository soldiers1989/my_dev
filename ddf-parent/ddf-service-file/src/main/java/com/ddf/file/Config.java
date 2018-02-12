package com.ddf.file;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class Config {
	
	@Value("${file.fastdfs.tracker_server.url}")
	private String fileFastdfsTrackerServerUrl;
	
	@Bean   
    public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
		factory.setMaxFileSize("1024MB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("10240MB");
		return factory.createMultipartConfig();
    }
	
	
	@Bean   
    public FastdfsConfig fastdfsConfig() {
		FastdfsConfig fastdfsConfig = new FastdfsConfig();
		fastdfsConfig.setConnect_timeout(2);
		fastdfsConfig.setNetwork_timeout(30);
		fastdfsConfig.setCharset("UTF-8");
		fastdfsConfig.setHttp_tracker_http_port(8080);
		fastdfsConfig.setHttp_anti_steal_token(false);
		fastdfsConfig.setHttp_secret_key("FastDFS1234567890");
		fastdfsConfig.setTracker_server(fileFastdfsTrackerServerUrl);
		return fastdfsConfig;
    }  


}
