package com.obp.system.model.listener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @Title:CharacterEncodingFilter.java
 * @Package:com.obp.system.model.listener
 * @Description:字符编码过滤器
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年4月21日下午7:30:26
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class CharacterEncodingFilter implements Filter {
	
	protected String encoding;
	protected FilterConfig filterConfig;
	protected boolean enabled;

	public CharacterEncodingFilter() {
		encoding = null;
		filterConfig = null;
		enabled = true;
	}
	
	public void destroy() {
		encoding = null;
		filterConfig = null;
	}
	
	/**
	 * @Description:过滤器主体方法
	 * @author: wangzhao
	 * @date: 2014年4月21日下午7:34:12
	 * @mail: wangzhao@huateng.com
	 * @param request
	 * @param response
	 * @param fChain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fChain) throws IOException, ServletException {
		if (enabled || request.getCharacterEncoding() == null) {
			if (encoding != null)
				request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
		}
		fChain.doFilter(request, response);

	}

	/**
	 * @Description:过滤器初始化方法
	 * @author: wangzhao
	 * @date: 2014年4月21日下午7:34:03
	 * @mail: wangzhao@huateng.com
	 * @param fConfig
	 * @throws ServletException
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("enabled");
		if (value == null)
			enabled = true;
		else if (value.equalsIgnoreCase("true"))
			enabled = true;
		else
			enabled = false;

	}

}
