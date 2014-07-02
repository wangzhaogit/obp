package com.obp.system.web.taglib;

import java.io.IOException;
import java.io.StringWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obp.system.common.constants.PropertiesConstants;
import com.obp.system.common.constants.SystemConstants;
import com.obp.system.common.constants.TagConstant;
import com.obp.system.common.factory.PropertiesFactory;
import com.obp.system.common.helper.PropertiesHelper;
import com.obp.system.common.helper.TagHelper;
import com.obp.system.common.service.AuthorityDataSupportService;
import com.obp.system.common.util.OBPUtils;
import com.obp.system.common.util.WebUtils;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.model.tplengine.DefaultTemplate;
import com.obp.system.model.tplengine.FileTemplate;
import com.obp.system.model.tplengine.TemplateEngine;
import com.obp.system.model.tplengine.TemplateEngineFactory;
import com.obp.system.model.tplengine.TemplateType;
import com.obp.system.web.vo.UserInfoVO;

/**
 * 
 * @Title:HtmlTag.java
 * @Package:com.obp.system.web.taglib
 * @Description:HTML标签
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午4:44:21
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class HtmlTag extends TagSupport{

	private static final long serialVersionUID = 1L;

	@Inject
	private AuthorityDataSupportService authorityDataSupportService;
	
	private static Logger logger = LogManager.getLogger();
	private String extDisabled;
	private String title;
	private String jqueryEnabled;
	private String showLoading;
	private String uxEnabled = "true";
	private String fcfEnabled = "false";
	private String doctypeEnable="false"; 
	private String exportParams = "false";
	private String exportUserinfo = "false";
	private String isSubPage = "true";
	private String urlSecurity2 = "true";
	private String exportExceptionWindow = "false";
	
	/**
	 * 标签开始
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public int doStartTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		UserInfoVO userInfo = (UserInfoVO)WebUtils.getSessionAttribute(request, SystemConstants.USER_INFO);
		String contextPath = request.getContextPath();
		request.setAttribute("webContext", contextPath);
		Dto dto = new BaseDto();
		PropertiesHelper pHelper = PropertiesFactory.getPropertiesHelper(PropertiesConstants.SYSTEM);
		String micolor = pHelper.getValue("micolor", "blue");
		dto.put("micolor", micolor);
		String urlSecurity = pHelper.getValue("urlSecurity", "1");
		dto.put("urlSecurity", urlSecurity);
		dto.put("urlSecurity2", urlSecurity2);
		dto.put("userInfo", userInfo);
		dto.put("ajaxErrCode", SystemConstants.AJAX_TIME_OUT);
		dto.put("requestURL", request.getRequestURL());
		dto.put("contextPath", contextPath);
		dto.put("doctypeEnable", doctypeEnable);
		dto.put("extDisabled", OBPUtils.isEmpty(extDisabled) ? "false" : extDisabled);
		dto.put("title", OBPUtils.isEmpty(title) ? "OBP" : title);
		dto.put("jqueryEnabled", OBPUtils.isEmpty(jqueryEnabled) ? "false" : jqueryEnabled);
		dto.put("showLoading", OBPUtils.isEmpty(showLoading) ? "true" : showLoading);
		dto.put("uxEnabled", uxEnabled);
		dto.put("exportExceptionWindow", exportExceptionWindow);
		dto.put("fcfEnabled", fcfEnabled);
		dto.put("exportParams", exportParams);
		dto.put("exportUserinfo", exportUserinfo);
		dto.put("isSubPage", isSubPage);
		dto.put("pageLoadMsg", WebUtils.getParamValue("PAGE_LOAD_MSG", request));
		String titleIcon = WebUtils.getParamValue("TITLE_ICON", request);
		dto.put("titleIcon", OBPUtils.isEmpty(titleIcon) ? "G4Studio.ico" : titleIcon);
		if (exportParams.equals("true")) {
			dto.put(SystemConstants.PARAM_LIST, WebUtils.getParamList(request));
		}
		PropertiesHelper p = PropertiesFactory.getPropertiesHelper(PropertiesConstants.SYSTEM);
		dto.put("extMode", p.getValue("extMode", TagConstant.Ext_Mode_Run));
		dto.put("runMode", p.getValue("runMode", TagConstant.RUN_MODE_NORMAL));
		Dto resultDto = new BaseDto();
		if(OBPUtils.isNotEmpty(userInfo)){
			resultDto = authorityDataSupportService.searchSysUserSubInfoByUserId(userInfo.getUserId());
		}
		String theme = null;
		if(OBPUtils.isNotEmpty(resultDto))
			theme = resultDto.getAsString("theme");
		String defaultTheme = WebUtils.getParamValue("SYS_DEFAULT_THEME", request);
		theme = OBPUtils.isEmpty(theme) ? defaultTheme : theme;
		dto.put("theme", theme);
		TemplateEngine engine = TemplateEngineFactory.getTemplateEngine(TemplateType.VELOCITY);
		DefaultTemplate template = new FileTemplate();
		template.setTemplateResource(TagHelper.getTemplatePath(getClass().getName()));
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
			pageContext.getOut().write("</html>");
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
		extDisabled = null;
		jqueryEnabled = null;
		uxEnabled = null;
		fcfEnabled = null;
		doctypeEnable = null;
		exportParams = null;
		exportUserinfo = null;
		isSubPage = null;
		urlSecurity2 = null;
		super.release();
	}

	public void setExtDisabled(String extDisabled) {
		this.extDisabled = extDisabled;
	}

	public void setJqueryEnabled(String jqueryEnabled) {
		this.jqueryEnabled = jqueryEnabled;
	}

	public void setShowLoading(String showLoading) {
		this.showLoading = showLoading;
	}

	public void setUxEnabled(String uxEnabled) {
		this.uxEnabled = uxEnabled;
	}

	public String getFcfEnabled() {
		return fcfEnabled;
	}

	public void setFcfEnabled(String fcfEnabled) {
		this.fcfEnabled = fcfEnabled;
	}

	public void setDoctypeEnable(String doctypeEnable) {
		this.doctypeEnable = doctypeEnable;
	}

	public void setExportParams(String exportParams) {
		this.exportParams = exportParams;
	}

	public void setExportUserinfo(String exportUserinfo) {
		this.exportUserinfo = exportUserinfo;
	}

	public void setIsSubPage(String isSubPage) {
		this.isSubPage = isSubPage;
	}

	public void setUrlSecurity2(String urlSecurity2) {
		this.urlSecurity2 = urlSecurity2;
	}

	public void setExportExceptionWindow(String exportExceptionWindow) {
		this.exportExceptionWindow = exportExceptionWindow;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
