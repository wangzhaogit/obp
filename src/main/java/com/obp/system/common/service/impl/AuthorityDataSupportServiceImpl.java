package com.obp.system.common.service.impl;

import org.springframework.stereotype.Service;

import com.obp.system.common.service.AuthorityDataSupportService;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.model.service.impl.BaseServiceImpl;

@Service("authorityDataSupportService")
public class AuthorityDataSupportServiceImpl extends BaseServiceImpl implements
		AuthorityDataSupportService {

	public Dto searchSysUserSubInfoByUserId(Long userId) {
		return (BaseDto)dao.queryForList("searchSysUserSubInfoByUserId");
	}
}
