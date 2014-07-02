package com.obp.system.common.service.impl;

import org.springframework.stereotype.Service;

import com.obp.system.common.service.SequenceService;
import com.obp.system.model.exception.CommonException;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.service.impl.BaseServiceImpl;

@Service("sequenceService")
public class SequenceServiceImpl extends BaseServiceImpl implements SequenceService {

	public Dto searchSysSequenceByTableName(String tableName)
			throws CommonException {
		return (Dto) dao.queryForObject("searchSysSequenceByTableName", tableName);
	}

	public void saveSysSequence(Dto dto) throws CommonException {
		dao.insert("saveSysSequence", dto);

	}

	public void updateSysSequence(Dto dto) throws CommonException {
		dao.update("updateSysSequence", dto);

	}

}
