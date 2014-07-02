package com.obp.system.model.tplengine;

import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obp.system.model.metatype.Dto;

/**
 * 
 * @Title:AbstractTemplateEngine.java
 * @Package:com.obp.system.model.tplengine
 * @Description:模板引擎抽象基类
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午3:38:31
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public abstract class AbstractTemplateEngine implements TemplateEngine{
	
	private static Logger logger =  LogManager.getLogger();
	
	/**
	 * 驱动模板
	 * @param pTemplate 模板对象
	 * @param pDto 合并参数集合(将模板中所需变量全部压入Dto)
	 * @return writer引擎驱动后的StringWriter对象
	 */
	public StringWriter mergeTemplate(DefaultTemplate pTemplate, Dto dto) {
		StringWriter writer = null;
		if(pTemplate instanceof StringTemplate){
			writer = mergeStringTemplate(pTemplate, dto);
		}else if(pTemplate instanceof FileTemplate){
			writer = mergeFileTemplate(pTemplate, dto);
		}else{
			logger.error("不支持的模板");
			throw new IllegalArgumentException("不支持的模板" );
		}
		return writer;
	}
	
	/**
	 * 驱动字符串模板
	 * @param pTemplate 模板对象
	 * @return 返回StringWriter对象
	 * @param pDto 合并参数集合(将模板中所需变量全部压入Dto)
	 * @throws Exception 
	 */
	protected abstract StringWriter mergeStringTemplate(DefaultTemplate pTemplate, Dto pDto);
	
	/**
	 * 驱动文件模板
	 * @param pTemplate 模板对象
	 * @param pDto 合并参数集合(将模板中所需变量全部压入Dto)
	 * @return 返回StringWriter对象
	 * @throws Exception 
	 */
	protected abstract StringWriter mergeFileTemplate(DefaultTemplate pTemplate, Dto pDto);

}
