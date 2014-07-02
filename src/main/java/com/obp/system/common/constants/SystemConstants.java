package com.obp.system.common.constants;

/**
 * 
 * @Title:SystemConstants.java
 * @Package:com.obp.system.common.constants
 * @Description:系统级常量(勿改动)
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 14, 20149:32:46 AM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class SystemConstants {
	/**
	 * 日期格式化(24小时制)
	 * yyyy-MM-dd HH:mm:ss日期时间
	 */
	public static final String FORMAT_DATE_TIME_24 = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期格式化(12小时制)
	 * yyyy-MM-dd hh:mm:ss日期时间
	 */
	public static final String FORMAT_DATE_TIME_12 = "yyyy-MM-dd hh:mm:ss";
	
	/**
	 * 日期格式化(小时)
	 * yyyy-MM-dd HH:mm:ss日期时间
	 */
	public static final String FORMAT_DATE_TIME_HH = "HH";
	
	/**
	 * 日期格式化10位<br>
	 * yyyy-MM-dd: 日期
	 */
	public static final String FORMAT_DATE_10 = "yyyy-MM-dd";
	
	/**
	 * 日期格式化8位<br>
	 * yyyy-MM-dd: 日期
	 */
	public static final String FORMAT_DATE_8 = "yyyyMMdd";

	/**
	 * 时间格式化(24小时制)<br>
	 * FORMAT_DateTime: 时间
	 */
	public static final String FORMAT_TIME_24 = "HH:mm:ss";
	
	/**
	 * 时间格式化(12小时制)<br>
	 * FORMAT_DateTime: 时间
	 */
	public static final String FORMAT_TIME_12 = "hh:mm:ss";
	
	/**
	 * 强制类加载<br>
	 * 1:强制
	 */
	public static final String FORCELOAD_Y = "1"; 
	
	/**
	 * 强制类加载<br>
	 * 0:不强制
	 */
	public static final String FORCELOAD_N = "0";
	
	/**
	 * 异常信息统一头信息<br>
	 * 非常遗憾的通知您,程序发生了异常
	 */
	public static final String EXCEPTION_HEAD = "非常遗憾的通知您,程序发生了异常";
	
	/**
	 * 数据集合<br>
	 * SYS_CODE表数据
	 */
	public static final String CODE_LIST = "codeList";
	
	/**
	 * 数据集合<br>
	 * SYS_PARAM数据
	 */
	public static final String PARAM_LIST = "paramList";
	
	/**
	 * 数据集合<br>
	 * USER_INFO信息
	 */
	public static final String USER_INFO = "userInfo";

	/**
	 * Ajax请求超时错误码<br>
	 * 999:Ajax请求超时错误码
	 */
	public static final int AJAX_TIME_OUT = 999;
	
	/**
	 * 数据加密<br>
	 * 加密信息，可以跟据证书加密
	 */
	public static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };
	
	/**
	 * 帐户类型<br>
	 * 1:常规帐户
	 */
	public static final String ACCOUNTTYPE_NORMAL = "1";
	
	/**
	 * 帐户类型<br>
	 * 2:SUPER帐户
	 */
	public static final String ACCOUNTTYPE_SUPER = "2";
	
	/**
	 * 帐户类型<br>
	 * 3:DEVELOPER帐户
	 */
	public static final String ACCOUNTTYPE_DEVELOPER = "3";
}
