package com.obp.system.common.helper;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.obp.system.common.constants.PropertiesConstants;
import com.obp.system.common.util.OBPUtils;
import com.obp.system.model.exception.CommonException;
import com.obp.system.model.metatype.Dto;

/**
 * 
 * @Title:VelocityHelper.java
 * @Package:com.obp.system.common.helper
 * @Description:Velocity模板引擎辅助类
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午2:17:46
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class VelocityHelper {
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * 实例化Velocity模板引擎并返回<br>
	 * <b>说明:</b>为了避免VelocityEngine跟其他项目用到的VelocityEngine产生冲突,所以这里使用多实例引擎,
	 * 而不是单一实例(singleton)引擎.
	 * @return 返回VelocityEngine实例
	 * @throws InitVelocityEngineException 
	 */
	public static VelocityEngine getVelocityEngine() throws CommonException{
		VelocityEngine ve = new VelocityEngine();
		try {
			ve.init(getDefaultProperties());
		} catch (Exception e) {
			logger.error(e);
			throw new CommonException(e.getMessage());
		}
		return ve;
	}
	
	/**
	 * 加载Velocity模板引擎属性配置文件
	 * @return
	 */
	public static Properties getDefaultProperties() {
	   	InputStream is = VelocityHelper.class.getResourceAsStream(PropertiesConstants.VELOCITY_PROPERTIES);
	   	Properties props = new Properties();
		try {
			props.load(is);
			is.close();
		} catch (Exception e) {
		    logger.error("导入Velocity模板引擎属性配置文件出错");
		    logger.error(e.getMessage());
		}
			return props;
		}
	
	/**
	 * 将Dto对象转换为VelocityContext对象
	 * @param pDto 传入的Dto对象
	 * @return 返回VelocityContext对象
	 */
	@SuppressWarnings("unchecked")
	public static VelocityContext convertDto2VelocityContext(Dto pDto){
		if(OBPUtils.isEmpty(pDto))
			return null;
		VelocityContext context = new VelocityContext();
		Set<String> set = pDto.keySet();
		for(String key : set){
			Object value = pDto.get(key);
			context.put(key, value);
		}
		return context;
		
	}

}
