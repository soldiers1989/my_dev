package com.ddf.route.filter;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.util.CheckSinUtil;
import com.ddf.util.JsonUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
@Component
public class SignFilter extends ZuulFilter{
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response=ctx.getResponse();
        response.setHeader( "Content-type", "text/html;charset=UTF-8" );
        ctx.setSendZuulResponse(false);
		ctx.setResponseStatusCode(401);
        //判断user——token是否为空
        try {
			if(!CheckSinUtil.checkSin( request )) {
			   response.getWriter().write(JsonUtil.toJson(ApiResponse.fail(ApiResponseResult.COMMON_PARAM_SIGN_ERROR)));
			   return null;
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
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
 	    if(requestURI.contains("api-docs")||requestURI.contains("swagger")||requestURI.equals("/capital/recharge/alinotify")
 	    		||requestURI.equals("/capital/bondOrder/alinotify")||requestURI.equals("/capital/depositOrder/alinotify")
 	    		||requestURI.equals("/capital/withdrawApplyBatch/alinotify")){
 		   return false;
 	    }
 	    if(requestURI.contains("/validateCode/imgCode/query")){
 	    	return false;
 	    }
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
