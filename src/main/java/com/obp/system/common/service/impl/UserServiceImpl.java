package com.obp.system.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.obp.system.common.service.UserService;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.model.service.impl.BaseServiceImpl;
import com.obp.system.web.vo.UserInfoVO;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@SuppressWarnings("unchecked")
	public List<BaseDto> searchAllSysUser() {
		return dao.queryForList("User.searchAllSysUser");
	}

	public Dto searchSysUserByUserId(Long userId) {
		return (BaseDto)dao.queryForObject("User.searchSysUserByUserId",userId);
	}

	public UserInfoVO searchSysUserByUserNo(String userNo) {
		return (UserInfoVO)dao.queryForObject("User.searchSysUserByUserNo",userNo);
	}

}
