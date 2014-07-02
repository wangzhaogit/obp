package com.obp.system.model.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.obp.system.common.constants.SystemConstants;
import com.obp.system.common.service.CodeService;
import com.obp.system.common.service.MonitorService;
import com.obp.system.common.service.ParamService;
import com.obp.system.common.util.ApplicationContextUtils;
import com.obp.system.model.metatype.impl.BaseDto;

/**
 * 系统启动监听器
 * @Title:SystemInitListener.java
 * @Package:com.obp.system.model.listener
 * @Description:
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 14, 201410:07:32 AM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class SystemInitListener implements ServletContextListener{
	private static Logger logger = LogManager.getLogger();
	public void contextDestroyed(ServletContextEvent arg0){
		
	}

	public void contextInitialized(ServletContextEvent arg0){
		systemStartup(arg0.getServletContext());
	}
	/**
	 * @Description:服务启动监听
	 * @author: wangzhao
	 * @date: Apr 14, 20141:59:23 PM
	 * @mail: wangzhao@huateng.com
	 * @param servletContext
	 */
	private void systemStartup(ServletContext servletContext){
		logger.debug("*********************************");
		logger.debug("OBP公共基础平台开始启动...");
		logger.debug("*********************************");
		try {
			ApplicationContextUtils.setContext(WebApplicationContextUtils.getWebApplicationContext(servletContext));
			logger.debug("系统开始删除托管的会话信息...");
			MonitorService monitorService = (MonitorService)ApplicationContextUtils.getBean("monitorService");
			monitorService.deleteHttpSession(new BaseDto());
			
			logger.debug("系统开始加载字典...");
			CodeService codeService = (CodeService)ApplicationContextUtils.getBean("codeService");
			List<BaseDto> codeList = codeService.searchAllCode();
			servletContext.setAttribute(SystemConstants.CODE_LIST, codeList);
			
			logger.debug("系统开始加载全局参数...");
			ParamService paramService = (ParamService)ApplicationContextUtils.getBean("paramService");
			List<BaseDto> paramList = paramService.searchAllParam();
			servletContext.setAttribute(SystemConstants.PARAM_LIST, paramList);
			
			logger.debug("*********************************");
			logger.debug("OBP公共基础平台启动成功...");
			logger.debug("*********************************");
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			logger.debug("*********************************");
			logger.debug("OBP公共基础平台启动失败...");
			logger.debug("*********************************");
		}
	}
	
	
}
