package com.ddf.file.api;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.ddf.util.DateUtil;


/**
 * 控制器支持类
 * @author ThinkGem
 * @version 2013-3-23
 */
public abstract class BaseApi {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
        //binder.registerCustomEditor(Double.class, new DoubleEditor()); 
        //binder.registerCustomEditor(Integer.class, new IntegerEditor());
    }

    private class DateEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Date date = null; 
            try {
            	date = DateUtil.StringToDate(text, DateUtil.yyyy_MM_dd_HH_mm_ss);
            } catch (Exception e) {
                date = DateUtil.StringToDate(text, DateUtil.yyyy_MM_dd); 
            }
            setValue(date);
        }
    }
    
 
	
}
