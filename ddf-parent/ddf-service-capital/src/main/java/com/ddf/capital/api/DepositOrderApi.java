/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.ddf.capital.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.internal.util.AlipaySignature;
import com.ddf.capital.alipay.constant.AlipayTradeWapConstant;
import com.ddf.capital.alipay.dto.AlipayTradeAppConfig;
import com.ddf.capital.service.simple.BillService;
import com.ddf.capital.service.simple.DepositOrderService;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.Bill;
import com.ddf.entity.capital.dto.BondOrder;
import com.ddf.entity.capital.dto.DepositOrder;
import com.ddf.entity.capital.eo.BillStatus;
import com.ddf.entity.capital.eo.BondOrderStatus;
import com.ddf.entity.capital.eo.DepositOrderStatus;
import com.ddf.entity.capital.query.BillQuery;
import com.ddf.entity.capital.query.DepositOrderQuery;
import com.ddf.entity.capital.vo.BillVo;
import com.ddf.entity.capital.vo.DepositOrderVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.entity.response.ApiResponseResult;
import com.ddf.reference.member.UserReference;
import com.ddf.util.ListUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * deposit_order Api
 * @author robot
 * @version 2018-01-22
 */
@RestController
public class DepositOrderApi extends BaseApi{

	private Logger logger =Logger.getLogger(DepositOrderApi.class);
	
	@Autowired
	private DepositOrderService depositOrderService;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private AlipayTradeAppConfig alipayTradeAppConfig;

	@ApiOperation(value="查询单个DepositOrder")
	@RequestMapping(value = "/depositOrder/query",method = {RequestMethod.GET})
	public ApiResponse<DepositOrder> query(@ApiParam(value = "id",required = true) @RequestParam(required = true) String id) {
		return ApiResponse.success(depositOrderService.query4id(id));
	}

	@ApiOperation(value="创建DepositOrder")
	@RequestMapping(value = "/depositOrder/create",method = {RequestMethod.POST})
	public ApiResponse<String> create(@RequestBody DepositOrder depositOrder){
		try {
			String form=depositOrderService.createOrder(depositOrder);
			return ApiResponse.success(form);
		} catch (Exception e) {
			logger.error("创建DepositOrder异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="创建DepositOrder")
	@RequestMapping(value = "/depositOrder/create",method = {RequestMethod.GET})
	public ApiResponse<String> create(@ApiParam(value = "currentUserId",required = true) @RequestParam(required = true) String currentUserId,
			@ApiParam(value = "houseDepositContractId",required = true) @RequestParam(required = true) String houseDepositContractId,
			@ApiParam(value = "landlordId",required = true) @RequestParam(required = true) String landlordId){
		try {
			DepositOrder depositOrder=new DepositOrder();
			depositOrder.setLandlordId(landlordId);
			depositOrder.setLodgerId(currentUserId);
			depositOrder.setHouseDepositContractId(houseDepositContractId);
			String form=depositOrderService.createOrder(depositOrder);
			return ApiResponse.success(form);
		} catch (Exception e) {
			logger.error("创建DepositOrder异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}

	@ApiOperation(value="修改DepositOrder信息")
	@RequestMapping(value = "/depositOrder/modify",method = {RequestMethod.POST})
	public ApiResponse<Boolean> modify(@RequestBody DepositOrder depositOrder){
		return ApiResponse.success(depositOrderService.modify(depositOrder));
	}

	@ApiOperation(value="删除DepositOrder信息")
	@RequestMapping(value = "/depositOrder/remove",method = {RequestMethod.POST})
	public ApiResponse<Boolean> remove(@ApiParam(value = "id",required = true) @RequestParam(required=true)String id){
		return ApiResponse.success(depositOrderService.remove(id));
	}
	
	@ApiOperation(value="根据租客id查询DepositOrder")
	@RequestMapping(value = "/depositOrder/lodger/batchquery",method = {RequestMethod.GET})
	public ApiResponse<List<DepositOrderVo>> batchquery4lodgerId(@ApiParam(value = "lodgerId",required = true) @RequestParam(required = true) String lodgerId) {
		try {
			List<DepositOrderVo> depositOrderVoList=depositOrderService.batchquery4lodgerId(lodgerId);
			return ApiResponse.success(depositOrderVoList);
		} catch (Exception e) {
			logger.error("根据租客id查询DepositOrder,异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="根据房东id查询DepositOrder")
	@RequestMapping(value = "/depositOrder/landlord/batchquery",method = {RequestMethod.GET})
	public ApiResponse<List<DepositOrderVo>> batchquery4landlordId(@ApiParam(value = "landlordId",required = true) @RequestParam(required = true) String landlordId) {
		try {
			List<DepositOrderVo> depositOrderVoList=depositOrderService.batchquery4landlordId(landlordId);
			return ApiResponse.success(depositOrderVoList);
		} catch (Exception e) {
			logger.error("根据房东id查询DepositOrder,异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@ApiOperation(value="分页查询DepositOrder")
	@RequestMapping(value = "/depositOrder/pagequery",method = {RequestMethod.POST})
	public ApiResponse<Page<DepositOrderVo>> batchquery4landlordId(@RequestBody DepositOrderQuery depositOrderQuery) {
		try {
			Page<DepositOrderVo> page=depositOrderService.query4page(depositOrderQuery);
			return ApiResponse.success(page);
		} catch (Exception e) {
			logger.error("分页查询DepositOrder,异常："+e.getMessage(),e);
			return ApiResponse.fail(ApiResponseResult.ERROR);
		}
	}
	
	@RequestMapping("/depositOrder/alinotify")
	public void rechargeAlinotify(HttpServletRequest request,HttpServletResponse response){
		logger.info("定金接口接收阿里支付接口异步通知开始");
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
						DepositOrder depositOrder=depositOrderService.query4id(out_trade_no);
						depositOrder.setStatus(DepositOrderStatus.SUCCESS);
						depositOrder.setAlipayTradeNo(trade_no);
						depositOrderService.modify(depositOrder);
						//更新资金明细
						if(bill!=null) {
							bill.setStatus(BillStatus.SUCCESS);
							billService.modify(bill);
						}
					} else if (trade_status.equals("TRADE_CLOSED")){
						//更新充值订单状态
						DepositOrder depositOrder=depositOrderService.query4id(out_trade_no);
						depositOrder.setStatus(DepositOrderStatus.FAILED);
						depositOrder.setAlipayTradeNo(trade_no);
						depositOrderService.modify(depositOrder);
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
			logger.info("定金接口接收阿里支付接口异步通知异常,异常信息："+e.getMessage(),e);
		}finally{
				try {
					response.getWriter().write("success");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		logger.info("定金接口接收阿里支付接口异步通知结束");
	}

}