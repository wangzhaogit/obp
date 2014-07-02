package com.obp.system.web.taglib;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obp.system.common.helper.TagHelper;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.model.tplengine.DefaultTemplate;
import com.obp.system.model.tplengine.StringTemplate;
import com.obp.system.model.tplengine.TemplateEngine;
import com.obp.system.model.tplengine.TemplateEngineFactory;
import com.obp.system.model.tplengine.TemplateType;

/**
 * 
 * @Title:ImportTag.java
 * @Package:com.obp.system.web.taglib
 * @Description:导入CSS、JS资源
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午1:56:21
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class ImportTag extends TagSupport{
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger();
	private String src;
	
	/**
	 * 标签开始
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public int doStartTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		String tpl = "#if($type==*css*)<link rel=*stylesheet* type=*text/css* href=*$src*/>#elseif($type==*js*)<script type=*text/javascript* src=*$src* ></script>#end";
		Dto dto = new BaseDto();
		dto.put("type", src.indexOf(".css") == -1 ? "js" : "css");
		dto.put("src", request.getContextPath() + src);
		TemplateEngine engine = TemplateEngineFactory.getTemplateEngine(TemplateType.VELOCITY);
		DefaultTemplate template = new StringTemplate(TagHelper.replaceStringTemplate(tpl));
		StringWriter writer = engine.mergeTemplate(template, dto);
		try {
			pageContext.getOut().write(writer.toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return super.SKIP_BODY;
	}
	
	/**
	 * 标签结束
	 */
	@SuppressWarnings("static-access")
	public int doEndTag() throws JspException{
		return super.EVAL_PAGE;
	}
	
	/**
	 * 释放资源
	 */
	public void release(){
		src = null;
		super.release();
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
