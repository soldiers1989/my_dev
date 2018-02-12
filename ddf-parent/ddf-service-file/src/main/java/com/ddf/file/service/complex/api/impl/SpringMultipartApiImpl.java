package com.ddf.file.service.complex.api.impl;

import com.ddf.file.service.complex.api.FastdfsService;
import com.ddf.file.service.complex.api.SpringMultipartApi;
import com.ddf.util.ImgUtil;
import com.ddf.util.ListUtil;
import com.ddf.util.StringUtil;

import sun.misc.BASE64Decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class SpringMultipartApiImpl implements SpringMultipartApi{
	
	@Autowired
    private FastdfsService fastdfsService;
	
	private static Logger logger = LoggerFactory.getLogger(SpringMultipartApiImpl.class);
	

	
	private String uploadFile(MultipartFile file) throws IOException {
		String sucessPath = null;
		String fileName = file.getOriginalFilename();
		if(StringUtil.isNotEmpty(fileName)){
			String fileExtName = StringUtil.getFileExtName(fileName);
			InputStream inputStream = file.getInputStream();
			inputStream=ImgUtil.rotateImg4stream(inputStream, fileExtName);
			sucessPath = fastdfsService.saveFile4inputStream(inputStream, fileExtName);
		}
		return sucessPath;
	}
	
	
	@Override
	public String upload(HttpServletRequest request) {
		String sucessPath = null;
		try{
			CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
			//判断 request 是否有文件上传,即多部分请求
			if(multipartResolver.isMultipart(request)){
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
				//取得request中的所有文件名（parameterName not OriginalFilename）
				Iterator<String> iter = multiRequest.getFileNames();
				//只取第一个type=file的input
				if(iter.hasNext()){
					String multiParameterName = iter.next();
					//只取input中第一个file
					MultipartFile file = multiRequest.getFile(multiParameterName);
					sucessPath = uploadFile(file);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
	    	logger.error(ex.getMessage(),ex);
		}
		return sucessPath;
	}
	
	@Override
	public List<String> batchUpload4sameFileName(HttpServletRequest request){
		List<String> list = new ArrayList<String>();
		try{
			CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
			//判断 request 是否有文件上传,即多部分请求
			if(multipartResolver.isMultipart(request)){
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
				//取得request中的所有文件名（parameterName not OriginalFilename）
				Iterator<String> iter = multiRequest.getFileNames();
				//只取第一个type=file的input
				if(iter.hasNext()){
					String multiParameterName = iter.next();
					//取input中的所有file
					List<MultipartFile> files = multiRequest.getFiles(multiParameterName);
					for(MultipartFile file : files){
						String sucessPath = uploadFile(file);
						if(StringUtil.isNotEmpty(sucessPath)){
							list.add(sucessPath);
						}
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
	    	logger.error(ex.getMessage(),ex);
		}
		return list;
	}
	
	@Override
	public Map<String,String> batchUpload4diffFileName(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		try{
			CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
			//判断 request 是否有文件上传,即多部分请求
			if(multipartResolver.isMultipart(request)){
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
				//取得request中的所有文件名（parameterName not OriginalFilename）
				Iterator<String> iter = multiRequest.getFileNames();
				//取得所有type=file的input
				while(iter.hasNext()){
					String multiParameterName = iter.next();
					//只取input中第一个file
					MultipartFile file = multiRequest.getFile(multiParameterName);
					String sucessPath = uploadFile(file);
					if(StringUtil.isNotEmpty(sucessPath)){
						map.put(multiParameterName, sucessPath);
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
	    	logger.error(ex.getMessage(),ex);
		}
		return map;
	}

	
	


	

}
