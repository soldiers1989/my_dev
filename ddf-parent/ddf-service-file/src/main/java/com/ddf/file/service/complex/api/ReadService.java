package com.ddf.file.service.complex.api;

import java.util.List;

public interface ReadService {
	String getHttpUrl(String filePath);
	List<String> getHttpUrls(List<String> filePaths);
	
	String getHttpUrl4m(String filePath,String width,String height);
	String getHttpUrl4t(String filePath,String width,String height);

	String getWpHttpUrl(String filePath);
	List<String> getWpHttpUrls(List<String> filePaths);
	String getWpHttpUrl4m(String filePath,String width,String height);
	String getWpHttpUrl4t(String filePath,String width,String height);
}
