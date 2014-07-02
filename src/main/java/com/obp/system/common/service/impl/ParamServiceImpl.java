package com.obp.system.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.obp.system.common.service.ParamService;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.model.service.impl.BaseServiceImpl;

@Service("paramService")
public class ParamServiceImpl extends BaseServiceImpl implements ParamService {

	@SuppressWarnings("unchecked")
	public List<BaseDto> searchAllParam() {
		return dao.queryForList("Param.searchAllParam");
	}

}
