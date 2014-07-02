package com.obp.system.web.vo;

import com.obp.system.model.metatype.BaseVO;

/**
 * 
 * @Title:UserInfoVO.java
 * @Package:com.obp.system.web.vo
 * @Description:用户登录信息
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 18, 20141:22:32 PM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class UserInfoVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号：Long <Primary Key>
	 */
	private Long userId;

	/**
	 * 用户名：varchar2(20)
	 */
	private String userName;

	/**
	 * 登陆帐户：varchar2(20)
	 */
	private String userNo;

	/**
	 * 密码：varchar2(20)
	 */
	private String password;

	/**
	 * 性别(0:未知;1:男;2:女)：varchar2(2)
	 */
	private String sex;

	/**
	 * 部门编号：Integer
	 */
	private String deptId;

	/**
	 * 锁定标志(0:锁定;1:激活)：varchar2(2)
	 */
	private String lock;

	/**
	 * 自定部门编号
	 */
	private String customId;
	
	/**
	 * 自定义主题
	 */
	private String theme;
	
	/**
	 * 会话ID
	 */
	private String sessionId;
	
	/**
	 * 会话创建时间
	 */
	private String sessionCreatedTime;
	
	/**
	 * 登录IP
	 */
	private String loginIP;
	
	/**
	 * 浏览器
	 */
	private String explorer;
	
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 自定义布局方案
	 */
	private String layout;

	public UserInfoVO(){
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getCustomId() {
		return customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionID(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionCreatedTime() {
		return sessionCreatedTime;
	}

	public void setSessionCreatedTime(String sessionCreatedTime) {
		this.sessionCreatedTime = sessionCreatedTime;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getExplorer() {
		return explorer;
	}

	public void setExplorer(String explorer) {
		this.explorer = explorer;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	
}
