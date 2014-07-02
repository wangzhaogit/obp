package com.obp.system.common.service;

import java.util.List;

import com.obp.system.model.metatype.impl.BaseDto;

/**
 * 
 * @Title:CodeService.java
 * @Package:com.obp.system.common.service
 * @Description:代码表服务层
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 18, 201411:19:55 AM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public interface CodeService {
	
	/**
	 * @Description:查询出CODE所有数据
	 * @author: wangzhao
	 * @date: Apr 18, 201411:17:38 AM
	 * @mail: wangzhao@huateng.com
	 * @return
	 */
	public List<BaseDto> searchAllCode();

}
