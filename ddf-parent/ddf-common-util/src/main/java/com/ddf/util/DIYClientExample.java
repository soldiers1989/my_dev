package com.ddf.util;

import java.util.HashMap;


public class DIYClientExample {
  
    public static String singleMt(String phone,String text) {
    	String mtUrl="http://esms100.10690007.net/sms/mt";
        //操作命令、SP编号、SP密码，必填参数
        String command = "MT_REQUEST";
        String spid = "9378";
        String sppassword = "ZV3H4g6O";
        //sp服务代码，可选参数，默认为 00
        String spsc = "00";
        //源号码，可选参数
        String sa = "";
        //目标号码，必填参数
        phone = "86"+phone;
        //下行内容以及编码格式，必填参数
        int dc = 15;
        text = DxUtils.encodeHexStr(dc,text);
        //组成url字符串
        String smsUrl = mtUrl + "?command=" + command + "&spid=" + spid + "&sppassword=" + sppassword + "&spsc=" + spsc + "&sa=" + sa + "&da=" + phone + "&sm=" + text + "&dc=" + dc;  
        //发送http请求，并接收http响应
        String  resStr= DxUtils.doPostRequest(smsUrl.toString(), null);
        //解析响应字符串
        HashMap pp = DxUtils.parseResStr(resStr);
        pp.put("command", command);
        pp.put("spid",spid);
        pp.put("mtmsgid", pp.get("mtmsgid"));
        pp.put("mtstat", pp.get("mtstat"));
        pp.put("mterrcode", pp.get("mterrcode"));        
        
        return pp.toString();
    }

    
}
