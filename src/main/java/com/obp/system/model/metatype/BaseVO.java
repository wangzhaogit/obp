package com.obp.system.model.metatype;

import java.io.Serializable;

import com.obp.system.common.util.OBPUtils;
import com.obp.system.model.metatype.impl.BaseDto;

/**
 * 
 * @Title:BaseVo.java
 * @Package:com.obp.system.model.metatype
 * @Description:简单值对象
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 18, 201410:18:09 AM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public abstract class BaseVO implements Serializable{
	private static final long serialVersionUID = 1L;

	public Dto toDto(){
		Dto dto = new BaseDto();
		OBPUtils.copyPropFromBeanToDto(this, dto);
		return dto;
	}
	
	/*public String toXml(String pStyle){
    	Dto dto = toDto();
    	return dto.toXml(pStyle);
    }*/
	
	public String toJson(){
    	Dto dto = toDto();
    	return dto.toJson();
    }

}
