package com.obp.system.common.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obp.system.model.exception.CommonException;

public class PropertiesHelper {
	private static Logger logger = LogManager.getLogger();
	private Properties objProperties;
	
	/**
	 * 
	 * @Description:构造数据流
	 *
	 * @author:wangzhao
	 * @date:2014年4月22日上午9:11:08
	 * @mail:wangzhao@huateng.com
	 */
	public PropertiesHelper(InputStream is) throws CommonException {
		try{
			objProperties = new Properties();
			objProperties.load(is);
		}
		catch(FileNotFoundException e){
			logger.error("未找到属性资源文件!");
			e.printStackTrace();
			throw new CommonException(e);
		}
		catch(Exception e){
			logger.error("读取属性资源文件发生未知错误!");
			e.printStackTrace();
			throw new CommonException(e);
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new CommonException(e);
			}
		}
	}
	
	/**
	 * @Description:持久化属性文件
	 * @author: wangzhao
	 * @date: 2014年4月22日上午9:24:02
	 * @mail: wangzhao@huateng.com
	 * @param pFileName
	 */
	public void storefile(String pFileName){
		FileOutputStream outStream = null;
		try{
			File file = new File(pFileName + ".properties");
			outStream = new FileOutputStream(file);
			objProperties.store(outStream, "#G4Studio");
		}catch(Exception e){
			logger.error("保存属性文件出错.");
			e.printStackTrace();
		}finally{
			try {
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @Description:获取属性值
	 * @author: wangzhao
	 * @date: 2014年4月22日上午9:24:39
	 * @mail: wangzhao@huateng.com
	 * @param key<指定Key值，获取value>
	 * @return<返回属性值>
	 */
	public String getValue(String key){
		return objProperties.getProperty(key);
	}

	/**
	 * @Description:获取属性值,支持缺省设置
	 * @author: wangzhao
	 * @date: 2014年4月22日上午9:25:07
	 * @mail: wangzhao@huateng.com
	 * @param key<key值>
	 * @param defaultValue<缺省值>
	 * @return<返回属性值>
	 */
	public String getValue(String key, String defaultValue){
		return objProperties.getProperty(key, defaultValue);
	}

	/**
	 * @Description:删除属性
	 * @author: wangzhao
	 * @date: 2014年4月22日上午9:25:40
	 * @mail: wangzhao@huateng.com
	 * @param key<属性Key>
	 */
	public void removeProperty(String key){
		objProperties.remove(key);
	}
	
	/**
	 * @Description:设置属性
	 * @author: wangzhao
	 * @date: 2014年4月22日上午9:25:54
	 * @mail: wangzhao@huateng.com
	 * @param key<属性Key>
	 * @param value<属性值>
	 */
	public void setProperty(String key, String value){
		objProperties.setProperty(key, value);
	}
	
	/**
	 * @Description:打印所有属性值
	 * @author: wangzhao
	 * @date: 2014年4月22日上午9:26:19
	 * @mail: wangzhao@huateng.com
	 */
	public void printAllVlue(){
		 objProperties.list(System.out);
	}

}
