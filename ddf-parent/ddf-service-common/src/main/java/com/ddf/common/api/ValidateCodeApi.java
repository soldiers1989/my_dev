/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.common.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ddf.entity.constant.RedisKeyConstant;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.cache.CacheReference;
import com.ddf.util.StringUtil;
import com.google.code.kaptcha.Producer;

/**
 * ValidateCodeApi
 * @author robot
 * @version 2018-01-05
 */
@Api(value = "ValidateCodeApi", tags = "验证码" )
@RestController
public class ValidateCodeApi extends BaseApi{
	
	@Autowired
	private Producer captchaProducer;
	
	@Autowired
	private CacheReference cacheReference;
	
	@ApiOperation(value="获取图形验证码")
	@RequestMapping(value = "/validateCode/imgCode/query",method = {RequestMethod.GET})
	public ModelAndView queryImgCode(@ApiParam(value = "设备号",required = true) @RequestParam(required = true) String deviceId, HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", 0);  
        // Set standard HTTP/1.1 no-cache headers.  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        // Set standard HTTP/1.0 no-cache header.  
        response.setHeader("Pragma", "no-cache");  
        // return a jpeg  
        response.setContentType("image/jpeg");  
        // create the text for the image  
        String capText = captchaProducer.createText();  
        // store the text in the session 
        synchronized (this) {
		//WebUtil.addSessionValue(request, WebUtil.REGISTER_IMG_CODE, capText);
    	//String deviceId = request.getParameter("deviceId");
        ApiResponse<Boolean> resp = cacheReference.put4time(RedisKeyConstant.txyzm_+deviceId, capText, 120);
    	//MemCached.setMccObject("txyzm_"+deviceId, capText, 2);
		}
        //request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, capText);  
        // create the image with the text  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
        // write the data out  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;
	}
	
	@ApiOperation(value="验证图形验证码")
	@RequestMapping(value = "/validateCode/imgCode/check",method = {RequestMethod.GET})
	private ApiResponse<Boolean> checkTxyzm(
			@ApiParam(value = "设备号",required = true) @RequestParam(required = true) String deviceId,
			@ApiParam(value = "验证码",required = true) @RequestParam(required = true) String txyzm){
		try{
			String txyzmLocal =cacheReference.get(RedisKeyConstant.txyzm_ + deviceId).getData();
			if (StringUtil.isEmpty(txyzmLocal)) {
				return ApiResponse.success(false);
			}
			if (!txyzm.equalsIgnoreCase(txyzmLocal)) {
				return ApiResponse.success(false);
			}
			return ApiResponse.success(true);
		}catch(Exception e){
			logger.error("验证图形验证码，异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
}