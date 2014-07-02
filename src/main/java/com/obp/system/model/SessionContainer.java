package com.obp.system.model;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.web.vo.UserInfoVO;


/**
 * 
 * @Title:SessionContainer.java
 * @Package:com.obp.system.model
 * @Description:Session瀹瑰櫒
 * @Copyright: Copyright(c)1995-2013
 * @Company:涓婃捣鍗庤吘杞欢绯荤粺鏈夐檺鍏徃
 *
 * @author: wangzhao
 * @date: 2014骞�鏈�2鏃ヤ笅鍗�:19:45
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class SessionContainer implements HttpSessionBindingListener {
	
	/**
	 * 鐧婚檰鐢ㄦ埛瀵硅薄
	 */
	private UserInfoVO userInfo;

	/**
	 * 鎶ヨ〃瀵硅薄闆�
	 */
	private Dto reportDto;
	
	public SessionContainer() {
		super();
		reportDto =  new BaseDto();
	}

	/**
	 * 娓呴櫎浼氳瘽瀹瑰櫒缂撳瓨瀵硅薄
	 */
	public void cleanUp() {
		reportDto.clear();
	}

	public void valueBound(HttpSessionBindingEvent event) {
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
	}

	/**
	 * 鑾峰彇鐢ㄦ埛浼氳瘽瀵硅薄
	 * @return UserInfo
	 */
	public UserInfoVO getUserInfo() {
		return userInfo;
	}

	/**
	 * 璁剧疆鐢ㄦ埛浼氳瘽瀵硅薄
	 * @param userInfo
	 */
	public void setUserInfo(UserInfoVO userInfo) {
		this.userInfo = userInfo;
		
	}

}
