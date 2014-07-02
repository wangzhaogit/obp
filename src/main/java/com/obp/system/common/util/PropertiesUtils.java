package com.obp.system.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.obp.system.common.constants.SystemConstants;

/**
 * 
 * @Title:PropertiesUtils.java
 * @Package:com.obp.system.common.util
 * @Description:Properties工具类
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 14, 201410:13:39 AM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class PropertiesUtils {
	private static Log log = LogFactory.getLog(PropertiesUtils.class);
	private Properties objProperties;
	
	/**
	 * 构造函数
	 * @param is<属性文件输入流>
	 * @throws Exception
	 */
	public PropertiesUtils(InputStream is) throws Exception {
		try{
			objProperties = new Properties();
			objProperties.load(is);
		}
		catch(FileNotFoundException e){
			log.error(SystemConstants.EXCEPTION_HEAD + "未找到属性资源文件!");
			e.printStackTrace();
			throw e;
		}
		catch(Exception e){
			log.error(SystemConstants.EXCEPTION_HEAD + "读取属性资源文件发生未知错误!");
			e.printStackTrace();
			throw e;
		}finally{
			is.close();
		}
	}
	
	/**
     * 持久化属性文件<br>
     * 使用setProperty()设置属性后,必须调用此方法才能将属性持久化到属性文件中
     * @param pFileName<文件名>
     * @throws IOException 
     */
	public PropertiesUtils(String pFileName){
		InputStream inputStream = null;
		try{
			File file = new File(pFileName);
			inputStream = new FileInputStream(file);
			objProperties.load(inputStream);
		}catch(Exception e){
			log.error(SystemConstants.EXCEPTION_HEAD + "保存属性文件出错.");
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

    /**
     * 获取属性值
     * @param key<属性Key>
     * @return String<返回属性值>
     */
	public String getValue(String key){
		return objProperties.getProperty(key);
	}

    /**
     * 获取属性值,支持缺省设置
     * @param key<属性Key>
     * @param defaultValue<缺省值>
     * @return String<返回属性值>
     */
	public String getValue(String key, String defaultValue){
		return objProperties.getProperty(key, defaultValue);
	}

    /**
     * 删除属性
     * @param key<属性Key>
     */
	public void removeProperty(String key){
		objProperties.remove(key);
	}
	
    /**
     * 设置属性
     * @param key<属性Key>
     * @param value<属性值>
     */
	public void setProperty(String key, String value){
		objProperties.setProperty(key, value);
	}
	
    /**
     * 打印所有属性值
     */
	public void printAllVlue(){
		 objProperties.list(System.out);
	}

}
