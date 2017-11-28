package com.cjj.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.thymeleaf.util.StringUtils;

import com.cjj.message.base.BaseResMessage;


/**
 * @author 打印简要日志
 *
 */
public class MpspLog {

    private static Log log;
    private static char separator = ',';
	
    private static Log logger(){
        if(log == null)
        	log = LogFactory.getLog("MPSP");
        return log;
    }
    
    
    
    /**
     * @param 组装日志，日志格式为：RPID,平台日期,平台时间,功能码,返回码,返回信息,处理时间
     * @param period
     */
    public static void logMPSP(BaseResMessage msg, long period){
        if(msg == null){
        	return;
        }
        StringBuilder sb = new StringBuilder();
        String rpid =  StringUtils.trim(msg.getRpid());    			   //RPID
        sb.append(rpid).append(separator);
        String date = StringUtils.trim(msg.getReqDate());              //平台日期
        sb.append(date).append(separator);
        String time = StringUtils.trim(msg.getReqTime());              //平台时间
        sb.append(time).append(separator);
        String funcode = StringUtils.trim(msg.getFunCode());           //功能码
        sb.append(funcode).append(separator);
        String retcode = StringUtils.trim(msg.getRetCode());           //返回码
        sb.append(retcode).append(separator);
        
        if("0000".equals(retcode)){
        	sb.append((new StringBuilder()).append("交易成功").toString()).append(separator);
        } else {
        	String retMsg = msg.getRetMsg();
        	if(retMsg == null || "".equals(retMsg)){
        		sb.append("交易失败").append(separator);
        	} else {
        		retMsg = replaceSign(retMsg.toString());
        		sb.append(retMsg).append(separator);
        	}
        }      
        sb.append(period);  //处理时间
        logMPSP(sb.toString());
    }

    private static synchronized void logMPSP(String info){
        logger().info(info);
    }

    /**
     * @param 去除分隔符
     * @return
     */
    private static String replaceSign(String str) {
	
		if(str.contains(",")){//将逗号转为竖线，以区分分隔符
			str = str.replaceAll(",", "|");
		}
		if(str.contains("，")){//将逗号转为竖线，以区分分隔符
			str = str.replaceAll("，", "|");
		}
		return str.toString();
    }

}
