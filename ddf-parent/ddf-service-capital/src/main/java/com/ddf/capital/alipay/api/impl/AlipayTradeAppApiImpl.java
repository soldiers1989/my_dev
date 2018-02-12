package com.ddf.capital.alipay.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.ddf.capital.alipay.api.AlipayTradeAppApi;
import com.ddf.capital.alipay.constant.AlipayTradeWapConstant;
import com.ddf.capital.alipay.dto.AlipayTradeAppConfig;

@Component("alipayTradeAppApi")
public class AlipayTradeAppApiImpl implements AlipayTradeAppApi{
	
	@Autowired
	private AlipayTradeAppConfig alipayTradeAppConfig;
	
	@Override
	public String alipayTradeAppPay(String out_trade_no, String subject, String total_amount,String notifyUrl, String body,
			String timeout_express) {
		AlipayClient alipayClient = new DefaultAlipayClient(alipayTradeAppConfig.getUrl(), alipayTradeAppConfig.getAppId(), 
				alipayTradeAppConfig.getRsaPrivateKey(), AlipayTradeWapConstant.FORMAT, AlipayTradeWapConstant.CHARSET, 
				alipayTradeAppConfig.getAlipayPublicKey(), AlipayTradeWapConstant.SIGNTYPE);
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(body);
		model.setSubject(subject);
		model.setOutTradeNo(out_trade_no);
		model.setTimeoutExpress(timeout_express);
		model.setTotalAmount(total_amount);
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(notifyUrl);
		try {
		        //这里和普通的接口调用不同，使用的是sdkExecute
		        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
		        System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
		        return response.getBody();
		    } catch (AlipayApiException e) {
		        e.printStackTrace();
		        return null;
		}
	}

}
