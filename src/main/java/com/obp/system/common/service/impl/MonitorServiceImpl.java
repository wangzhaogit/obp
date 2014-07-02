package com.obp.system.common.service.impl;

import org.springframework.stereotype.Service;

import com.obp.system.common.service.MonitorService;
import com.obp.system.model.exception.CommonException;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.service.impl.BaseServiceImpl;
import com.obp.system.web.vo.UserInfoVO;

@Service("monitorService")
public class MonitorServiceImpl extends BaseServiceImpl implements MonitorService {

	public void saveEvent(Dto dto) throws CommonException{
		dao.insert("Monitor.saveEvent", dto);

	}

	public Dto deleteEvent(Dto inDto) throws CommonException{
		return null;
	}

	public Dto deleteMonitorData(Dto inDto) throws CommonException{
		return null;
	}

	public void deleteHttpSession(Dto dto) {
		dao.delete("Monitor.deleteHttpSession");
		
	}

	public UserInfoVO queryHttpSessionsBySessionId(String sessionId)
			throws CommonException {
		return (UserInfoVO)dao.queryForObject("Monitor.queryHttpSessionsBySessionId", sessionId);
	}

	public void saveHttpSession(UserInfoVO userInfo)throws CommonException {
		dao.insert("Monitor.saveHttpSession",userInfo);
		
	}

	public UserInfoVO searchHttpSessionsBySessionId(String sessionId)
			throws CommonException {
		return null;
	}

	public void saveSysException(Dto dto) {
		dao.insert("Monitor.saveSysException",dto);
	}

	public Integer countHttpSessionByUserNo(String userNo)
			throws CommonException {
		return (Integer)dao.queryForObject("Monitor.countHttpSessionByUserNo", userNo);
	}

}
