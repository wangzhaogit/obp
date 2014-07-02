package com.obp.system.common.util;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.obp.system.model.metatype.Dto;

public class OBPUtils{

	private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };
	private static Logger logger = LogManager.getLogger();
	/**
	 * @Description:将属性值复制到DTO
	 * @author: wangzhao
	 * @date: Apr 18, 20141:59:17 PM
	 * @mail: wangzhao@huateng.com
	 * @param pFromObj
	 * @param dto
	 */
	@SuppressWarnings("unchecked")
	public static void copyPropFromBeanToDto(Object pFromObj, Dto dto){
		try {
			dto.putAll(BeanUtils.describe(pFromObj));
			dto.remove("class");
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @Description:判断对象是否Empty
	 * @author: wangzhao
	 * @date: 2014-4-19下午2:21:40
	 * @mail: wangzhao@huateng.com
	 * @param pObj<待检查对象>
	 * @return <返回的布尔值?
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object pObj) {
		if (pObj == null)
			return true;
		if (pObj == "")
			return true;
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return true;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return true;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @Description:判断对象是否为NotEmpty
	 * @author: wangzhao
	 * @date: 2014年4月22日上午9:00:06
	 * @mail: wangzhao@huateng.com
	 * @param pObj<待检查对象>
	 * @return<返回的布尔值>
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Object pObj) {
		if (pObj == null)
			return false;
		if (pObj == "")
			return false;
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return false;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return false;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @Description:数据加密(DES)
	 * @author: wangzhao
	 * @date: 2014年4月23日下午2:05:54
	 * @mail: wangzhao@huateng.com
	 * @param data
	 * @return
	 */
	public static String encryptBasedDes(String data) {
		String encryptedData = null;
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			encryptedData = new BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
		} catch (Exception e) {
			logger.error("加密错误，错误信息：", e);
			throw new RuntimeException("加密错误，错误信息：", e);
		}
		return encryptedData;
	}

	/**
	 * @Description:数据解密(DES)
	 * @author: wangzhao
	 * @date: 2014年4月23日下午2:57:17
	 * @mail: wangzhao@huateng.com
	 * @param cryptData
	 * @return
	 */
	public static String decryptBasedDes(String cryptData) {
		String decryptedData = null;
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			decryptedData = new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(cryptData)));
		} catch (Exception e) {
			logger.error("解密错误，错误信息：", e);
			throw new RuntimeException("解密错误，错误信息：", e);
		}
		return decryptedData;
	}
	
	/**
	 * @Description:获取客户端类型
	 * @author: wangzhao
	 * @date: 2014年4月23日下午3:55:21
	 * @mail: wangzhao@huateng.com
	 * @param request
	 * @return
	 */
	public static String getClientExplorerType(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		String explorer = "非主流浏览器";
		if (! (userAgent.indexOf("msie") == -1)) {
			int index = userAgent.indexOf("msie");
			explorer = userAgent.substring(index, index + 8);
		} else if(! (userAgent.indexOf("chrome") == -1)) {
			int index = userAgent.indexOf("chrome");
			explorer = userAgent.substring(index, index + 12);
		} else if(! (userAgent.indexOf("firefox") == -1)) {
			int index = userAgent.indexOf("firefox");
			explorer = userAgent.substring(index, index + 11);
		}
		return explorer.toUpperCase();
	}

}
