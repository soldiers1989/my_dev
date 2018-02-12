package com.ddf.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * DES加密 解密算法
 * 
 * @author lifq
 * @date 2015-3-17 上午10:12:11
 */
public class DesUtil {
	private final static String DES = "DES";
    private final static String ENCODE = "GBK";
    private final static String defaultKey = "nsshuahua--";

    public static void main(String[] args) throws Exception {
        String data = "sdjfkdsjfkdsjfkdsfkdjkdsfjsd--93864843-81F7-4D52-B90F-CDE264669F7B";
        System.out.println(encrypt(data));
        System.out.println(decrypt(encrypt(data)));

    }
    
    
    public static String encrypt(String plainText){  
        byte[] b=plainText.getBytes();  
        Base64 base64=new Base64();  
        b=base64.encode(b);  
        String s=new String(b);  
        return s;  
    }  
      
    public static String decrypt(String encodeStr){  
        byte[] b=encodeStr.getBytes();  
        Base64 base64=new Base64();  
        b=base64.decode(b);  
        String s=new String(b);  
        return s;  
    }
}
