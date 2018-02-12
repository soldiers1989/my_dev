/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.capital.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.internal.util.AlipaySignature;
import com.ddf.capital.alipay.constant.AlipayTradeWapConstant;
import com.ddf.capital.alipay.dto.AlipayTradeAppConfig;
import com.ddf.capital.service.simple.BillService;
import com.ddf.capital.service.simple.RechargeOrderService;
import com.ddf.capital.service.simple.UserWalletService;
import com.ddf.entity.capital.dto.Bill;
import com.ddf.entity.capital.dto.RechargeOrder;
import com.ddf.entity.capital.eo.BillStatus;
import com.ddf.entity.capital.eo.RechargeOrderStatus;
import com.ddf.entity.capital.eo.UserWalletStatus;
import com.ddf.entity.capital.query.BillQuery;
import com.ddf.entity.capital.vo.BillVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.util.ListUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * recharge_order Api
 * @author robot
 * @version 2018-01-22
 */
@RestController
public class RechargeOrderApi extends BaseApi{

	private static Logger logger=LoggerFactory.getLogger(RechargeOrderApi.class);
	
	@Autowired
	private AlipayTradeAppConfig alipayTradeAppConfig;
	
	@Autowired
	private RechargeOrderService rechargeOrderService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private UserWalletService userWalletService;
	
	@ApiOperation(value="查询单个RechargeOrder")
	@RequestMapping(value = "/rechargeOrder/query",method = {RequestMethod.GET})
	public ApiResponse<RechargeOrder> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(rechargeOrderService.query4id(id));
	}

	@ApiOperation(value="创建RechargeOrder返回支付宝参数")
	@RequestMapping(value = "/rechargeOrder/create",method = {RequestMethod.POST})
	public ApiResponse<String> create(@RequestBody RechargeOrder rechargeOrder){
		try {
			String form=rechargeOrderService.createOrder(rechargeOrder);
			return ApiResponse.success(form);
		} catch (Exception e) {
			logger.error("创建RechargeOrder异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="创建RechargeOrder返回支付宝参数")
	@RequestMapping(value = "/rechargeOrder/create",method = {RequestMethod.GET})
	public ApiResponse<String> create(@ApiParam(value = "currentUserId",required = true) @RequestParam(required = true) String currentUserId,
			@ApiParam(value = "amount",required = true) @RequestParam(required = true) String amount){
		try {
			RechargeOrder rechargeOrder=new RechargeOrder();
			rechargeOrder.setUserId(currentUserId);
			rechargeOrder.setAmount(new BigDecimal(amount));
			String form=rechargeOrderService.createOrder(rechargeOrder);
			return ApiResponse.success(form);
		} catch (Exception e) {
			logger.error("创建RechargeOrder异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改RechargeOrder信息")
	@RequestMapping(value = "/rechargeOrder/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@ModelAttribute RechargeOrder rechargeOrder){
		return ApiResponse.success(rechargeOrderService.modify(rechargeOrder));
	}

	@ApiOperation(value="删除RechargeOrder信息")
	@RequestMapping(value = "/rechargeOrder/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(rechargeOrderService.remove(id));
	}
	
	@RequestMapping("/recharge/alinotify")
	public void rechargeAlinotify(HttpServletRequest request,HttpServletResponse response){
		logger.info("充值接口接收阿里支付接口异步通知开始");
		try{
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
				//商户订单号

				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
				//支付宝交易号

				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

				//交易状态
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

				//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
				//计算得出通知验证结果
				//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
				boolean verify_result = AlipaySignature.rsaCheckV1(params, alipayTradeAppConfig.getAlipayPublicKey(), AlipayTradeWapConstant.CHARSET, AlipayTradeWapConstant.SIGNTYPE);
				
				if(verify_result){//验证成功
					//////////////////////////////////////////////////////////////////////////////////////////
					//请在这里加上商户的业务逻辑程序代码

					//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
					Bill bill=null;
					BillQuery billQuery=new BillQuery();
					billQuery.getBill().setOrderId(out_trade_no);
					List<BillVo> billVoList=billService.findList(billQuery);
					if(!ListUtil.isEmpty(billVoList)) {
						bill=billVoList.get(0);
					}
					if(trade_status.equals("TRADE_FINISHED")){
						//判断该笔订单是否在商户网站中已经做过处理
							//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
							//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
							//如果有做过处理，不执行商户的业务程序
							
						//注意：
						//如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
						//如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
					} else if (trade_status.equals("TRADE_SUCCESS")){
						//更新充值订单状态
						RechargeOrder rechargeOrder=rechargeOrderService.query4id(out_trade_no);
						rechargeOrder.setStatus(RechargeOrderStatus.SUCCESS);
						rechargeOrder.setAlipayTradeNo(trade_no);
						rechargeOrderService.modify(rechargeOrder);
						//更新用户钱包
						userWalletService.recharge(rechargeOrder.getUserId(), rechargeOrder.getAmount());
						//更新资金明细
						if(bill!=null) {
							bill.setStatus(BillStatus.SUCCESS);
							billService.modify(bill);
						}
					} else if (trade_status.equals("TRADE_CLOSED")){
						//更新充值订单状态
						RechargeOrder rechargeOrder=rechargeOrderService.query4id(out_trade_no);
						rechargeOrder.setStatus(RechargeOrderStatus.FAILED);
						rechargeOrder.setAlipayTradeNo(trade_no);
						rechargeOrderService.modify(rechargeOrder);
						//更新资金明细
						if(bill!=null) {
							bill.setStatus(BillStatus.FAILED);
							billService.modify(bill);
						}
					}
					//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
					System.out.println("success");	//请不要修改或删除

					//////////////////////////////////////////////////////////////////////////////////////////
				}else{//验证失败
					System.out.println("fail");
				}
		}catch (Exception e) {
			logger.info("充值接口接收阿里支付接口异步通知异常,异常信息："+e.getMessage(),e);
		}finally{
				try {
					response.getWriter().write("success");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		logger.info("充值接口接收阿里支付接口异步通知结束");
	}

}