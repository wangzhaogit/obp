package com.obp.system.model.tplengine;

/**
 * 
 * @Title:StringTemplate.java
 * @Package:com.obp.system.model.tplengine
 * @Description:字符串模板
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午2:31:38
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class StringTemplate implements DefaultTemplate{

	/**
	 * 字符串模板内容
	 */
	private String resource;
	
	/**
	 * 构造函数
	 * @param pResource 字符串模板内容
	 */
	public StringTemplate(String pResource){
		this.resource = pResource;
	}
	
	/**
	 * 缺省构造函数
	 */
	public StringTemplate() {
	}

	/**
	 * 获取字符串模板内容
	 */
	public String getTemplateResource() {
		return getResource();
	}

	/**
	 * 设置字符串模板内容
	 */
	public void setTemplateResource(String pResource) {
		this.resource = pResource;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

}
