package com.obp.system.model.dao;

import java.util.List;

/**
 * 
 * @Title:BaseDAO.java
 * @Package:com.obp.system.model.dao
 * @Description:数据访问接口(基于iBatis实现,支持自定义的数据操作)
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 13, 201410:51:18 AM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public interface BaseDAO {
	
	/**
	 * @Description:插入一条记录
	 * @author: wangzhao
	 * @date: Apr 13, 201410:52:47 AM
	 * @mail: wangzhao@huateng.com
	 * @param statementName<SQL语句ID号>
	 * @param parameterObject<插入对象>
	 */
	public void insert(String statementName, Object parameterObject);
	
	/**
	 * @Description:插入一条记录
	 * @author: wangzhao
	 * @date: Apr 13, 201410:53:50 AM
	 * @mail: wangzhao@huateng.com
	 * @param statementName<SQL语句ID号>
	 */
	public void insert(String statementName);
	
	/**
	 * @Description:查询一条记录
	 * @author: wangzhao
	 * @date: Apr 13, 201410:54:10 AM
	 * @mail: wangzhao@huateng.com
	 * @param statementName<SQL语句ID号>
	 * @param parameterObject<条件对象>
	 * @return Object<对象>
	 */
	public Object queryForObject(String statementName, Object parameterObject);
	
	/**
	 * @Description:查询一条记录
	 * @author: wangzhao
	 * @date: Apr 13, 201410:56:08 AM
	 * @mail: wangzhao@huateng.com
	 * @param statementName<SQL语句ID号>
	 * @return
	 */
	public Object queryForObject(String statementName);
	
	/**
	 * @Description:查询记录集合
	 * @author: wangzhao
	 * @date: Apr 13, 201410:56:29 AM
	 * @mail: wangzhao@huateng.com
	 * @param statementName<SQL语句ID号>
	 * @param parameterObject<条件对象>
	 * @return List<对象>
	 */
	@SuppressWarnings("rawtypes")
	public List queryForList(String statementName, Object parameterObject);
	
	/**
	 * @Description:查询记录集合
	 * @author: wangzhao
	 * @date: Apr 13, 201410:57:19 AM
	 * @mail: wangzhao@huateng.com
	 * @param statementName<SQL语句ID号>
	 * @return List<对象>
	 */
	@SuppressWarnings("rawtypes")
	public List queryForList(String statementName);
	
	/**
	 * @Description:更新记录
	 * @author: wangzhao
	 * @date: Apr 13, 201410:59:03 AM
	 * @mail: wangzhao@huateng.com
	 * @param statementName<语句ID号>
	 * @param parameterObject<更新对象>
	 * @return
	 */
	public int update(String statementName, Object parameterObject);
	
	/**
	 * @Description:更新记录
	 * @author: wangzhao
	 * @date: Apr 13, 201410:59:32 AM
	 * @mail: wangzhao@huateng.com
	 * @param statementName<SQL语句ID号>
	 * @return
	 */
	public int update(String statementName);
	
	/**
	 * @Description:删除记录
	 * @author: wangzhao
	 * @date: Apr 13, 201410:59:51 AM
	 * @mail: wangzhao@huateng.com
	 * @param statementName<SQL语句ID号>
	 * @param parameterObject<更新对象>
	 * @return
	 */
	public int delete(String statementName, Object parameterObject);
	
	/**
	 * @Description:删除记录
	 * @author: wangzhao
	 * @date: Apr 13, 201411:00:18 AM
	 * @mail: wangzhao@huateng.com
	 * @param statementName<SQL语句ID号>
	 * @return
	 */
	public int delete(String statementName);
	
	/**
	 * @Description:调用存储过程,存储过程成功返回标志缺省：appCode=1
	 * @author: wangzhao
	 * @date: Apr 13, 201411:00:36 AM
	 * @mail: wangzhao@huateng.com
	 * @param prcName<存储过程ID号>
	 * @param prcDto
	 * @throws PrcException
	 */
	public void callPrc(String prcName);
	
	/**
	 * @Description:调用存储过程,存储过程成功返回标志自定义：appCode=successFlag(自定义的传入变量)
	 * @author: wangzhao
	 * @date: Apr 13, 201411:01:28 AM
	 * @mail: wangzhao@huateng.com
	 * @param prcName<存储过程ID号>
	 * @param prcDto
	 * @param successFlag
	 * @throws PrcException
	 */
	public void callPrc(String prcName, String successFlag);

}
