package com.obp.system.common.service.impl;

import org.springframework.stereotype.Service;

import com.obp.system.common.service.DepartmentService;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.service.impl.BaseServiceImpl;

@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl implements DepartmentService {

	public Dto searchSysDepartmentByDeptId(String deptId) {
		return (Dto) dao.queryForObject("Department.searchSysDepartmentByDeptId",deptId);
	}

}
