package com.obp.system.model.listener;

import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obp.system.common.service.MonitorService;
import com.obp.system.common.util.ApplicationContextUtils;
import com.obp.system.common.util.OBPUtils;
import com.obp.system.model.SessionContainer;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.web.vo.UserInfoVO;

/**
 * 
 * @Title:SessionListener.java
 * @Package:com.obp.system.model.listener
 * @Description:Session监听
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年4月22日下午3:23:21
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class SessionListener implements HttpSessionListener {
	private static Logger logger = LogManager.getLogger();

	// 集合对象，保存session对象的引用
	@SuppressWarnings("rawtypes")
	static Hashtable sessionHt = new Hashtable();
	
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public void sessionDestroyed(HttpSessionEvent event) {
		MonitorService monitorService = (MonitorService)ApplicationContextUtils.getBean("monitorService");
		HttpSession session = event.getSession();
		if (session == null) return;
		SessionContainer sessionContainer =  (SessionContainer)session.getAttribute("SessionContainer");
		if(sessionContainer == null)
			return;
		sessionContainer.setUserInfo(null); //配合RequestFilter进行拦截
		sessionContainer.cleanUp();
		Dto dto = new BaseDto();
		dto.put("sessionId", session.getId());
		monitorService.deleteHttpSession(dto);
		sessionHt.remove(session.getId());
		logger.info("销毁了一个Session连接:" + session.getId());
	}
	
	/**
	 * 增加一个有效Session
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	static public void sessionCreated(HttpSession session, UserInfoVO userInfo) {
		MonitorService monitorService = (MonitorService)ApplicationContextUtils.getBean("monitorService");
		sessionHt.put(session.getId(), session);
		UserInfoVO usInfo = monitorService.searchHttpSessionsBySessionId(session.getId());
		if(OBPUtils.isEmpty(usInfo)){
			monitorService.saveHttpSession(userInfo);
		}
	}

	/**
	 * 返回全部session对象集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	static public Iterator getSessions() {
		return sessionHt.values().iterator();
	}

	/**
	 * 依据session id返回指定的session对象
	 * @param sessionId
	 * @return
	 */
	static public HttpSession getSessionByID(String sessionId) {
		return (HttpSession) sessionHt.get(sessionId);
	}
	
	

}
