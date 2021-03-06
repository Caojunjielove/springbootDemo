package com.cjj.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.logging.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cjj.message.base.BaseResMessage;
import com.cjj.util.MpspLog;
import com.cjj.util.RandomUtils;

public class MyInterceptor extends HandlerInterceptorAdapter{

	protected final Log log = LogFactory.getLog(this.getClass());
	
	private final static String RPID = "RPID";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("--------------业务处理开始-----------------");
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		MDC.put(RPID, RandomUtils.getUuidStr());
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long starTime = (long) request.getAttribute("startTime");
		request.removeAttribute("startTime");
		long endTime = System.currentTimeMillis();
		Object baseMessage = request.getAttribute("resData");
		MDC.remove(RPID);
		if(baseMessage instanceof BaseResMessage){
			MpspLog.logMPSP((BaseResMessage) baseMessage, System.currentTimeMillis() - starTime);
		}
		log.info("--------------业务处理结束-----------------处理时间：" + (endTime-starTime) + "ms");
		super.afterCompletion(request, response, handler, ex);
	}

}
