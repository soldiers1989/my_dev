package com.ddf.capital.alipay.api.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import com.ddf.capital.alipay.api.AlipayBatchTransApi;
import com.ddf.capital.alipay.constant.AlipayBatchTransConstant;
import com.ddf.capital.alipay.dto.BatchTransUser;
import com.ddf.capital.alipay.sign.MD5;
import com.ddf.capital.alipay.util.AlipayCore;
import com.ddf.capital.alipay.util.UtilDate;
import com.ddf.capital.alipay.util.httpClient.HttpProtocolHandler;
import com.ddf.capital.alipay.util.httpClient.HttpRequest;
import com.ddf.capital.alipay.util.httpClient.HttpResponse;
import com.ddf.capital.alipay.util.httpClient.HttpResultType;
import com.ddf.util.ListUtil;

@Component("alipayBatchTransApi")
public class AlipayBatchTransApiImpl implements AlipayBatchTransApi{
	
	
    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
	
    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    private static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = AlipayCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if("MD5".equals(AlipayBatchTransConstant.sign_type) ) {
        	mysign = MD5.sign(prestr, AlipayBatchTransConstant.key, AlipayBatchTransConstant.input_charset);
        }
        return mysign;
    }
	
    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", AlipayBatchTransConstant.sign_type);

        return sPara;
    }

    /**
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    private static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW
                      + "_input_charset=" + AlipayBatchTransConstant.input_charset + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");

        return sbHtml.toString();
    }
    
    /**
     * 建立请求，以表单HTML形式构造，带文件上传功能
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @param strParaFileName 文件上传的参数名
     * @return 提交表单HTML文本
     */
    private static String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName, String strParaFileName) {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\"  enctype=\"multipart/form-data\" action=\"" + ALIPAY_GATEWAY_NEW
                      + "_input_charset=" + AlipayBatchTransConstant.input_charset + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);

            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }
        
        sbHtml.append("<input type=\"file\" name=\"" + strParaFileName + "\" />");

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");

        return sbHtml.toString();
    }
    
    /**
     * 建立请求，以模拟远程HTTP的POST请求方式构造并获取支付宝的处理结果
     * 如果接口中没有上传文件参数，那么strParaFileName与strFilePath设置为空值
     * 如：buildRequest("", "",sParaTemp)
     * @param strParaFileName 文件类型的参数名
     * @param strFilePath 文件路径
     * @param sParaTemp 请求参数数组
     * @return 支付宝处理结果
     * @throws Exception
     */
    private static String buildRequest(String strParaFileName, String strFilePath,Map<String, String> sParaTemp) throws Exception {
        //待请求参数数组
        Map<String, String> sPara = buildRequestPara(sParaTemp);

        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(AlipayBatchTransConstant.input_charset);

        request.setParameters(generatNameValuePair(sPara));
        request.setUrl(ALIPAY_GATEWAY_NEW+"_input_charset="+AlipayBatchTransConstant.input_charset);

        HttpResponse response = httpProtocolHandler.execute(request,strParaFileName,strFilePath);
        if (response == null) {
            return null;
        }
        
        String strResult = response.getStringResult();

        return strResult;
    }

    /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        return nameValuePair;
    }
    
    /**
     * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数
     * 注意：远程解析XML出错，与服务器是否支持SSL等配置有关
     * @return 时间戳字符串
     * @throws IOException
     * @throws DocumentException
     * @throws MalformedURLException
     */
	private static String query_timestamp() throws MalformedURLException,
                                                        DocumentException, IOException {

        //构造访问query_timestamp接口的URL串
        String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayBatchTransConstant.partner + "&_input_charset" +AlipayBatchTransConstant.input_charset;
        StringBuffer result = new StringBuffer();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new URL(strUrl).openStream());

        List<Node> nodeList = doc.selectNodes("//alipay/*");

        for (Node node : nodeList) {
            // 截取部分不需要解析的信息
            if (node.getName().equals("is_success") && node.getText().equals("T")) {
                // 判断是否有成功标示
                List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1) {
                    result.append(node1.getText());
                }
            }
        }

        return result.toString();
    }

	@Override
	public String batchTransRequest(List<BatchTransUser> batchTransUsers,String batchNo,String notifyUrl) {
		if(!ListUtil.isEmpty(batchTransUsers)){
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "batch_trans_notify");
	        sParaTemp.put("partner", AlipayBatchTransConstant.partner);
	        sParaTemp.put("_input_charset", AlipayBatchTransConstant.input_charset);
	        //异步通知地址
			sParaTemp.put("notify_url", notifyUrl);
			//付款账户
			sParaTemp.put("email", AlipayBatchTransConstant.account);
			//付款账户名，个人支付宝账号是真实姓名公司支付宝账号是公司名称
			sParaTemp.put("account_name", AlipayBatchTransConstant.account_name);
			//付款当天日期，格式：年[4位]月[2位]日[2位]，如：20100801
			sParaTemp.put("pay_date", UtilDate.getDate());
			//批次号,格式：当天日期[8位]+序列号[3至16位]，如：201008010000001
			sParaTemp.put("batch_no", batchNo);
			//付款总金额，即参数detail_data的值中所有金额的总和
			Map<String,String> map=getTotalAmountDetailData(batchTransUsers);
			sParaTemp.put("batch_fee", map.get("totalAmount"));
			//付款笔数,，即参数detail_data的值中，“|”字符出现的数量加1，最大支持1000笔（即“|”字符出现的数量999个）
			sParaTemp.put("batch_num", batchTransUsers.size()+"");
			//付款详细数据，格式：流水号1^收款方帐号1^真实姓名^付款金额1^备注说明1|流水号2^收款方帐号2^真实姓名^付款金额2^备注说明2....
			sParaTemp.put("detail_data", map.get("detailData"));
			return buildRequest(sParaTemp,"get","确认");
		}
		return null;
	}
	private static Map<String,String> getTotalAmountDetailData(List<BatchTransUser> batchTransUsers){
		Map<String,String> map=new HashMap<String,String>();
		String detailData="";
		BigDecimal totalAmount=BigDecimal.ZERO;
		for(BatchTransUser batchTransUser:batchTransUsers){
			detailData+=UtilDate.getOrderNum()+UtilDate.getThree()+"^"+batchTransUser.getAccount()+"^"+batchTransUser.getRealName()+"^"+batchTransUser.getAmount().
					toString()+"^"+batchTransUser.getRemark()+"|";
			totalAmount=totalAmount.add(batchTransUser.getAmount());
		}
		detailData=detailData.substring(0, detailData.lastIndexOf("|"));
		map.put("totalAmount", totalAmount.toString());
		map.put("detailData", detailData);
		return map;
	}

}
