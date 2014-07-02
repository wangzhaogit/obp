package com.obp.system.web.taglib;

import java.io.IOException;
import java.io.StringWriter;

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

public class DivTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger();
	private String key;
    private String cls;
    private String style;
    private String any;
    
    /**
     * 标签开始
     */
    @SuppressWarnings({ "static-access", "unchecked" })
	public int doStartTag() throws JspException{
		Dto dto = new BaseDto();
		dto.put("key", key);
		dto.put("any", TagHelper.checkEmpty(any));
		dto.put("style", TagHelper.checkEmpty(style));
		dto.put("cls", TagHelper.checkEmpty(cls));
		String tpl = "<div id=*$key* #if(${cls}!=*off*)class=*${cls}*#end #if(${style}!=*off*)style=*${style}*#end #if(${any}!=*off*)${any}#end>";
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
     */
    @SuppressWarnings("static-access")
	public int doEndTag() throws JspException{
		try {
			pageContext.getOut().write("</div>");
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
    	key = null;
    	cls = null;
    	style = null;
    	any = null;
    	super.release();
    }
    
	public void setCls(String cls) {
		this.cls = cls;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public void setAny(String any) {
		this.any = any;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
