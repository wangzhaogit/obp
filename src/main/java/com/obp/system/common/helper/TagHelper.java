package com.obp.system.common.helper;

import javax.servlet.jsp.tagext.BodyContent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obp.system.common.constants.TagConstant;
import com.obp.system.common.util.OBPUtils;

/**
 * 
 * @Title:TagHelper.java
 * @Package:com.obp.system.common.helper
 * @Description:JSP自定义标签(eRedUI)内部使用的辅助工具类
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午2:25:03
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class TagHelper {
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * 获取模板路径
	 * @param pPath 标签实现类的Java包路径
	 * @return 返回模板路径
	 */
	public static String getTemplatePath(String pPath){
		if(OBPUtils.isEmpty(pPath))
			return "";
		String templatePath = "";
		String path = pPath.replace('.', '/');
		String packageUnits[] = path.split("/");
		String className = packageUnits[packageUnits.length - 1];
		templatePath = path.substring(0, path.length() - className.length());
		templatePath += "template/" + className + ".tpl";
		logger.debug("模板文件路径:" + templatePath);
		return templatePath;
	}
	
	/**
	 * 获取模板路径
	 * @param pPath 标签实现类的Java包路径
	 * @return 返回模板路径
	 */
	public static String getTemplatePath(String pPath,String pFileName){
		if(OBPUtils.isEmpty(pPath))
			return "";
		String templatePath = "";
		String path = pPath.replace('.', '/');
		String packageUnits[] = path.split("/");
		String className = packageUnits[packageUnits.length - 1];
		templatePath = path.substring(0, path.length() - className.length());
		templatePath += "template/" + pFileName;
		logger.debug("模板文件路径:" + templatePath);
		return templatePath;
	}
	
	/**
	 * 对BodyContent进行格式处理
	 * @param pBodyContent 传入的BodyContent对象
	 * @return 返回处理后的BodyContent字符串对象
	 */
	public static String formatBodyContent(BodyContent pBodyContent){
		if(OBPUtils.isEmpty(pBodyContent))
			return "";
		return pBodyContent.getString().trim();
	}
	
	/**
	 * 对字符串模板中的特殊字符进行处理
	 * @param pStr 传入的字符串模板
	 * @return 返回处理后的字符串
	 */
	public static String replaceStringTemplate(String pStr){
		if(OBPUtils.isEmpty(pStr))
			return "";
		pStr = pStr.replace('*','\"');

		return pStr;
	}
	
	/**
	 * 对模板字符型变量进行空校验
	 * @param pString
	 * @return
	 */
	public static String checkEmpty(String pString){
		return OBPUtils.isEmpty(pString) ? TagConstant.Tpl_Out_Off : pString;
	}

}
