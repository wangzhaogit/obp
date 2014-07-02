package com.obp.test.springAnnotation;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.obp.system.common.service.UserService;
import com.obp.system.model.metatype.impl.BaseDto;

public class UserServiceTest {
	@Test
	public void userTest(){
		ApplicationContext ac=new ClassPathXmlApplicationContext( new String[]{"classpath*:applicationContext.xml"});
		UserService userService=(UserService)ac.getBean("userService");
		List<BaseDto> list = userService.searchAllSysUser();
		Gson gson = new Gson();
		System.out.println(gson.toJsonTree(list));
	}
}
