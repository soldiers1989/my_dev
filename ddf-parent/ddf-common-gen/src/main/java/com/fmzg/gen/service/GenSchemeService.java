/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fmzg.gen.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fmzg.gen.dao.GenSchemeDao;
import com.fmzg.gen.dao.GenTableColumnDao;
import com.fmzg.gen.dao.GenTableDao;
import com.fmzg.gen.entity.GenConfig;
import com.fmzg.gen.entity.GenScheme;

import com.fmzg.gen.dao.GenDataBaseDictDao;
import com.fmzg.gen.dao.GenTableColumnDao;
import com.fmzg.gen.dao.GenTableDao;
import com.fmzg.gen.entity.GenTable;
import com.fmzg.gen.entity.GenTableColumn;
import com.fmzg.gen.entity.GenTemplate;
import com.fmzg.gen.util.GenUtils;
import com.fmzg.gen.util.StringUtils;


/**
 * 生成方案Service
 * @author ThinkGem
 * @version 2013-10-15
 */
@Service
@Transactional(readOnly = true)
public class GenSchemeService {

	@Autowired
	private GenSchemeDao genSchemeDao;
//	@Autowired
//	private GenTemplateDao genTemplateDao;
	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	
	public GenScheme get(String id) {
		return genSchemeDao.get(id);
	}
	
	/*public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme) {
		GenUtils.getTemplatePath();
		genScheme.setPage(page);
		page.setList(genSchemeDao.findList(genScheme));
		return page;
	}
	
	@Transactional(readOnly = false)
	public String save(GenScheme genScheme) {
		if (StringUtils.isBlank(genScheme.getId())){
			genScheme.preInsert();
			genSchemeDao.insert(genScheme);
		}else{
			genScheme.preUpdate();
			genSchemeDao.update(genScheme);
		}
		// 生成代码
		if ("1".equals(genScheme.getFlag())){
			return generateCode(genScheme);
		}
		return "";
	}*/
	
	@Transactional(readOnly = false)
	public void delete(GenScheme genScheme) {
		genSchemeDao.delete(genScheme);
	}
	
	public String generateCode(GenScheme genScheme){
		StringBuilder result = new StringBuilder();
		// 获取所有代码模板
		GenConfig config = GenUtils.getConfig();
		// 获取模板列表
		List<GenTemplate> templateList = GenUtils.getTemplateList(config, genScheme.getCategory(), false);
		
		// 生成主表模板代码
		Map<String, Object> model = GenUtils.getDataModel(genScheme);
		for (GenTemplate tpl : templateList){
			result.append(GenUtils.generateToFile(tpl, model, genScheme.getReplaceFile()));
		}
		return result.toString();
	}
}
