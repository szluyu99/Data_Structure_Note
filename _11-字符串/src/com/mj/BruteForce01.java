package com.mj;

public class BruteForce01 {
	
	/**
	 * 蛮力匹配 - 改进
	 */
	public static int indexOf2(String text, String pattern) {
		if (text == null || pattern == null) return -1;
		char[] textChars = text.toCharArray();
		int tlen = textChars.length;
		char[] patternChars = pattern.toCharArray();
		int plen = patternChars.length;
		if (tlen == 0 || plen == 0 || tlen < plen) return -1;
		
		int pi = 0, ti = 0;
		while (pi < plen && ti - pi <= tlen - plen) { // ti - pi <= tlen - plen 是关键
			if (textChars[ti] == patternChars[pi]) {
				ti++;
				pi++;
			} else {
				ti = ti - pi + 1;
				// ti -= pi - 1;
				pi = 0;
			}
		}
		return pi == plen ? ti - pi : -1;
	}
	
	/**
	 * 蛮力匹配
	 */
	public static int indexOf(String text, String pattern) {
		// 检测数据合法性
		if (text == null || pattern == null) return -1;
		char[] textChars = text.toCharArray();
		int tlen = textChars.length;
		char[] patternChars = pattern.toCharArray();
		int plen = patternChars.length;
		if (tlen == 0 || plen == 0 || tlen < plen) return -1;
		
		int pi = 0, ti = 0;
		while (pi < plen && ti < tlen) {
			if (textChars[ti] == patternChars[pi]) {
				ti++;
				pi++;
			} else {
				ti = ti - pi + 1;
				// ti -= pi - 1;
				pi = 0;
			}
		} 
		return pi == plen ? ti - pi : -1;
	}
	
}
