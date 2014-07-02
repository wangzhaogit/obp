package com.obp.system.model.tplengine;

/**
 * 
 * @Title:FileTemplate.java
 * @Package:com.obp.system.model.tplengine
 * @Description:文件模板
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午3:39:27
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class FileTemplate implements DefaultTemplate{

	/**
	 * 文件模板资源路径
	 */
	private String resource;
	
	/**
	 * 构造函数
	 * @param pResource 文件模板资源路径
	 */
	public FileTemplate(String pResource){
		this.resource = pResource;
	}
    
	/**
	 * 构造函数
	 */
	public FileTemplate() {
	}
    
	/**
	 * 获取文件模板资源路径
	 */
	public String getTemplateResource() {
		return getResource();
	}
	
	/**
	 * 设置文件模板资源路径
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
