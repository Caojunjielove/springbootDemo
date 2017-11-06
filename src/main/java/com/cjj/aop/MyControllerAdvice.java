package com.cjj.aop;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjj.exception.MyException;
import com.cjj.message.base.BaseResMessage;
import com.cjj.message.base.ErrorMesaage;

@ControllerAdvice
public class MyControllerAdvice {
	protected final Log log = LogFactory.getLog(this.getClass());
	/**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	log.debug("------initBinder------");
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    	log.debug("------addAttributes------");
//        model.addAttribute("author", "Magical Sam");
    }

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Object errorHandler(MyException ex) {
    	log.debug("------errorHandler------");
    	if(ex.getBaseReqMessage() != null){
    		BaseResMessage baseMsg = new BaseResMessage(ex.getBaseReqMessage());
    		baseMsg.setRetCode(ex.getCode());
    		baseMsg.setRetMsg(ex.getMsg());
    		return baseMsg;
    	}else{
    		return new ErrorMesaage(ex.getCode(),ex.getMsg());
    	}
    }
}
