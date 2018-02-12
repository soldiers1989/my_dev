package com.ddf.dic.pagehelper;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

@Configuration
public class PageHelperUtil {
	
	/**
	 * pageHelper分页引入
	 * @return
	 */
	@Bean
	public PageHelper pageHelper() {
		System.out.println("MyBatisConfiguration pageHelper Start》》》》》》》》》》》》》》》》》》》》》》");
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}
