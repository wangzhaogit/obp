package com.obp.system.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.obp.system.common.constants.PropertiesConstants;
import com.obp.system.common.factory.PropertiesFactory;
import com.obp.system.common.helper.PropertiesHelper;
import com.obp.system.common.id.IDGenerator;
import com.obp.system.common.service.MonitorService;
import com.obp.system.common.util.ApplicationContextUtils;
import com.obp.system.common.util.DateUtils;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;

/**
 * 
 * @Title:SpringAspect.java
 * @Package:com.obp.system.model
 * @Description:Spring切面编程处理类
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年5月6日下午6:41:04
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
@Component
@Aspect
public class SpringAspect {

	private static Logger logger = LogManager.getLogger();

	/**
	 * @Description:方法调用通知
	 * @author: wangzhao
	 * @date: 2014年5月6日下午6:38:24
	 * @mail: wangzhao@huateng.com
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object interceptCall(ProceedingJoinPoint pjp) throws Throwable {
		String clazzString = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String fullPath = clazzString + "." + methodName;
		int flag = clazzString.indexOf("$");
		if (flag < 0)
			logger.info("开始业务处理[" + methodName + "];全路径[" + fullPath + "]");
		long time = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
		if (flag < 0)
			logger.info("结束业务处理[" + methodName + "];耗时:" + time + "毫秒;全路径[" + fullPath + "]");
		return retVal;
	}

	/**
	 * @Description:方法异常通知
	 * 		<br>synchronized:标记为同步方法主要是为处理开启切面监控时候造成死锁的问题
	 * @author: wangzhao
	 * @date: 2014年5月6日下午6:37:01
	 * @mail: wangzhao@huateng.com
	 * @param jp<数据集>
	 * @param ex<异常>
	 */
	@SuppressWarnings("unchecked")
	public synchronized void interceptException(JoinPoint jp, Throwable ex) {
		PropertiesHelper g4PHelper = PropertiesFactory.getPropertiesHelper(PropertiesConstants.SYSTEM);
		String exceptionMonitor = g4PHelper.getValue("exceptionMonitor");
		if (exceptionMonitor.equals("0")) {
			return;
		}
		String clazzString = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		int flag = clazzString.indexOf("$");
		if (flag < 0) {
			MonitorService monitorService = (MonitorService)ApplicationContextUtils.getBean("monitorService");
			Dto dto = new BaseDto();
			dto.put("EXCEPTION_ID", IDGenerator.getId("SYS_EXCEPTION"));
			dto.put("CLAZZ", clazzString);
			dto.put("METHOD_NAME", methodName);
			dto.put("EXCEPTION", ex.getMessage());
			dto.put("ACTIVE_TIME", DateUtils.getCurrentDateTime());
			monitorService.saveSysException(dto);
		}
	}
}
