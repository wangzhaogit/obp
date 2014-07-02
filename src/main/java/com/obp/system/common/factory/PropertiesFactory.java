package com.obp.system.common.factory;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obp.system.common.constants.PropertiesConstants;
import com.obp.system.common.helper.PropertiesHelper;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;

/**
 * 
 * @Title:PropertiesFactory.java
 * @Package:com.obp.system.common.factory
 * @Description:属性文件静态工厂
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年4月22日上午9:17:39
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
@SuppressWarnings("unchecked")
public class PropertiesFactory {
	private static Logger logger = LogManager.getLogger();
	private static Dto container = new BaseDto();
	
	static {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = PropertiesFactory.class.getClassLoader();
		}
		// 加载属性文件global.g4.properties
		try {
			InputStream is = classLoader.getResourceAsStream(PropertiesConstants.SYSTEM_PROPERTIES);
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesConstants.SYSTEM, ph);
		} catch (Exception e1) {
			logger.error("加载属性文件"+PropertiesConstants.SYSTEM_PROPERTIES+"出错!");
			e1.printStackTrace();
		}
	}
	
	/**
	 * @Description:获取属性文件实例
	 * @author: wangzhao
	 * @date: 2014年4月22日上午9:19:38
	 * @mail: wangzhao@huateng.com
	 * @param pFile<文件类型>
	 * @return<返回属性文件实例>
	 */
	public static PropertiesHelper getPropertiesHelper(String pFile) {
		PropertiesHelper ph = (PropertiesHelper) container.get(pFile);
		return ph;
	}

}
