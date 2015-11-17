package com.example.aop;

public abstract interface Action {
	public abstract void doAct();

	public abstract void doActString(String paramString);

	public abstract void doActWithParam(String paramString, int paramInt);
}
