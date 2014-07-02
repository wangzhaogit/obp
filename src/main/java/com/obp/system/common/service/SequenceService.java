package com.obp.system.common.service;

import com.obp.system.model.exception.CommonException;
import com.obp.system.model.metatype.Dto;

/**
 * 
 * @Title:SequenceService.java
 * @Package:com.obp.system.common.service
 * @Description:序列值服务接口
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年5月6日下午8:31:29
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public interface SequenceService {
	
	/**
	 * @Description:跟据TableName查询序列
	 * @author: wangzhao
	 * @date: 2014年5月7日上午8:19:32
	 * @mail: wangzhao@huateng.com
	 * @param tableName<表名>
	 * @return DTO<数据集>
	 * @throws CommonException
	 */
	public Dto searchSysSequenceByTableName(String tableName)throws CommonException;
	
	/**
	 * @Description:保存序列值
	 * @author: wangzhao
	 * @date: 2014年5月7日上午8:21:38
	 * @mail: wangzhao@huateng.com
	 * @param dto
	 * @throws CommonException
	 */
	public void saveSysSequence(Dto dto)throws CommonException;
	
	/**
	 * @Description:更新序列值
	 * @author: wangzhao
	 * @date: 2014年5月7日上午8:21:38
	 * @mail: wangzhao@huateng.com
	 * @param dto
	 * @throws CommonException
	 */
	public void updateSysSequence(Dto dto)throws CommonException;

}
