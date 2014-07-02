package com.obp.system.model.tplengine.velocity;

import java.io.StringWriter;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.obp.system.common.helper.VelocityHelper;
import com.obp.system.common.util.OBPUtils;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.tplengine.AbstractTemplateEngine;
import com.obp.system.model.tplengine.DefaultTemplate;

/**
 * 
 * @Title:VelocityTemplateEngine.java
 * @Package:com.obp.system.model.tplengine.velocity
 * @Description:Velocity模板引擎
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午2:10:32
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class VelocityTemplateEngine extends AbstractTemplateEngine{
	Logger logger = LogManager.getLogger();
	
	/**
	 * 驱动文件模板
	 * @param pTemplate 模板对象
	 * @param pDto 合并参数集合(将模板中所需变量全部压入Dto)
	 * @return 返回StringWriter对象
	 * @throws Exception 
	 */
	protected StringWriter mergeStringTemplate(DefaultTemplate pTemplate, Dto pDto) {
		VelocityEngine ve = VelocityHelper.getVelocityEngine();
		String strTemplate = pTemplate.getTemplateResource();
		if(OBPUtils.isEmpty(strTemplate)){
			throw new IllegalArgumentException("字符串模板不能为空");
		}
		StringWriter writer = new StringWriter();
		VelocityContext context = VelocityHelper.convertDto2VelocityContext(pDto);
		try {
			if(logger.isDebugEnabled())
				logger.debug("字符串模板为:\n" + strTemplate);
			logger.debug("OBP模板引擎启动,正在驱动字符串模板合并...");
			ve.evaluate(context, writer, "eRedTemplateEngine.log", strTemplate);
			if(logger.isDebugEnabled())
				logger.debug("字符串模板合并成功.合并结果如下:\n" + writer);
		} catch (Exception e) {
			logger.error("字符串模板合并失败");
			e.printStackTrace();
		}
		return writer;
	}
	
	/**
	 * 驱动字符串模板
	 * @param pTemplate 模板对象
	 * @param pDto 合并参数集合(将模板中所需变量全部压入Dto)
	 * @return 返回StringWriter对象
	 * @throws Exception 
	 * @throws Exception 
	 */
	protected StringWriter mergeFileTemplate(DefaultTemplate pTemplate, Dto pDto) {
		VelocityEngine ve = VelocityHelper.getVelocityEngine();
		String filePath = pTemplate.getTemplateResource();
		if(StringUtils.isEmpty(filePath)){
			throw new IllegalArgumentException("文件模板资源路径不能为空");
		}
		StringWriter writer = new StringWriter();
		Template template = null;
		try {
			if(logger.isDebugEnabled())
				logger.debug("OBP模板引擎启动,正在生成文件模板...");
			template = ve.getTemplate(filePath);
			if(logger.isDebugEnabled())
				logger.debug("生成文件模板成功");
		} catch (Exception e) {
			logger.error("生成文件模板失败");
			e.printStackTrace();
		}
		VelocityContext context = VelocityHelper.convertDto2VelocityContext(pDto);
		try {
			if(logger.isDebugEnabled())
				logger.debug("OBP模板引擎启动,正在驱动文件模板合并...");
			template.merge(context, writer);
			if(logger.isDebugEnabled())
				logger.debug("合并文件模板成功.合并结果如下:\n" + writer);
		} catch (Exception e) {
			if(logger.isDebugEnabled())
				logger.error("文件模板合并失败");
			e.printStackTrace();
		} 
		return writer;
	}

}
