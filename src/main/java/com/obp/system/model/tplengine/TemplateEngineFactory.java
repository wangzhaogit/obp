package com.obp.system.model.tplengine;

import java.util.HashMap;
import java.util.Map;

import com.obp.system.model.tplengine.velocity.VelocityTemplateEngine;

/**
 * 
 * @Title:TemplateEngineFactory.java
 * @Package:com.obp.system.model.tplengine
 * @Description:模板引擎工厂
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午2:06:59
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
@SuppressWarnings("unchecked")
public class TemplateEngineFactory {
	
	/**
	 * 引擎容器
	 */
	@SuppressWarnings("rawtypes")
	private static Map ENGINES = new HashMap();
	
	/**
	 * 实例化模板引擎并压入引擎容器
	 */
	static{
		if (isExistClass("org.apache.velocity.app.VelocityEngine")){
			VelocityTemplateEngine ve = new VelocityTemplateEngine();
			ENGINES.put(TemplateType.VELOCITY, ve);
		}else{
			
		}
	}
	
	/**
	 * 检查当前ClassLoader种,是否存在指定class
	 * @param pClass
	 * @return
	 */
	private static boolean isExistClass(String pClass) {
		try {
			Class.forName(pClass);
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 获取模板引擎实例
	 * @param pTemplateType 引擎类型
	 * @return 返回模板引擎实例
	 */
	public static TemplateEngine getTemplateEngine(TemplateType pType) {
		if (pType == null) {
			return null;
		}
		if (ENGINES.containsKey(pType) == false) {
			throw new IllegalArgumentException("不支持的模板类别:" + pType.getType());
		}
		return (TemplateEngine) ENGINES.get(pType);
	}

}
