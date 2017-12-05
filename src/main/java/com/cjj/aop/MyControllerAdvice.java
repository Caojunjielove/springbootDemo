package com.cjj.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjj.exception.MyException;
import com.cjj.message.base.BaseReqMessage;
import com.cjj.message.base.BaseResMessage;

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
     * 全局自定义异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Object umfExceptionHandler(MyException ex,HttpServletRequest request ,HttpServletResponse response) {
    	BaseResMessage baseMsg = BaseResMessage.error(request,ex.getCode(),ex.getMsg());
       	Object reqData = request.getAttribute("reqData");
    	if(reqData instanceof BaseReqMessage){
    		baseMsg.setBaseReqMessage((BaseReqMessage) reqData);
    	}
    	return baseMsg;
    }
    /**
     * 全局参数校验异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentValidFailHandler(MethodArgumentNotValidException exception,HttpServletRequest request ,HttpServletResponse response) {
    	BaseResMessage baseMsg = BaseResMessage.error(request);
    	Object reqData = request.getAttribute("reqData");
    	if(reqData instanceof BaseReqMessage){
    		baseMsg.setBaseReqMessage((BaseReqMessage) reqData);
    	}
		baseMsg.setRetMsg(exception.getBindingResult().getFieldError().getDefaultMessage());
		return baseMsg;
    }

    /**
     * 全局参数校验异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(Exception exception,HttpServletRequest request ,HttpServletResponse response) {
    	BaseResMessage baseMsg = BaseResMessage.error(request);
    	Object reqData = request.getAttribute("reqData");
    	if(reqData instanceof BaseReqMessage){
    		baseMsg.setBaseReqMessage((BaseReqMessage) reqData);
    	}
    	return baseMsg;
    }
}
