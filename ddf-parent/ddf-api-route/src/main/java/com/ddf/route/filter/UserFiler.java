package com.ddf.route.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.cache.CacheReference;
import com.ddf.util.DesUtil;
import com.ddf.util.JsonUtil;
import com.ddf.util.StringUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class UserFiler extends ZuulFilter{
	@Autowired
	private CacheReference cacheReference;
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String user_token=request.getParameter("user_token");
        HttpServletResponse response=ctx.getResponse();
        response.setHeader( "Content-type", "text/html;charset=UTF-8" );
        ctx.setSendZuulResponse(false);
		ctx.setResponseStatusCode(401);
        //判断user——token是否为空
        try {
			if(StringUtil.isEmpty(user_token)) {
				response.getWriter().write(JsonUtil.toJson(ApiResponse.fail(ApiResponseResult.COMMON_USER_TOKEN_ERROR)));
				return null;
			}else {
				//判断user是否有效
				String openToken=DesUtil.decrypt(user_token);
				if(StringUtil.isEmpty(openToken)) {
					response.getWriter().write(JsonUtil.toJson(ApiResponse.fail(ApiResponseResult.COMMON_USER_TOKEN_ERROR)));
					return null;
				}
				ApiResponse<String> result_userId4cache=cacheReference.get(openToken);
				if(result_userId4cache == null || result_userId4cache.getData()==null) {
					response.getWriter().write(JsonUtil.toJson(ApiResponse.fail(ApiResponseResult.COMMON_USER_TOKEN_ERROR)));
					return null;
				}
				String userId = request.getParameter("currentUserId");
				if(StringUtil.isNotEmpty(userId)&&!userId.equals(result_userId4cache.getData())){
					response.getWriter().write(JsonUtil.toJson(ApiResponse.fail(ApiResponseResult.COMMON_TOKEN_USERID_ERROR)));
			        return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().write(JsonUtil.toJson(ApiResponse.fail(ApiResponseResult.ERROR)));
				return null;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
        ctx.setSendZuulResponse(true);
		ctx.setResponseStatusCode(200);
		return null;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		if(ctx.getResponseStatusCode()==401) {
			return false;
		}
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
 	    if(requestURI.contains("api-docs")||requestURI.contains("swagger")||requestURI.equals("/capital/recharge/alinotify")
 	    		||requestURI.equals("/capital/bondOrder/alinotify")||requestURI.equals("/capital/depositOrder/alinotify")
 	    		||requestURI.equals("/capital/withdrawApplyBatch/alinotify")){
 		   return false;
 	    }
 	    //不需要过滤 user_token接口
 	    if(	requestURI.contains("/user/login")||
 	    	requestURI.contains("/user/register")||
 	    	requestURI.contains("/sms/shortMessage/")||
 	    	requestURI.contains("/common/validateCode/")||
 	    	requestURI.contains("/user/userName/check")
 	    ){
 	    	return false;
 	    }
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
