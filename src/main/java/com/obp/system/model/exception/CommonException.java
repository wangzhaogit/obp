package com.obp.system.model.exception;

/**
 * 
 * @Title:CommonException.java
 * @Package:com.obp.system.model.exception
 * @Description:通用异常处理
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: Apr 14, 20149:19:17 AM
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class CommonException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	/*
	 * 异常代码
	 */
	//private String errCode;
	/*
	 * 异常信息
	 */
	//private String message;
	
	public CommonException(String message) {		
		super(new Exception(message));
		//this.message = message;
	}
	
	public CommonException(Throwable t){
		super(t);
		//message=t.getMessage();
	}
	
	public CommonException(String errCode,Throwable t){
		/*super(t);
		this.errCode=errCode;
		if(CommonUtil.isAsciiAlphaNum(errCode)){
			message=ErrorCodeDefine.getMessage(errCode,null);
		} else{//如果是中文, 则不用去errmsg.xml查找
			message = errCode;
		}*/
	}

}
