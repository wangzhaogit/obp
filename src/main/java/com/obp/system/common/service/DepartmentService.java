package com.obp.system.common.service;

import com.obp.system.model.metatype.Dto;
public interface DepartmentService{
	/**
	 * @Description:跟据用户主键查询用户信息
	 * @author: wangzhao
	 * @date: Apr 13, 20149:18:53 AM
	 * @mail: wangzhao@huateng.com
	 * @param userId<用户主键>
	 * @return SysUser<用户信息>
	 */
	public Dto searchSysDepartmentByDeptId(String deptId);

}
