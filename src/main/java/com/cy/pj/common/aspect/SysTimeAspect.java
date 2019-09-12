package com.cy.pj.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class SysTimeAspect {
	/*
	 * bean(掌握),within（了解） 粗粒度的切入点表达式（只能精确到类）
	 */
	/*
	 * execution表达式(了解），@annotation表达式（掌握） 细粒度切入点表达式(可以精确到具体方法)
	 */
	// @Pointcut("bean(*ServiceImpl)")
	// @Pointcut("within(com.cy.pj.sys.service.impl.*)")
	// @Pointcut("execution(int
	// com.cy.pj.sys.service.impl.SysRoleServiceImple.saveObject(..))")
	// @Pointcut("bean(sysRoleServiceImpl)")
	@Pointcut("@annotation(com.cy.pj.common.annotation.RequiredTime)")
	public void doTimePointCut() {
	}

	@Before("doTimePointCut()")
	public void beforeAdvice() {
		log.info("time:beforeAdvice");
	}

	@After("doTimePointCut()")
	public void afterAdvice() {
		log.info("time:afterAdvice");
	}

	@AfterReturning("doTimePointCut()")
	public void returnAdvice() {
		log.info("time:ruturnAdivce");
	}

	@AfterThrowing("doTimePointCut()")
	public void throwingAdvice() {
		log.info("time:throwingAdvice");
	}

	@Around("doTimePointCut()")
	public Object aroundAdivce(ProceedingJoinPoint pj) throws Throwable {
		log.info("time:arountAdvice before");
		Object result = pj.proceed();
		log.info("time:arountAdvice after");
		return result;
	}
}
