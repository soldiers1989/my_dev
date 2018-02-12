package com.ddf.capital.alipay.api;

public interface AlipayTradeAppApi {
	/**
	 * 手机app支付
	 * @param out_trade_no
	 * @param subject
	 * @param total_amount
	 * @return
	 */
	String alipayTradeAppPay(String out_trade_no,String subject,String total_amount,String notifyUrl,String body,String timeout_express);
}
