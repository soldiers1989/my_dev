package com.ddf.file.service.complex.api.impl;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.file.FastdfsConfig;
import com.ddf.file.service.complex.api.FastdfsService;
import com.ddf.util.FileTypeUtil;
import com.ddf.util.StreamUtil;
import com.ddf.util.StringUtil;

import sun.misc.BASE64Decoder;

@Service
public class FastdfsServiceImpl implements FastdfsService{
	
	/*****************************************************************重写Fastdfs_client部分***************************************************/
	@Autowired
	private FastdfsConfig fastdfsConfig;

	public void extClientGlobal_init() throws FileNotFoundException, IOException, MyException
	{
  		String[] szTrackerServers;
			String[] parts;
			

			ClientGlobal.g_connect_timeout = fastdfsConfig.getConnect_timeout();
  		if (ClientGlobal.g_connect_timeout < 0)
  		{
  			ClientGlobal.g_connect_timeout = ClientGlobal.DEFAULT_CONNECT_TIMEOUT;
  		}
  		ClientGlobal.g_connect_timeout *= 1000; //millisecond
  		
  		ClientGlobal.g_network_timeout = fastdfsConfig.getNetwork_timeout();
  		if (ClientGlobal.g_network_timeout < 0)
  		{
  			ClientGlobal.g_network_timeout = ClientGlobal.DEFAULT_NETWORK_TIMEOUT;
  		}
  		ClientGlobal.g_network_timeout *= 1000; //millisecond

  		ClientGlobal.g_charset = fastdfsConfig.getCharset();
  		if (ClientGlobal.g_charset == null || ClientGlobal.g_charset.length() == 0)
  		{
  			ClientGlobal.g_charset = "ISO8859-1";
  		}
  		
  		szTrackerServers = fastdfsConfig.getTracker_server().split("\\;");
  		if (szTrackerServers == null)
  		{
  			throw new MyException("item \"tracker_server\"  not found");
  		}
  		
  		InetSocketAddress[] tracker_servers = new InetSocketAddress[szTrackerServers.length];
  		for (int i=0; i<szTrackerServers.length; i++)
  		{
  			parts = szTrackerServers[i].split("\\:", 2);
  			if (parts.length != 2)
  			{
  				throw new MyException("the value of item \"tracker_server\" is invalid, the correct format is host:port");
  			}
  			
  			tracker_servers[i] = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
  		}
  		ClientGlobal.g_tracker_group = new TrackerGroup(tracker_servers);
  		
  		ClientGlobal.g_tracker_http_port = fastdfsConfig.getHttp_tracker_http_port();
  		ClientGlobal.g_anti_steal_token = fastdfsConfig.isHttp_anti_steal_token();
  		if (ClientGlobal.g_anti_steal_token)
  		{
  			ClientGlobal.g_secret_key = fastdfsConfig.getHttp_secret_key();
  		}
	}
    /*****************************************************************重写Fastdfs_client部分***************************************************/
    
    private static Logger logger = LoggerFactory.getLogger(FastdfsServiceImpl.class);
	
	
    
    private StorageClient1 getStorageClient1() {
    	TrackerClient trackerClient = null;
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = null;
    	try {
	        extClientGlobal_init();
	
	        trackerClient = new TrackerClient();
	        trackerServer = trackerClient.getConnection();
	        storageServer = trackerClient.getStoreStorage(trackerServer);
	        storageClient1 = new StorageClient1(trackerServer, storageServer);
		} catch (Exception ex) {
			ex.printStackTrace();
	    	logger.error(ex.getMessage(),ex);
		}
    	
        return storageClient1;
    }

    

    @Override
    public String saveFile4inputStream(InputStream inputStream,String fileExtName){
		try {
			byte[] fileBuff = StreamUtil.readStream(inputStream);
			return getStorageClient1().upload_file1(fileBuff, fileExtName, null);
		} catch(Exception ex){
	    	ex.printStackTrace();
	    	logger.error(ex.getMessage(),ex);
	    }
		return null;  
    }
    
    @Override
    public String saveFile4httpUrl(String httpUrl){
		HttpClient client = new HttpClient();
		//链接超时
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);  
		//读取超时
		client.getHttpConnectionManager().getParams().setSoTimeout(10000);
		client.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		GetMethod method = new GetMethod();
		String filename = null;
		try {
			URI uri = new URI(httpUrl, false);
			method.setURI(uri);
			method.addRequestHeader("Connection", "close");
			method.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
			int code = client.executeMethod(method);
			//System.out.println(httpUrl + " status: " + code);
			if (code == HttpStatus.SC_OK) {	
				InputStream is = method.getResponseBodyAsStream();
				String fileExtName = StringUtil.getFileExtName(httpUrl);
				//没找到合适的后缀名，通过流的形式获得文件名
				if(StringUtil.isEmpty(fileExtName)||fileExtName.length()>=6){
					List<byte[]> bytes = StreamUtil.copy2manyBytes(is,2);
					fileExtName = FileTypeUtil.getFileTypeByStream(StreamUtil.subBytes(bytes.get(0), 10));
					filename = getStorageClient1().upload_file1(bytes.get(1), fileExtName, null);
				}else{
					byte[] bytes = StreamUtil.readStream(is);
					filename = getStorageClient1().upload_file1(bytes, fileExtName, null);
				}
				return filename;
			}else{
				logger.error("文件“"+httpUrl+"”http下载失败");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
	    	logger.error(ex.getMessage(),ex);
		} finally {
			method.releaseConnection();
			method =null;
			client = null;
		}
		
		return null;
	}
    
    
    @Override
	public String upload4base64(String base64) {
		if (StringUtil.isNotEmpty(base64)) {
			try {
				String fileExtName = base64.substring(base64.indexOf("image") + 6, base64.indexOf(";"));
				String file = base64.split(",")[1];
				BASE64Decoder decoder = new BASE64Decoder();  
				byte[] decoderBytes = decoder.decodeBuffer(file);
				InputStream inputStream = new ByteArrayInputStream(decoderBytes);
				//上传至fastdfs
				String sucessPath = saveFile4inputStream(inputStream, fileExtName);
				return sucessPath;
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return null;
	}
}
