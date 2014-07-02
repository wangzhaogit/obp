package com.obp.system.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.obp.system.model.exception.CommonException;

/**
 * 
 * @Title:ApplicationContextUtils.java
 * @Package:com.obp.system.common.util
 * @Description:用来获取ApplicationContext
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 18, 20149:51:34 AM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class ApplicationContextUtils {
	private static ApplicationContext context = null;

	private static Logger logger = LogManager.getLogger();

	public static Object getBean(String beanName) {
		if (null == context){
			logger.entry(beanName);
			logger.error("ApplicationContext没有被初始化");
			throw new CommonException("ApplicationContext没有被初始化");
		}
		return context.getBean(beanName);
	}

	public synchronized static void close() {
		context = null;
	}


	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		ApplicationContextUtils.context = context;
	}
}