package com.obp.system.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.obp.system.common.service.MonitorService;
import com.obp.system.common.service.UserService;
import com.obp.system.common.util.DateUtils;
import com.obp.system.common.util.OBPUtils;
import com.obp.system.model.controller.BaseController;
import com.obp.system.model.listener.SessionListener;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.web.vo.UserInfoVO;

/**
 * 
 * @Title:LoginController.java
 * @Package:com.obp.system.web.controller
 * @Description:登录处理
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 18, 20142:17:11 PM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
@Controller("loginController")
@RequestMapping("/login")
public class LoginController extends BaseController{
	private static Logger logger = LogManager.getLogger();
	
	@Inject
	private UserService userService;
	@Inject
	private MonitorService monitorService;

	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("system/admin/jsp/login");
		String bannerPath = getParamValue("LOGIN_WINDOW_BANNER", request);
		bannerPath = request.getContextPath() + bannerPath;
		mav.addObject("bannerPath", bannerPath);
		mav.addObject("sysTitle", getParamValue("SYS_TITLE", request));
		return mav;
	}
	@SuppressWarnings({ "unchecked" })
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		String userNo = request.getParameter("userNo");
		String password = request.getParameter("password");
		password = OBPUtils.encryptBasedDes(password);
		logger.debug("帐户[" + userNo + "]正尝试登陆系统...");
		UserInfoVO userInfo = userService.searchSysUserByUserNo(userNo);
		Dto jsonDto = new BaseDto();
		if (OBPUtils.isEmpty(userInfo)) {
			jsonDto.put("success", new Boolean(false));
			jsonDto.put("msg", "帐号输入错误,请重新输入!");
			jsonDto.put("errorType", "1");
			logger.warn("帐户[" + userNo + "]登陆失败.(失败原因：不存在此帐户)");
			write(jsonDto.toJson(), response);
			mav.setViewName("system/admin/jsp/login");
			return mav;
		}
		if (!password.equals(userInfo.getPassword())) {
			jsonDto.put("success", new Boolean(false));
			jsonDto.put("msg", "密码输入错误,请重新输入!");
			jsonDto.put("errorType", "2");
			logger.warn(userInfo.getUserName() + "[" + userInfo.getUserNo() + "]" + "登录系统失败(失败原因：密码输入错误)");
			write(jsonDto.toJson(), response);
			mav.setViewName("system/admin/jsp/login");
			return mav;
		}
		String multiSession = getParamValue("MULTI_SESSION", request);
		if ("0".equals(multiSession)) {
			Integer sessions = monitorService.countHttpSessionByUserNo(userNo);
			if (sessions.intValue() > 0) {
				jsonDto.put("success", new Boolean(false));
				jsonDto.put("msg", "此用户已经登录,系统不允许建立多个会话连接!");
				jsonDto.put("errorType", "3");
				logger.warn(userInfo.getUserName() + "[" + userInfo.getUserNo() + "]"
						+ "登录系统失败(失败原因：此用户已经登录,系统参数配置为不允许一个用户建立多个连接)");
				write(jsonDto.toJson(), response);
				mav.setViewName("system/admin/jsp/login");
				return mav;
			}
		}
		userInfo.setSessionID(request.getSession().getId());
		userInfo.setSessionCreatedTime(DateUtils.getCurrentDateTime());
		userInfo.setLoginIP(request.getRemoteAddr());
		userInfo.setExplorer(OBPUtils.getClientExplorerType(request));
		/*if (!checkMultiUser(userInfo, request)) {
			jsonDto.put("success", new Boolean(false));
			jsonDto.put("msg", "不允许在同一客户端上同时以不同帐户登录系统,请先退出你已经登录的帐户后再尝试登录!");
			jsonDto.put("errorType", "1");
			log.warn("帐户[" + account + "]登陆失败.(失败原因：不允许在同一客户端上同时以不同帐户登录系统.请先退出你已经登录的帐户后再尝试登录)");
			write(jsonDto.toJson(), response);
			return mapping.findForward("");
		}*/
		super.getSessionContainer(request).setUserInfo(userInfo);
		logger.debug(userInfo.getUserName() + "[" + userInfo.getUserNo() + "]" + "成功登录系统!创建了一个有效Session连接,会话ID:["
				+ request.getSession().getId() + "]" + DateUtils.getCurrentDateTime());
		SessionListener.sessionCreated(request.getSession(), userInfo); // 保存有效Session
		/*if (g4PHelper.getValue("requestMonitor", "0").equals("1")) {
			saveLoginEvent(userInfo, request);
		}*/
		jsonDto.put("success", new Boolean(true));
		jsonDto.put("userId", userInfo.getUserId());
		write(jsonDto.toJson(), response);
		mav.setViewName("/index/init");
		return mav;
	}
}
