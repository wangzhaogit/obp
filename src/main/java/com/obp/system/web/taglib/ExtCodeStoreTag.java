package com.obp.system.web.taglib;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obp.system.common.constants.TagConstant;
import com.obp.system.common.helper.TagHelper;
import com.obp.system.common.util.WebUtils;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.model.tplengine.DefaultTemplate;
import com.obp.system.model.tplengine.FileTemplate;
import com.obp.system.model.tplengine.TemplateEngine;
import com.obp.system.model.tplengine.TemplateEngineFactory;
import com.obp.system.model.tplengine.TemplateType;

/**
 * 
 * @Title:ExtCodeStoreTag.java
 * @Package:com.obp.system.web.taglib
 * @Description:CodeStoreTag标签<br>
 * 		导入Ext扩展组件的CSS、JS资源
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年5月10日上午10:14:59
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class ExtCodeStoreTag extends TagSupport {
	
	private static final long serialVersionUID = -6266068921590568610L;
	
	private static Logger logger = LogManager.getLogger();
	private String fields;
	private String showCode = "false";
	
	/**
	 * @Description:开始
	 * @author: wangzhao
	 * @date: 2014年5月10日上午10:12:07
	 * @mail: wangzhao@huateng.com
	 * @return
	 * @throws JspException
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		StringBuffer sb = new StringBuffer();
		sb.append(TagConstant.SCRIPT_START);
		Dto dto = new BaseDto();
		dto.put("showCode", showCode.toLowerCase());
		String[] arrayFields = fields.split(",");
		TemplateEngine engine = TemplateEngineFactory.getTemplateEngine(TemplateType.VELOCITY);
		DefaultTemplate template = new FileTemplate();
		template.setTemplateResource(TagHelper.getTemplatePath(getClass().getName()));
		for (int i = 0; i < arrayFields.length; i++) {
			if (arrayFields[i].indexOf(":") != -1) {
				String field = arrayFields[i].substring(0, arrayFields[i].indexOf(":"));
				dto.put("field", field);
				List<Dto> codeList = WebUtils.getCodeListByField(field, request);
				String filter =  arrayFields[i].substring(arrayFields[i].indexOf(":") + 1);
				String filters[] = filter.split("!");
				List<Dto> okList = new ArrayList<Dto>();
				
				for (Dto codeDto : codeList) {
					boolean flag = true;
					for (int k = 0; k < filters.length; k++) {
						if (codeDto.getAsString("code").equalsIgnoreCase(filters[k])) {
							flag = false;
						}
					}
					if (flag) {
						okList.add(codeDto);
					}
				}
				dto.put("codeList", okList);
			} else {
				List<Dto> codeList = WebUtils.getCodeListByField(arrayFields[i], request);
				dto.put("field", arrayFields[i]);
				dto.put("codeList", codeList);
			}
			StringWriter writer = engine.mergeTemplate(template, dto);
			sb.append(writer.toString());
		}
		sb.append(TagConstant.SCRIPT_END);
		try {
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return super.SKIP_BODY;
	}
	
	/**
	 * @Description:标签结束
	 * @author: wangzhao
	 * @date: 2014年5月10日上午10:23:14
	 * @mail: wangzhao@huateng.com
	 * @return
	 * @throws JspException
	 */
	@SuppressWarnings("static-access")
	public int doEndTag() throws JspException {
		return super.EVAL_PAGE;
	}

	/**
	 * @Description:释放资源
	 * @author: wangzhao
	 * @date: 2014年5月10日上午10:23:35
	 * @mail: wangzhao@huateng.com
	 */
	public void release() {
		fields = null;
		showCode = null;
		super.release();
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public void setShowCode(String showCode) {
		this.showCode = showCode;
	}

}
