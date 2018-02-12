package com.ddf.file.api;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.member.dto.BankCard;
import com.ddf.entity.response.ApiResponse;
import com.ddf.file.service.complex.api.FastdfsService;
import com.ddf.file.service.complex.api.ReadService;
import com.ddf.file.service.complex.api.SpringMultipartApi;
import com.ddf.file.service.complex.api.impl.ReadServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class FileApi extends BaseApi{
	
	@Autowired
	private FastdfsService fastdfsService;
	@Autowired
	private SpringMultipartApi springMultipartApi;
	@Autowired
	private ReadService readService;

	@ApiOperation(value = "以base64字符串形式上传")
	@RequestMapping(value = "/file/base64/upload", method = { RequestMethod.POST })
	public ApiResponse<String> upload4base64(@ApiParam(value = "base64", required = true) @RequestParam(required = true) String base64) {
		return ApiResponse.success(fastdfsService.upload4base64(base64));
	}
	
	@ApiOperation(value = "以http地址上传")
	@RequestMapping(value = "/file/httpUrl/upload", method = { RequestMethod.POST })
	public ApiResponse<String> upload4httpUrl(@ApiParam(value = "httpUrl", required = true) @RequestParam(required = true) String httpUrl) {
		return ApiResponse.success(fastdfsService.saveFile4httpUrl(httpUrl));
	}
	
	@ApiOperation(value = "文件的形式上传，一个input_name上传一个文件")
	@RequestMapping(value = "/file/upload", method = { RequestMethod.POST })
	public ApiResponse<String> fileUpload(HttpServletRequest request) {
		return ApiResponse.success(springMultipartApi.upload(request));
	}
	
	@ApiOperation(value = "文件的形式上传，一个input_name上传多个文件")
	@RequestMapping(value = "/file/sameFileName/batchupload", method = { RequestMethod.POST })
	public ApiResponse<List<String>> fileUpload4sameFileName(HttpServletRequest request) {
		return ApiResponse.success(springMultipartApi.batchUpload4sameFileName(request));
	}
	@ApiOperation(value = "文件的形式上传，多个input_name上传多个文件")
	@RequestMapping(value = "/file/diffFileName/batchupload", method = { RequestMethod.POST })
	public ApiResponse<Map<String,String>> fileUpload4diffFileName(HttpServletRequest request) {
		return ApiResponse.success(springMultipartApi.batchUpload4diffFileName(request));
	}
	
	
	
	/******访问*******/
	@ApiOperation(value = "通过文件路径获得http访问路径")
	@RequestMapping(value = "/file/httpUrl/query", method = { RequestMethod.GET })
	public ApiResponse<String> getHttpUrl(@ApiParam(value = "filePath", required = true) @RequestParam(required = true) String filePath,
											@ApiParam(value = "waterPrint", required = false) @RequestParam(required = false) boolean waterPrint) {
		if(waterPrint){
			return ApiResponse.success(readService.getWpHttpUrl(filePath));
		}
		return ApiResponse.success(readService.getHttpUrl(filePath));
	}
	@ApiOperation(value = "通过一组文件路径获得一组http访问路径")
	@RequestMapping(value = "/file/httpUrls/batchquery", method = { RequestMethod.GET })
	public ApiResponse<List<String>> getHttpUrls(@ApiParam(value = "filePaths", required = true) @RequestParam(required = true) List<String> filePaths,
													@ApiParam(value = "waterPrint", required = false) @RequestParam(required = false) boolean waterPrint) {
		if(waterPrint){
			return ApiResponse.success(readService.getWpHttpUrls(filePaths));
		}
		return ApiResponse.success(readService.getHttpUrls(filePaths));
	}
	
	@ApiOperation(value = "通过文件路径获得http以m方式压缩的访问路径")
	@RequestMapping(value = "/file/httpUrl4m/query", method = { RequestMethod.GET })
	public ApiResponse<String> getHttpUrl4m(@ApiParam(value = "filePath", required = true) @RequestParam(required = true) String filePath,
											@ApiParam(value = "width", required = true) @RequestParam(required = true) String width,
											@ApiParam(value = "height", required = true) @RequestParam(required = true) String height,
											@ApiParam(value = "waterPrint", required = false) @RequestParam(required = false) boolean waterPrint) {
		if(waterPrint){
			return ApiResponse.success(readService.getWpHttpUrl4m(filePath, width, height));
		}
		return ApiResponse.success(readService.getHttpUrl4m(filePath, width, height));
	}
	@ApiOperation(value = "通过文件路径获得http以t方式压缩的访问路径")
	@RequestMapping(value = "/file/httpUrl4t/query", method = { RequestMethod.GET })
	public ApiResponse<String> getHttpUrl4t(@ApiParam(value = "filePath", required = true) @RequestParam(required = true) String filePath,
											@ApiParam(value = "width", required = true) @RequestParam(required = true) String width,
											@ApiParam(value = "height", required = true) @RequestParam(required = true) String height,
											@ApiParam(value = "waterPrint", required = false) @RequestParam(required = false) boolean waterPrint) {
		if(waterPrint){
			return ApiResponse.success(readService.getWpHttpUrl4t(filePath, width, height));
		}
		return ApiResponse.success(readService.getHttpUrl4t(filePath, width, height));
	}
	
	
}
