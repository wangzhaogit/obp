package com.obp.system.common.service;

import com.obp.system.model.exception.CommonException;
import com.obp.system.model.metatype.Dto;

/**
 * 
 * @Title:MenuService.java
 * @Package:com.obp.system.common.service
 * @Description:菜单权权服务层
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年5月9日下午7:21:00
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public interface MenuService{
	/**
	 * @Description:获取菜单卡片
	 * @author: wangzhao
	 * @date: 2014年5月9日下午7:23:54
	 * @mail: wangzhao@huateng.com
	 * @param pDto
	 * @return
	 * @throws CommonException
	 */
	public Dto getCardList(Dto pDto)throws CommonException;
	
	/**
	 * @Description:获取用户的卡片
	 * @author: wangzhao
	 * @date: 2014年5月9日下午8:12:30
	 * @mail: wangzhao@huateng.com
	 * @param userNo
	 * @return
	 * @throws CommonException
	 */
	public Dto getCardTreeList(Dto pDto)throws CommonException;
	
	/**
	 * @Description:跟据菜单号查询菜单名称
	 * @author: wangzhao
	 * @date: 2014年5月10日上午9:57:06
	 * @mail: wangzhao@huateng.com
	 * @param menuId
	 * @return
	 * @throws CommonException
	 */
	public String searchSysMenuNameByMenuId(String menuId)throws CommonException;
}