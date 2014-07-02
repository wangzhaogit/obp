package com.obp.system.common.service;

import java.util.List;

import com.obp.system.model.metatype.impl.BaseDto;

/**
 * 
 * @Title:ParamService.java
 * @Package:com.obp.system.common.service
 * @Description:参数数据服务接口
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 18, 201411:29:52 AM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public interface ParamService {
	
	public List<BaseDto> searchAllParam();

}
