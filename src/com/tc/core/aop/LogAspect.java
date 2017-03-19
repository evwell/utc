package com.tc.core.aop;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tc.core.constant.IdentificationDefined;
import com.tc.core.frame.service.LogService;
import com.tc.core.frame.service.Service;
import com.tc.core.util.JacksonUtil;
import com.tc.vo.impl.TcLog;

/**
 * 日志记录，添加、删除、修改方法AOP
 * 
 * @author HotStrong
 *
 */
@Aspect
public class LogAspect implements Ordered{

	
	private static Logger logger = Logger.getLogger(LogAspect.class);
	@Autowired
	private LogService logService;// 日志记录Service

	@Autowired
	private Service service;// Service

	public void saveAfterReturning(JoinPoint joinPoint, Object rtv) throws Throwable {
		logger.debug("saveAfterReturning...");
		writeLog(joinPoint, IdentificationDefined.LOG_CMD_CREATE);
	}
	
	public void updateAfterReturning(JoinPoint joinPoint, Object rtv) throws Throwable {
		logger.debug("updateAfterReturning...");
		writeLog(joinPoint, IdentificationDefined.LOG_CMD_UPDATE);
	}

	/**
	 * 管理员删除操作(环绕通知)，使用环绕通知的目的是 在被删除前可以先查询出信息用于日志记录
	 * 
	 */
	public Object deleteAround(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		logger.debug("deleteAround...");
		obj = pjp.proceed();
		writeLog(pjp, IdentificationDefined.LOG_CMD_DELETE);
		return obj;
	}
	
	public void findAfterReturning(JoinPoint joinPoint, Object rtv) throws Throwable {
		if(this.logService.loginUserId()==null||this.logService.loginUserId()==0){
			//没登陆的查询操作不作日志
			return;
		}
		logger.debug("findAfterReturning...");
		writeLog(joinPoint, IdentificationDefined.LOG_CMD_RETRIVE);
	}
	
	/**
	 * 写日志
	 * @param joinPoint
	 * @param cmd
	 * @throws Exception
	 */
	private void writeLog(JoinPoint joinPoint, IdentificationDefined cmd) throws Exception{
		String methodName = joinPoint.getSignature().getName();
		String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
		if(opContent.length()>IdentificationDefined.LOG_CONTENT_LENGTH.getIntValue()){opContent = opContent.substring(0, IdentificationDefined.LOG_CONTENT_LENGTH.getIntValue());}
		TcLog tcLog = new TcLog();
		tcLog.setOperator(logService.loginUserId());
		tcLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
		tcLog.setIp(getIpAddr());
		tcLog.setContent(opContent);// 操作内容
		tcLog.setCmd(cmd.getStrValue());// 操作
		logService.log(tcLog);// 添加日志
	}
	
	/**
	 * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
	 */
	private String adminOptionContent(Object[] args, String mName) throws Exception {
		if (args == null) {
			return null;
		}
		StringBuffer rs = new StringBuffer();
		rs.append(mName);
		String className = null;
		int index = 1;
		// 遍历参数对象
		for (Object info : args) {
			// 获取对象类型
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			rs.append("[参数" + index + "，类型：" + className + "，值：").append(JacksonUtil.toJSon(info));
			rs.append("]");
			index++;
		}
		return rs.toString();
	}

	/**
	 * 获取请求IP地址
	 * @return
	 */
	private String getIpAddr() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = request.getHeader("x-forwarded-for");
		logger.debug("request.getHeader(\"x-forwarded-for\") = " + ip);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		logger.debug("request.getHeader(\"Proxy-Client-IP\") = " + ip);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		logger.debug("request.getHeader(\"WL-Proxy-Client-IP\") = " + ip);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		logger.debug("request.getRemoteAddr() = " + ip);
		return ip;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
