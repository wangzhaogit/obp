package com.obp.system.model.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.obp.system.model.dao.BaseDAO;
import com.obp.system.model.service.BaseService;

@Service("baseService")
public class BaseServiceImpl implements BaseService{
	
	@Inject
	protected BaseDAO dao;
	
	public void setBaseDao(BaseDAO dao) {
		this.dao = dao;
	}

}
