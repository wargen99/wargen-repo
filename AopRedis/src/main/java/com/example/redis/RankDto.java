package com.example.redis;

public class RankDto {
	private int rank;
	private String methodName;
	private int callCount;

	public int getRank() {
		return this.rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public int getCallCount() {
		return this.callCount;
	}

	public void setCallCount(int callCount) {
		this.callCount = callCount;
	}
}
