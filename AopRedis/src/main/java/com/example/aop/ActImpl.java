package com.example.aop;


import org.springframework.stereotype.Component;

@Component
public class ActImpl implements Action {
	public void doAct() {
		System.out.println("do Act");
	}

	public void doActString(String actParam) {
		System.out.println(actParam + " do act");
	}

	public void doActWithParam(String actParam, int actnum) {
		System.out.println(actParam + actnum + " do act");
	}
}
