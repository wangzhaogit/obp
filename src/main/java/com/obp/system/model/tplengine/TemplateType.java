package com.obp.system.model.tplengine;

/**
 * 
 * @Title:TemplateType.java
 * @Package:com.obp.system.model.tplengine
 * @Description:模板引擎类型
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014-4-19下午2:08:12
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class TemplateType {
	
	/**
	 * 引擎类型
	 */
	private String type;
	
	/**
	 * 引擎描述
	 */
	private String description;
	
	/**
	 * Velocity引擎定义
	 */
	public static final TemplateType VELOCITY = new TemplateType("Velocity", "Velocity engine");

	/**
	 * 构造函数
	 * @param pType 引擎类型
	 * @param pDescription 引擎描述
	 */
	public TemplateType(String pType, String pDescription) {
		this.type = pType;
		this.description = pDescription;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
