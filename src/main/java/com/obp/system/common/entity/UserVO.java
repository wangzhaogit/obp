package com.obp.system.common.entity;

import com.obp.system.model.metatype.BaseVO;

/**
 * 
 * @Title:SysUser.java
 * @Package:com.obp.system.common.entity
 * @Description:用户信息
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 13, 20148:18:05 AM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class UserVO extends BaseVO{
	private static final long serialVersionUID = 1L;
	/*有户主键*/
	private Long userId;
	/*用户编号*/
	private String userNo;
	/*用户名*/
	private String userName;
	/*密码*/
	private String password;
	/*性别*/
	private String sex;
	/*部门主键*/
	private Long deptId;
	/*锁定标志(1:锁定;0:激活)*/
	private String locked;
	/*人员类型(1:经办员;2:管理员;3:系统内置人员)*/
	private String userType;
	/*启用状态(1:启用;0:停用)*/
	private String enabled;
	/*备注*/
	private String remark;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
