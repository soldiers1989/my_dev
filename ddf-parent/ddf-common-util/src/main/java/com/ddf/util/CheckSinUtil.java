package com.ddf.util;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.DigestUtils;


public class CheckSinUtil
{
   public static boolean checkSin( HttpServletRequest request )
   {
      String sign = ( String ) request.getParameter( "sign" );
      StringBuffer urlparmsstr = new StringBuffer();
      Map parmsmap = request.getParameterMap();
      parmsmap = Utils.sortMapByKey( parmsmap );
      for ( Iterator iter = parmsmap.keySet().iterator(); iter.hasNext(); )
      {
         String key = ( String ) iter.next();
         if ( "sign".equals( key ) /*|| "user_avatar".equals( key )*/ )  continue;
         String[] values = ( String[] ) parmsmap.get( key );
         for ( int i = 0; i < values.length; i++ )
         {
            urlparmsstr.append( key ).append( "=" ).append( values[ i ] ).append( "&" );
         }
      }
      String urlparmsall = urlparmsstr.substring( 0, urlparmsstr.length() - 1 ).toString(); // 去掉末尾一个&符号
      String serverSign = DigestUtils.md5DigestAsHex( ( urlparmsall ).getBytes() );
      //如果对比不正确
      if ( !serverSign.equals( sign ) )
      {
         System.out.println("签名："+serverSign+"     URI："+request.getRequestURI()+"     参数："+JsonUtil.toJson(request.getParameterMap()));
         return false;
      }
      return true;
   }

}
