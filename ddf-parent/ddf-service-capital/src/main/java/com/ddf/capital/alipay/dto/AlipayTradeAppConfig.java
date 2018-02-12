package com.ddf.capital.alipay.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AlipayTradeAppConfig {
	
	@Value("${alipay_trade_app_appId}")
	private String appId;
	
	@Value("${alipay_trade_app_rsaPrivateKey}")
	private String rsaPrivateKey;
	
	@Value("alipay_trade_app_url")
	private String url;
	
	@Value("alipay_trade_app_alipayPublicKey")
	private String alipayPublicKey ;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRsaPrivateKey() {
		return rsaPrivateKey;
	}

	public void setRsaPrivateKey(String rsaPrivateKey) {
		this.rsaPrivateKey = rsaPrivateKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAlipayPublicKey() {
		return alipayPublicKey;
	}

	public void setAlipayPublicKey(String alipayPublicKey) {
		this.alipayPublicKey = alipayPublicKey;
	}
	
}
