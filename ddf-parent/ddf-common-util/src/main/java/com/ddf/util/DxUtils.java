package com.ddf.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class DxUtils {
	//以下代码为定义的工具方法或变量，可单独在一个工具类中进行定义

    /**
     * 发送http GET请求，并返回http响应字符串
     * 
     * @param urlstr 完整的请求url字符串
     * @return
     */
    public static String doGetRequest(String urlstr) {
        String res = null;
        try {
            URL url = new URL(urlstr);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Content-Type", "text/html; charset=GB2312");
            System.setProperty("sun.net.client.defaultConnectTimeout", "5000");//jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout", "10000"); //jdk1.4换成这个,读操作超时
            //httpConn.setConnectTimeout(5000);//jdk 1.5换成这个,连接超时
            //httpConn.setReadTimeout(10000);//jdk 1.5换成这个,读操作超时
            httpConn.setDoInput(true);
            int rescode = httpConn.getResponseCode();
            if (rescode == 200) {
                BufferedReader bfw = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                res = bfw.readLine();
            } else {
                res = "Http request error code :" + rescode;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return res;
    }

    /**
     * 发送http POST请求，并返回http响应字符串
     * 
     * @param urlstr 完整的请求url字符串
     * @return
     */
    public static String doPostRequest(String urlstr, String dasms) {
        String res = null;
        try {
            URL url = new URL(urlstr);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=ISO-8859-1");
            OutputStream outputstream = httpConn.getOutputStream();
           // outputstream.write(("dasm=" + dasm).getBytes());
            outputstream.flush();
            
            System.setProperty("sun.net.client.defaultConnectTimeout", "5000");//jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout", "10000"); //jdk1.4换成这个,读操作超时
            //httpConn.setConnectTimeout(5000);//jdk 1.5换成这个,连接超时
            //httpConn.setReadTimeout(5000);//jdk 1.5换成这个,读操作超时
            
            int rescode = httpConn.getResponseCode();
            if (rescode == 200) {
                BufferedReader bfw = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                res = bfw.readLine();
            } else {
                res = "Http request error code :" + rescode;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return res;
    }
    
    /**
     * 将 短信下行 请求响应字符串解析到一个HashMap中
     * @param resStr
     * @return
     */
    public static HashMap parseResStr(String resStr) {
        HashMap pp = new HashMap();
        try {
            String[] ps = resStr.split("&");
            for (int i = 0; i < ps.length; i++) {
                int ix = ps[i].indexOf("=");
                if (ix != -1) {
                    pp.put(ps[i].substring(0, ix), ps[i].substring(ix + 1));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return pp;
    }


    /** 
     * Hex编码字符组
     */
    private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 将普通字符串转换成Hex编码字符串
     * 
     * @param dataCoding 编码格式，15表示GBK编码，8表示UnicodeBigUnmarked编码，0表示ISO8859-1编码
     * @param realStr 普通字符串
     * @return Hex编码字符串
     */
    public static String encodeHexStr(int dataCoding, String realStr) {
        String hexStr = null;

        if (realStr != null) {
            byte[] data = null;
            try {
                if (dataCoding == 15) {
                    data = realStr.getBytes("GBK");
                } else if ((dataCoding & 0x0C) == 0x08) {
                    data = realStr.getBytes("UnicodeBigUnmarked");
                } else {
                    data = realStr.getBytes("ISO8859-1");
                }
            } catch (UnsupportedEncodingException e) {
                System.out.println(e.toString());
            }

            if (data != null) {
                int len = data.length;
                char[] out = new char[len << 1];
                // two characters form the hex value.
                for (int i = 0, j = 0; i < len; i++) {
                    out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
                    out[j++] = DIGITS[0x0F & data[i]];
                }
                hexStr = new String(out);
            }
        }
        return hexStr;
    }
    
    /**
     * 将Hex编码字符串还原成普通字符串
     * 
     * @param dataCoding 反编码格式，15表示GBK编码，8表示UnicodeBigUnmarked编码，0表示ISO8859-1编码
     * @param hexStr Hex编码字符串
     * @return 普通字符串
     */
    public static String decodeHexStr(int dataCoding, String hexStr) {
        String realStr = null;

        if (hexStr != null) {
            char[] data = hexStr.toCharArray();

            int len = data.length;

            if ((len & 0x01) != 0) {
                throw new RuntimeException("Odd number of characters.");
            }

            byte[] out = new byte[len >> 1];

            // two characters form the hex value.
            for (int i = 0, j = 0; j < len; i++) {
                int f = Character.digit(data[j], 16) << 4;
                if(f==-1){
                    throw new RuntimeException("Illegal hexadecimal charcter " + data[j] + " at index " + j);
                }
                j++;
                f = f | Character.digit(data[j], 16);
                if(f==-1){
                    throw new RuntimeException("Illegal hexadecimal charcter " + data[j] + " at index " + j);
                }
                j++;
                out[i] = (byte) (f & 0xFF);
            }
            try {
                if (dataCoding == 15) {
                    realStr = new String(out, "GBK");
                } else if ((dataCoding & 0x0C) == 0x08) {
                    realStr = new String(out, "UnicodeBigUnmarked");
                } else {
                    realStr = new String(out, "ISO8859-1");
                }
            } catch (UnsupportedEncodingException e) {
                System.out.println(e.toString());
            }
        }

        return realStr;
    }

}
