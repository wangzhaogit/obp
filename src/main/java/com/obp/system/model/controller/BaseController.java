package com.obp.system.model.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.obp.system.common.util.WebUtils;
import com.obp.system.model.SessionContainer;

/**
 * 
 * @Title:BaseController.java
 * @Package:com.obp.system.model
 * @Description:基础控制层
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 18, 20143:53:09 PM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class BaseController {
	
	/**
	 * @Description:获取全局参数值
	 * @author: wangzhao
	 * @date: Apr 18, 20143:58:04 PM
	 * @mail: wangzhao@huateng.com
	 * @param pParamKey<参数名>
	 * @param request<请求对象>
	 * @return<参数值>
	 */
	protected String getParamValue(String pParamKey, HttpServletRequest request) {
		return WebUtils.getParamValue(pParamKey, request);
	}
	
	/**
	 * @Description:
	 * @author: wangzhao
	 * @date: 2014年4月23日下午3:46:08
	 * @mail: wangzhao@huateng.com
	 * @param str
	 * @param response
	 * @throws IOException
	 */
	protected void write(String str, HttpServletResponse response){
		try{
			response.getWriter().write(str);
			response.getWriter().flush();
			response.getWriter().close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description:获取一个Session容器
	 * @author: wangzhao
	 * @date: 2014年4月23日下午4:01:43
	 * @mail: wangzhao@huateng.com
	 * @param request
	 * @return
	 */
	protected SessionContainer getSessionContainer(HttpServletRequest request) {
		SessionContainer sessionContainer = (SessionContainer) this.getSessionAttribute(request, "SessionContainer");
		if (sessionContainer == null) {
			sessionContainer = new SessionContainer();
			HttpSession session = request.getSession(true);
			session.setAttribute("SessionContainer", sessionContainer);
		}
		return sessionContainer;
	}
	
	/**
	 * @Description:获取一个Session属性对象
	 * @author: wangzhao
	 * @date: 2014年4月23日下午4:02:47
	 * @mail: wangzhao@huateng.com
	 * @param request
	 * @param sessionKey
	 * @return
	 */
	protected Object getSessionAttribute(HttpServletRequest request, String sessionKey) {
		Object objSessionAttribute = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			objSessionAttribute = session.getAttribute(sessionKey);
		}
		return objSessionAttribute;
	}

}
