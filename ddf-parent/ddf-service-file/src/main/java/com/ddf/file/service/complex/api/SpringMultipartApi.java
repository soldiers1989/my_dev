package com.ddf.file.service.complex.api;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface SpringMultipartApi {

	String upload(HttpServletRequest request);
	List<String> batchUpload4sameFileName(HttpServletRequest request);
	Map<String,String> batchUpload4diffFileName(HttpServletRequest request);
	
	
}
