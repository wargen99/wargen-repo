package com.example.aop;

import java.util.ArrayList;

public class ActionHistory {
	private static ArrayList<String> list = new ArrayList();

	public static void setList(String value) {
		list.add(value);
	}
}
