package com.ddf.file.service.complex.api;

import java.io.InputStream;

public interface FastdfsService {
	
	/*存入文件*/
	String saveFile4inputStream(InputStream inputStream,String fileExtName);
	
	String saveFile4httpUrl(String httpUrl);
	
	String upload4base64(String base64);
}
