package com.obp.system.common.service;

import com.obp.system.model.exception.CommonException;
import com.obp.system.model.metatype.Dto;

/**
 * 
 * @Title:AuthorityDataSupport.java
 * @Package:com.obp.system.common.service
 * @Description:权限数据通用处理
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年4月21日下午8:19:54
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public interface AuthorityDataSupportService {
	
	/**
	 * @Description:跟据用户主键查询系统用户附加属性
	 * @author: wangzhao
	 * @date: 2014年4月21日下午8:21:14
	 * @mail: wangzhao@huateng.com
	 * @param userId<用户ID>
	 * @return
	 */
	public Dto searchSysUserSubInfoByUserId(Long userId)throws CommonException;
	

}
