package com.obp.system.model.tplengine;

import java.io.StringWriter;

import com.obp.system.model.metatype.Dto;

/**
 * 
 * @Title:TemplateEngine.java
 * @Package:com.obp.system.model.tplengine
 * @Description:模板引擎接口
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午2:03:18
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public interface TemplateEngine {
	
	/**
	 * @Description:驱动模板
	 * @author: wangzhao
	 * @date: 2014-4-19下午2:03:48
	 * @mail: wangzhao@huateng.com
	 * @param pTemplate<模板对象>
	 * @param pDto<合并参数集合	>
	 * @return 引擎驱动后的StringWriter对象
	 */
	public StringWriter mergeTemplate(DefaultTemplate pTemplate, Dto pDto);

}
