package com.cjj.filter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StreamUtils;

//@WebFilter(urlPatterns = "/*", filterName = "myFilter")
public class MyFilter implements Filter{

	protected final Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
//		String msg = StreamUtils.copyToString(httpRequest.getInputStream(), Charset.forName("utf-8"));
//		log.info("请求报文为：" + msg);
//		String[] temp = msg.split("=");
//		httpRequest.setAttribute("msg", temp[1]);
//	   	chain.doFilter(new MyHttpServletRequestWrapper(httpRequest , "/" + temp[0]), response);
	   	
	   	chain.doFilter(httpRequest, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/** 
	* 重新封装request包装类 
	* @author Administrator 
	* 
	*/  
	class MyHttpServletRequestWrapper extends HttpServletRequestWrapper{  
		private String url;  
		  
		public MyHttpServletRequestWrapper(HttpServletRequest request,String url) {  
			super(request);  
			this.url=url;  
		}  
		  
		@Override  
		public String getServletPath() {
			return url;  
		}  
		@Override  
		public StringBuffer getRequestURL() {
			return new StringBuffer(super.getRequestURL()).append(url);  
		}  
	}  
}
