package com.obp.system.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.obp.system.common.constants.SystemConstants;
import com.obp.system.model.SessionContainer;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;

/**
 * 
 * @Title:WebUtils.java
 * @Package:com.obp.system.common.util
 * @Description:与WEB层相关的工具类
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年4月21日上午10:51:35
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class WebUtils {
	
	/**
	 * @Description:获取全局参数
	 * @author: wangzhao
	 * @date: 2014年4月22日上午9:02:13
	 * @mail: wangzhao@huateng.com
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<BaseDto> getParamList(HttpServletRequest request) {
		ServletContext context = request.getSession().getServletContext();
		if (OBPUtils.isEmpty(context)) {
			return new ArrayList<BaseDto>();
		}
		return (List<BaseDto>) context.getAttribute(SystemConstants.PARAM_LIST);
	}
	
	/**
	 * @Description:获取全局参数值
	 * @author: wangzhao
	 * @date: 2014年4月22日上午9:13:40
	 * @mail: wangzhao@huateng.com
	 * @param pParamKey
	 * @param request
	 * @return
	 */
	public static String getParamValue(String pParamKey,
			HttpServletRequest request) {
		String paramValue = "";
		
		List<BaseDto> paramList = getParamList(request);
		for (Dto dto : paramList) {
			if (pParamKey.equals(dto.getAsString("PARAM_KEY"))) {
				paramValue = dto.getAsString("PARAM_VALUE");
			}
		}
		return paramValue;
	}
	
	/**
	 * @Description:获取一个Session属性对象
	 * @author: wangzhao
	 * @date: 2014年4月22日上午9:28:16
	 * @mail: wangzhao@huateng.com
	 * @param request
	 * @param sessionKey
	 * @return
	 */
	public static Object getSessionAttribute(HttpServletRequest request,
			String sessionKey) {
		Object objSessionAttribute = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			objSessionAttribute = session.getAttribute(sessionKey);
		}
		return objSessionAttribute;
	}
	
	/**
	 * @Description:获取容器对象
	 * @author: wangzhao
	 * @date: 2014年4月25日下午1:00:22
	 * @mail: wangzhao@huateng.com
	 * @param request
	 * @return
	 */
	public static SessionContainer getSessionContainer(HttpServletRequest request) {
		/*SessionContainer sessionContainer = (SessionContainer) request
				.getAttribute("SessionContainer");*/
		SessionContainer sessionContainer = (SessionContainer) request
				.getSession().getAttribute("SessionContainer");
		if (sessionContainer == null) {
			sessionContainer = new SessionContainer();
			request.setAttribute("SessionContainer", sessionContainer);
		}
		return sessionContainer;
	}
	
	/**
	 * @Description:根据代码类别获取代码表列表
	 * @author: wangzhao
	 * @date: 2014年5月10日上午10:16:14
	 * @mail: wangzhao@huateng.com
	 * @param pField
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Dto> getCodeListByField(String pField,
			HttpServletRequest request) {
		List<Dto> codeList = (List<Dto>) request.getSession().getServletContext()
				.getAttribute(SystemConstants.CODE_LIST);
		List<Dto> lst = new ArrayList<Dto>();
		for (Dto codeDto : codeList) {
			if (codeDto.getAsString("field").equalsIgnoreCase(pField)) {
				lst.add(codeDto);
			}
		}
		return lst;
	}

}
