package com.obp.system.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.obp.system.common.service.CodeService;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.model.service.impl.BaseServiceImpl;

@Service("codeService")
public class CodeServiceImpl extends BaseServiceImpl implements CodeService {

	@SuppressWarnings("unchecked")
	public List<BaseDto> searchAllCode() {
		return dao.queryForList("Code.searchAllCode");
	}

}
