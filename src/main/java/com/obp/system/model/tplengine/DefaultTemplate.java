package com.obp.system.model.tplengine;

/**
 * 
 * @Title:DefaultTemplate.java
 * @Package:com.obp.system.model.tplengine
 * @Description:模板接口
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午2:05:15
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public interface DefaultTemplate {
	
	/**
	 * @Description:设置字符串模板内容或文件模板资源路径
	 * @author: wangzhao
	 * @date: 2014-4-19下午2:05:27
	 * @mail: wangzhao@huateng.com
	 * @param pResource
	 */
	public void setTemplateResource(String pResource);
	
	/**
	 * @Description:获取字符串模板内容或文件模板资源路径
	 * @author: wangzhao
	 * @date: 2014-4-19下午2:05:38
	 * @mail: wangzhao@huateng.com
	 * @return
	 */
	public String getTemplateResource();
}
