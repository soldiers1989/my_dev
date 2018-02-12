package com.fmzg.gen.main;




import com.fmzg.gen.config.Global;
import com.fmzg.gen.entity.GenScheme;
import com.fmzg.gen.entity.GenTable;
import com.fmzg.gen.service.GenSchemeService;
import com.fmzg.gen.service.GenTableService;
import com.fmzg.gen.util.FileUtils;
import com.fmzg.gen.util.StringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class Generate {
	public static void main(String[] args){
		//清除生成目录文件
		FileUtils.delFile(Global.getProjectPath());
		String[] str = {
				"month_rent_amount_circle","month_rent_amount_district","month_rent_amount_xiaoqu"};
				for(String s:str){
					genFile(s);
				}
	}

	private static void genFile(String tableName){
		genFile(tableName,null,null);
	}
	
	private static void genFile(String tableName,String objectName,String author){
		if(StringUtils.isEmpty(tableName)){
			System.out.println("表名不能为空");
			return ;
		}
		if(StringUtils.isEmpty(objectName)){
			objectName=tableName+" ";
		}
		if(StringUtils.isEmpty(author)){
			author = "robot";
		}
		ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext("classpath:/frame/bd-gen/spring/dispatcher-servlet.xml");
		GenTableService genTableService =  context.getBean("genTableService", GenTableService.class);
		GenSchemeService genSchemeService =  context.getBean("genSchemeService", GenSchemeService.class);
		
		GenTable genTable = genTableService.getTableFormDb(tableName);
		
		
		GenScheme genScheme = new GenScheme();
		genScheme.setBasePackageName(Global.getConfig("basePackageName"));
		genScheme.setBizPackageName(Global.getConfig("bizPackageName"));
		genScheme.setName("单表操作"); 	// 名称
		genScheme.setCategory("curd");		// 分类
		genScheme.setPackageName("");		// 生成包路径
		genScheme.setModuleName("");		// 生成模块名
		genScheme.setSubModuleName("");		// 生成子模块名  不能为null
		genScheme.setFunctionName(objectName);		// 生成功能名
		genScheme.setFunctionNameSimple("sss");		// 生成功能名（简写）
		genScheme.setFunctionAuthor(author);		// 生成功能作者
		genScheme.setFlag("1"); 	// 0：保存方案； 1：保存方案并生成代码
		genScheme.setReplaceFile(true);	// 是否替换现有文件    0：不替换；1：替换文件
		genScheme.setGenTable(genTable);
		String result = genSchemeService.generateCode(genScheme);
		System.out.println("操作生成方案'" + genScheme.getName() + "'成功\n"+result);
		
		context.close();
	}
}
