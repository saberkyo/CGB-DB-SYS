package com.cy.pj.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.util.IPUtils;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Aspect 描述的类为切面类，此类中实现：
 * 
 *         1.切入点的定义
 * 
 *         2.通知（advice）的定义（扩展功能）
 * @author Administrator
 *
 */
@Aspect
@Slf4j
@Component
public class SysLogAspect {

	@Autowired
	private SysLogService sysLogService;

	/**
	 * @Pointcut 注解用于描述或定义一个切入点，切入点的定义需要遵循spring中指定的表达式规范
	 * 
	 *           bean(bean名称/表达式）
	 */
//	@Pointcut("bean(sysMenuServiceImpl)")
	@Pointcut("@annotation(com.cy.pj.common.annotation.RequiredLog)")
	public void logPointCut() {

	}

	/**
	 * @around： 环绕通知方法，在此方法中可以添加业务
	 * 
	 * @param pj 此链接点只用于@Around描述的方法
	 * @return
	 * @throws Throwable
	 */
	@Around("logPointCut()")
	public Object aroundAdivce(ProceedingJoinPoint pj/* 此链接点只用于@Around描述的方法 */) throws Throwable {
		long start = System.currentTimeMillis();
		log.info("start:" + start);
		Object result = pj.proceed();
		long end = System.currentTimeMillis();
		log.info("end:" + end);
		saveLog((end - start), pj);
		return result;
	}

	private void saveLog(long time, ProceedingJoinPoint jp) throws Throwable {
		// 1.获取用户行为日志（ip,username,operation,method,params,time,createdTime)
		// 获取类字节码对象
		Class<? extends Object> targetCls = jp.getTarget().getClass();
		String targetClsName = targetCls.getName();
		// 获取方法签名
		MethodSignature ms = (MethodSignature) jp.getSignature();
		// 获取目标方法上的操作名称(目标注解上的value值）
		Method targetMethod = ms.getMethod();
		RequiredLog targetAnnotation = targetMethod.getAnnotation(RequiredLog.class);
		String operation = targetAnnotation.value();
//		Method targetMethod = targetCls.getDeclaredMethod(s.getName(), s.getParameterTypes());
		// 获取目标方法名（目标类型+方法名)
		String targetObjectMethod = targetClsName + "." + ms.getName();
		// 获取请求参数
		String targetMethodParams = Arrays.toString(jp.getArgs());

		// 2.封装用户行为日志（SysLog）
		SysLog sysLog = new SysLog();
		sysLog.setIp(IPUtils.getIpAddr());
		sysLog.setUsername("admin");
		sysLog.setOperation(operation);
		sysLog.setTime(time);
		sysLog.setCreatedTime(new Date());
		sysLog.setParams(targetMethodParams);
		sysLog.setMethod(targetObjectMethod);
		// 3.调用业务层对象方法，将日志写入到数据库
		sysLogService.saveObject(sysLog);

	}
}
