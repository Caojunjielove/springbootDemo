package com.cjj.aop.handler;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.alibaba.fastjson.JSON;
import com.cjj.exception.MyException;
import com.cjj.message.base.BaseMessage;


public class MyMessageConverter extends AbstractHttpMessageConverter<BaseMessage> {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	private static String CHARSET = "UTF-8";
	private final static String RPID = "RPID";
	public MyMessageConverter(){
        super(new MediaType("application", "json", Charset.forName(CHARSET)),new MediaType("text", "plain", Charset.forName(CHARSET)));
    }
	@Override
	protected BaseMessage readInternal(Class<? extends BaseMessage> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		String msg = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName(CHARSET));
		log.debug("接收请求报文：" + msg);
		BaseMessage baseMsg = null;
		//以{开头的是未加密报文
		if(msg.startsWith("{")){
			try{
				baseMsg = JSON.parseObject(msg, clazz);
				baseMsg.setSignFlag(false);
			}catch(Exception e){
				throw new MyException("0001", "报文不合法");
			}
		}else{
			try{
				//解密处理开始
				//解密处理
				//解密处理结束
//				baseMsg = JSON.parseObject(解密后的报文, clazz);
			}catch(Exception e){
				throw new MyException("9901", "报文解密失败");
			}
			baseMsg.setSignFlag(true);
		}
		baseMsg.setRpid(MDC.get(RPID));
		baseMsg.setStartTime(System.currentTimeMillis());
		return baseMsg;
	}

	//只支持BaseMesaage类
	@Override
	protected boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return BaseMessage.class.isAssignableFrom(clazz);
	}

	@Override
	protected void writeInternal(BaseMessage baseMessage, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String jsonMsg = JSON.toJSONString(baseMessage);
		log.debug("返回响应报文：" + jsonMsg);
		if(baseMessage.isSignFlag()){
			//对jsonMsg加密处理
		}
		outputMessage.getBody().write(jsonMsg.getBytes(CHARSET));
	}	
}
