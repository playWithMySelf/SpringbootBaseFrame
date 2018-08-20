package com.jw.aspect;

import com.jw.base.BaseAction;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 切点类
 *
 * @author jinwei
 * @since 2017-12-04 Pm 20:35
 * @version 1.0
 */
@Aspect
@Component
public class SystemLogAop {
	// 本地异常日志记录对象
	private static final Logger logger = Logger
			.getLogger(SystemLogAop.class);


	public SystemLogAop(){
		System.out.println("*********************SystemLogAop*******************");
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 拦截com.*.controller包下所有以Controller结尾的java文件
	 * @param joinPoint
	 *            切点
	 */
	@Before("execution(* com..*Controller.*(..))")
	public void doBefore(JoinPoint joinPoint) {
		System.out.println("*********************SystemLogAop begin*******************");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		try {
			// *========控制台输出=========*//
			System.out.println("=====前置通知开始=====");
			System.out.println("请求方法:"
					+ (joinPoint.getTarget().getClass().getName() + "."
							+ joinPoint.getSignature().getName() + "()"));

			// *========获取请求的参数=========*//
			BaseAction baseAction = new BaseAction();
			Map<String, String> logParam = new HashMap();
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for ( int i = 0; i < joinPoint.getArgs().length; i++) {

	                String type = (joinPoint.getArgs()[i]).getClass().toString();
	                if(type.indexOf("RequestFacade")>=0){
	                	HttpServletRequest hq = (HttpServletRequest) joinPoint.getArgs()[i];
	                	//获取session
	                	HttpSession ss = hq.getSession();

	                	logParam = baseAction.getParameterMap(hq);
                        String log_ip = getIp(hq);
                        System.out.println("Action请求的IP："+log_ip);
	                	System.out.println("Action请求的参数："+logParam);
	                }
	            }
			}

			System.out.println("=====前置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息:{}",e);
		}
	}

    @Before("execution(* com..*Controller.*(..))")
	public void doAfter(){
		System.out.println("*********************SystemLogAop end*****************");

	}

	/**
	 * @Title: getIp
	 * @描述:获取客户端Ip
	 * @作者: jin_wei@founder.com
	 * @参数: 传入参数定义
	 * @返回值: void    返回类型
	 * @throws
	 */
	public String getIp(HttpServletRequest request){
		String ip = null;
		ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        	ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        	ip = request.getHeader("WL-Proxy-Client-IP");
        }
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getRemoteAddr();
        }
		return ip;
	}

}