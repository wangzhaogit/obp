package com.obp.system.common.service;

import com.obp.system.model.exception.CommonException;
import com.obp.system.model.metatype.Dto;
import com.obp.system.web.vo.UserInfoVO;

/**
 * 
 * @Title:MonitorService.java
 * @Package:com.obp.system.common.service
 * @Description:系统监控业务接口
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 18, 201410:13:04 AM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public interface MonitorService {
	
	/**
	 * @Description:删除一个托管的会话连接
	 * @author: wangzhao
	 * @date: Apr 18, 201410:18:56 AM
	 * @mail: wangzhao@huateng.com
	 * @param dto
	 */
	public void saveEvent(Dto dto)throws CommonException;
	
	/**
	 * @Description:创建一个事件
	 * @author: wangzhao
	 * @date: Apr 18, 201410:19:03 AM
	 * @mail: wangzhao@huateng.com
	 * @param inDto
	 * @return
	 */
	public Dto deleteEvent(Dto inDto)throws CommonException;
	
	/**
	 * @Description:删除事件
	 * @author: wangzhao
	 * @date: Apr 18, 201410:19:16 AM
	 * @mail: wangzhao@huateng.com
	 * @param inDto
	 * @return
	 */
	public Dto deleteMonitorData(Dto inDto)throws CommonException;
	
	/**
	 * @Description:删除一个托管的会话连接
	 * @author: wangzhao
	 * @date: 2014年4月22日下午3:25:58
	 * @mail: wangzhao@huateng.com
	 * @param dto
	 */
	public void deleteHttpSession(Dto dto)throws CommonException;
	
	/**
	 * @Description:跟据SESSION_ID查询
	 * @author: wangzhao
	 * @date: 2014年4月22日下午6:18:05
	 * @mail: wangzhao@huateng.com
	 * @param sessionId
	 * @throws CommonException
	 */
	public UserInfoVO searchHttpSessionsBySessionId(String sessionId)throws CommonException;
	
	/**
	 * @Description:保存会话信息
	 * @author: wangzhao
	 * @date: 2014年4月22日下午6:26:20
	 * @mail: wangzhao@huateng.com
	 * @param userInfo
	 */
	public void saveHttpSession(UserInfoVO userInfo)throws CommonException;
	
	/**
	 * @Description:保存异常信息
	 * @author: wangzhao
	 * @date: 2014年5月6日下午6:55:45
	 * @mail: wangzhao@huateng.com
	 * @param dto
	 */
	public void saveSysException(Dto dto)throws CommonException;
	
	/**
	 * @Description:查询用户个数
	 * @author: wangzhao
	 * @date: 2014年5月8日下午7:15:11
	 * @mail: wangzhao@huateng.com
	 * @param userNo<用户号>
	 * @return
	 */
	public Integer countHttpSessionByUserNo(String userNo)throws CommonException;
	
}
