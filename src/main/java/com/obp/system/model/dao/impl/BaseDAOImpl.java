package com.obp.system.model.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.obp.system.model.dao.BaseDAO;

@Repository
public class BaseDAOImpl extends SqlSessionDaoSupport implements BaseDAO {

	@SuppressWarnings("unused")
	@Inject
	private SqlSessionFactory sqlSessionFactory;

	public void insert(String statementName, Object parameterObject) {
		this.getSqlSession().insert(statementName, parameterObject);
	}

	public void insert(String statementName) {
		this.getSqlSession().insert(statementName);
		
	}

	public Object queryForObject(String statementName, Object parameterObject) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(statementName, parameterObject);
	}

	public Object queryForObject(String statementName) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(statementName);
	}

	@SuppressWarnings("rawtypes")
	public List queryForList(String statementName, Object parameterObject) {
		return this.getSqlSession().selectList(statementName, parameterObject);
	}

	@SuppressWarnings("rawtypes")
	public List queryForList(String statementName) {
		try{
			return this.getSqlSession().selectList(statementName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public int update(String statementName, Object parameterObject) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update(statementName,parameterObject);
	}

	public int update(String statementName) {
		return this.getSqlSession().update(statementName);
	}

	public int delete(String statementName, Object parameterObject) {
		return this.getSqlSession().delete(statementName, parameterObject);
	}

	public int delete(String statementName) {
		return this.getSqlSession().delete(statementName);
	}

	public void callPrc(String prcName) {
		// TODO Auto-generated method stub
		
	}

	public void callPrc(String prcName, String successFlag) {
		// TODO Auto-generated method stub
	}
	
}
