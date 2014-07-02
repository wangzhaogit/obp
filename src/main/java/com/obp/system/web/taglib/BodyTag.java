package com.obp.system.web.taglib;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obp.system.common.helper.TagHelper;
import com.obp.system.model.exception.CommonException;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.model.tplengine.DefaultTemplate;
import com.obp.system.model.tplengine.StringTemplate;
import com.obp.system.model.tplengine.TemplateEngine;
import com.obp.system.model.tplengine.TemplateEngineFactory;
import com.obp.system.model.tplengine.TemplateType;

/**
 * 
 * @Title:BodyTag.java
 * @Package:com.obp.system.web.taglib
 * @Description:Body标签
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年4月25日上午11:20:50
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class BodyTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger();
	private String onload;
	private String any;
	private String cls;
	
	/**
	 * @Description:标签开始
	 * @author: wangzhao
	 * @date: 2014年4月25日上午11:21:27
	 * @mail: wangzhao@huateng.com
	 * @return
	 * @throws JspException
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public int doStartTag() throws CommonException{
		Dto dto = new BaseDto();
		dto.put("onload", TagHelper.checkEmpty(onload));
		dto.put("any", TagHelper.checkEmpty(any));
		dto.put("cls", TagHelper.checkEmpty(cls));
		String tpl = "<body #if(${cls}!=*off*)class=*${cls}*#end #if(${onload}!=*off*)onload=*${onload}*#end #if(${any}!=*off*)${any}#end>";
		TemplateEngine engine = TemplateEngineFactory.getTemplateEngine(TemplateType.VELOCITY);
		DefaultTemplate template = new StringTemplate(TagHelper.replaceStringTemplate(tpl));
		StringWriter writer = engine.mergeTemplate(template, dto);
		try {
			pageContext.getOut().write(writer.toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return super.EVAL_BODY_INCLUDE;
	}
	
	/**
	 * 标签结束
	 * @param onload
	 */
	@SuppressWarnings("static-access")
	public int doEndTag() throws JspException{
		try {
			pageContext.getOut().write("</body>");
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return super.EVAL_PAGE;
	}
	
	/**
	 * 释放资源
	 */
	public void release(){
		any = null;
		cls = null;
		onload = null;
		super.release();
	}
	
	public void setOnload(String onload) {
		this.onload = onload;
	}
	public void setAny(String any) {
		this.any = any;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

}
