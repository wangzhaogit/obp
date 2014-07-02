package com.obp.system.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.obp.system.model.controller.BaseController;

@Controller("indexController")
@RequestMapping("index")
public class IndexController extends BaseController{
	
	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("system/admin/jsp/frame");
		mav.addObject("sysTitle", getParamValue("SYS_TITLE", request));
		return mav;
	}

}
