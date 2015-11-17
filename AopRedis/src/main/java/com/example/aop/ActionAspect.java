package com.example.aop;

import com.example.redis.ActionHistoryRepo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class ActionAspect {
	@Autowired
	private ActionHistoryRepo<String, String> saver;

	@Before("execution(* com.example.aop.ActImpl.do*(..))")
	public void actBefore(JoinPoint joinpoint) {
		String signature = joinpoint.getSignature().toShortString();
		System.out.println("captured before");
		this.saver.addAction(signature);
	}

	@After("execution(* com.example.aop.ActImpl.do*(..))")
	public void actAfter(JoinPoint joinpoint) {
	}
}
