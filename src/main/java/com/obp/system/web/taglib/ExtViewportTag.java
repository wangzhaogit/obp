package com.obp.system.web.taglib;

import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obp.system.common.constants.SystemConstants;
import com.obp.system.common.entity.MenuVO;
import com.obp.system.common.helper.TagHelper;
import com.obp.system.common.service.AuthorityDataSupportService;
import com.obp.system.common.service.DepartmentService;
import com.obp.system.common.service.MenuService;
import com.obp.system.common.util.ApplicationContextUtils;
import com.obp.system.common.util.DateUtils;
import com.obp.system.common.util.OBPUtils;
import com.obp.system.common.util.WebUtils;
import com.obp.system.model.SessionContainer;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.model.tplengine.DefaultTemplate;
import com.obp.system.model.tplengine.FileTemplate;
import com.obp.system.model.tplengine.TemplateEngine;
import com.obp.system.model.tplengine.TemplateEngineFactory;
import com.obp.system.model.tplengine.TemplateType;

/**
 * 
 * @Title:ExtViewportTag.java
 * @Package:com.obp.system.web.taglib
 * @Description:展示框架标签
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年4月25日下午12:01:11
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class ExtViewportTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger();
	private String northTitle = "";
	private String westTitle = "";
	private String scriptStart = "<script type=\"text/javascript\">";
	private String scriptEnd = "</script>";

	/**
	 * @Description:标签初始方法
	 * @author: wangzhao
	 * @date: 2014年4月26日下午3:12:36
	 * @mail: wangzhao@huateng.com
	 * @return
	 * @throws JspException
	 */
	@SuppressWarnings("static-access")
	public int doStartTag() throws JspException {
		return super.SKIP_BODY;
	}

	/**
	 * @Description:标签主体
	 * @author: wangzhao
	 * @date: 2014年4月26日下午3:12:45
	 * @mail: wangzhao@huateng.com
	 * @return
	 * @throws JspException
	 */
	@SuppressWarnings("static-access")
	public int doEndTag() throws JspException {
		JspWriter writer = pageContext.getOut();
		try {
			writer.print(getPanelScript());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return super.EVAL_PAGE;
	}

	/**
	 * @Description:获取Viewport标记脚本
	 * @author: wangzhao
	 * @date: 2014年4月26日下午3:12:56
	 * @mail: wangzhao@huateng.com
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getPanelScript() {
		MenuService menuService = (MenuService)ApplicationContextUtils.getBean("menuService");
		DepartmentService departmentService = (DepartmentService)ApplicationContextUtils.getBean("departmentService");
		AuthorityDataSupportService authorityDataSupportService = (AuthorityDataSupportService)ApplicationContextUtils.getBean("authorityDataSupportService");
		
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		Dto dto = new BaseDto();
		dto.put("contextPath", request.getContextPath());
		dto.put("northTitle", northTitle);
		dto.put("centerTitle",
				OBPUtils.isEmpty(WebUtils.getParamValue("MENU_FIRST", request)) ? "请配置" : WebUtils.getParamValue(
						"MENU_FIRST", request));
		dto.put("welcomePageTitle", OBPUtils.isEmpty(WebUtils.getParamValue("WELCOME_PAGE_TITLE", request)) ? "请配置" : WebUtils.getParamValue(
				"WELCOME_PAGE_TITLE", request));
		dto.put("banner", request.getContextPath() + WebUtils.getParamValue("INDEX_BANNER", request));
		dto.put("westTitle", westTitle);
		dto.put("scriptStart", scriptStart);
		dto.put("scriptEnd", scriptEnd);
		dto.put("copyright", WebUtils.getParamValue("BOTTOM_COPYRIGHT", request));
		String activeOnTop = "true";
		if ("0".equals(WebUtils.getParamValue("WEST_CARDMENU_ACTIVEONTOP", request))) {
			activeOnTop = "false";
		}
		dto.put("activeOnTop", activeOnTop);
		SessionContainer sessionContainer = WebUtils.getSessionContainer(request);
		Long userId = sessionContainer.getUserInfo().getUserId();
		Dto dto2 = new BaseDto();
		dto2.put("userId", userId);
		String userNo = sessionContainer.getUserInfo().getUserNo()== null ? "" : sessionContainer.getUserInfo().getUserNo();
		String accountType = SystemConstants.ACCOUNTTYPE_NORMAL;
		if (userNo.equalsIgnoreCase(WebUtils.getParamValue("DEFAULT_ADMIN_ACCOUNT", request))) {
			accountType = SystemConstants.ACCOUNTTYPE_SUPER;
		} else if (userNo.equalsIgnoreCase(WebUtils.getParamValue("DEFAULT_DEVELOP_ACCOUNT", request))) {
			accountType = SystemConstants.ACCOUNTTYPE_DEVELOPER;
		}
		dto2.put("userType", accountType);
		dto.put("userType", accountType);
		List<MenuVO> cardList = menuService.getCardList(dto2).getDefaultAList();
		for(int i = 0; i < cardList.size(); i++) {
			MenuVO cardVo = (MenuVO) cardList.get(i);
			if (i != cardList.size() - 1) {
				cardVo.setIsNotLast("true");
			}
		}
		dto.put("date", DateUtils.getCurrentDate_10());
		dto.put("week", DateUtils.getWeekDayByDate(DateUtils.getCurrentDate_10()));
		dto.put("welcome", getWelcomeMsg());
		dto.put("cardList", cardList);
		dto.put("userName", sessionContainer.getUserInfo().getUserName());
		dto.put("userNo", sessionContainer.getUserInfo().getUserNo());
		/*dto.put("deptName", departmentService.searchSysDepartmentByDeptId(sessionContainer.getUserInfo().getDeptId()).getAsString("deptName"));
		Dto resultDto = new BaseDto();
		resultDto = authorityDataSupportService.searchSysUserSubInfoByUserId(sessionContainer.getUserInfo().getUserId());
		String theme = resultDto.getAsString("theme");
		theme = OBPUtils.isEmpty(theme) ? "default" : theme;
		dto.put("theme", theme);
		String layout = null;
		if(OBPUtils.isNotEmpty(resultDto))
			layout = resultDto.getAsString("layout");
		String defaultLayout = WebUtils.getParamValue("APP_LAYOUT", request);
		layout = OBPUtils.isEmpty(layout) ? defaultLayout : layout;
		dto.put("layout", layout);
		dto.put("themeColor", getThemeColor(theme));*/
		TemplateEngine engine = TemplateEngineFactory.getTemplateEngine(TemplateType.VELOCITY);
		DefaultTemplate template = new FileTemplate();
		template.setTemplateResource(TagHelper.getTemplatePath(getClass().getName()));
		StringWriter writer = engine.mergeTemplate(template, dto);
		String treesString = generateCardTrees(dto);
		return treesString + "\n" + writer.toString();
	}

	/**
	 * @Description:生成卡片树
	 * @author: wangzhao
	 * @date: 2014年5月10日下午3:49:09
	 * @mail: wangzhao@huateng.com
	 * @param pDto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String generateCardTrees(Dto pDto) {
		MenuService menuService = (MenuService)ApplicationContextUtils.getBean("menuService");
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		SessionContainer sessionContainer = WebUtils.getSessionContainer(request);
		Long userId = sessionContainer.getUserInfo().getUserId();
		Dto qDto = new BaseDto();
		qDto.put("userid", userId);
		List<MenuVO> cardList = (List<MenuVO>) pDto.get("cardList");
		String treesString = scriptStart + "Ext.onReady(function(){";
		for (MenuVO menuVO : cardList) {
			qDto.put("menuId", menuVO.getMenuId());
			qDto.put("accountType", pDto.getAsString("accountType"));
			List<MenuVO> menuList = menuService.getCardTreeList(qDto).getDefaultAList();
			String rootName = (String) menuService.searchSysMenuNameByMenuId("01");
			Dto pathDto = new BaseDto();
			pathDto.put("01", rootName);
			Dto dto = new BaseDto();
			dto.put("menuList", generateMenuPathName(menuList, pathDto));
			dto.put("menuId", menuVO.getMenuId());
			TemplateEngine engine = TemplateEngineFactory.getTemplateEngine(TemplateType.VELOCITY);
			DefaultTemplate template = new FileTemplate();
			template.setTemplateResource(TagHelper.getTemplatePath(getClass().getName(), "CardTreesTag.tpl"));
			StringWriter writer = engine.mergeTemplate(template, dto);
			treesString = treesString + "\n" + writer.toString();
		}
		return treesString + "\n});" + scriptEnd;
	}

	/**
	 * 生成菜单路径对应中文名
	 * 
	 * @param pMenuList
	 *            菜单列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<MenuVO> generateMenuPathName(List<MenuVO> pMenuList, Dto pDto) {
		for (MenuVO menuVO : pMenuList) {
			pDto.put(menuVO.getMenuId(), menuVO.getMenuName());
		}
		for (MenuVO menuVO : pMenuList) {
			String path = "";
			String menuId = menuVO.getMenuId();
			int temp = menuId.length() / 2;
			int m = 0, k = 2;
			for (int j = 0; j < temp; j++) {
				path += pDto.getAsString(menuId.substring(m, k)) + " -> ";
				k += 2;
			}
			menuVO.setMenuPath(path.substring(0, path.length() - 4));
		}
		return pMenuList;
	}

	/**
	 * 释放资源
	 */
	public void release() {
		super.release();
		northTitle = null;
		westTitle = null;
	}

	/**
	 * 生成问候信息
	 * 
	 * @return
	 */
	private String getWelcomeMsg() {
		String welcome = "晚上好";
		Integer timeInteger = new Integer(DateUtils.getCurrentDateTime(SystemConstants.FORMAT_DATE_TIME_HH));
		if (timeInteger.intValue() >= 7 && timeInteger.intValue() <= 12) {
			welcome = "上午好";
		} else if (timeInteger.intValue() > 12 && timeInteger.intValue() < 19) {
			welcome = "下午好";
		}
		return welcome;
	}

	/**
	 * 获取和主题对应匹配的颜色值
	 */
	private String getThemeColor(String theme) {
		String color = "slategray";
		if (theme.equalsIgnoreCase("default")) {
			color = "4798D7";
		} else if (theme.equalsIgnoreCase("lightRed")) {
			color = "F094C9";
		} else if (theme.equalsIgnoreCase("lightYellow")) {
			color = "EAAA85";
		} else if (theme.equalsIgnoreCase("gray")) {
			color = "969696";
		} else if (theme.equalsIgnoreCase("lightGreen")) {
			color = "53E94E";
		} else if (theme.equalsIgnoreCase("purple2")) {
			color = "BC5FD8";
		}else if (theme.equalsIgnoreCase("red")) {
			color = "FF3300";
		}
		return color;
	}

	public void setNorthTitle(String northTitle) {
		this.northTitle = northTitle;
	}

	public void setWestTitle(String westTitle) {
		this.westTitle = westTitle;
	}

	public void setScriptStart(String scriptStart) {
		this.scriptStart = scriptStart;
	}

	public void setScriptEnd(String scriptEnd) {
		this.scriptEnd = scriptEnd;
	}

}
