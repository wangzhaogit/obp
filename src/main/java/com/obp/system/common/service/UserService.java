package com.obp.system.common.service;

import java.util.List;

import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.web.vo.UserInfoVO;
public interface UserService{
	/**
	 * @Description:查询所有用户信息集合
	 * @author: wangzhao
	 * @date: Apr 13, 20149:17:51 AM
	 * @mail: wangzhao@huateng.com
	 * @return List<用户信息>
	 */
	public List<BaseDto> searchAllSysUser();
	
	/**
	 * @Description:跟据用户主键查询用户信息
	 * @author: wangzhao
	 * @date: Apr 13, 20149:18:53 AM
	 * @mail: wangzhao@huateng.com
	 * @param userId<用户主键>
	 * @return SysUser<用户信息>
	 */
	public Dto searchSysUserByUserId(Long userId);
	/**
	 * @Description:跟据用户号查询用户信息
	 * @author: wangzhao
	 * @date: 2014年4月23日下午3:36:42
	 * @mail: wangzhao@huateng.com
	 * @param userNo<用户号>
	 * @return<用户信息>
	 */
	public UserInfoVO searchSysUserByUserNo(String userNo);

}
