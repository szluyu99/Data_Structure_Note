package com.mj;

public class KMP {
	public static int indexOf(String text, String pattern) {
		// 检测数据合法性
		if (text == null || pattern == null) return -1;
		char[] textChars = text.toCharArray();
		int tlen = textChars.length;
		char[] patternChars = pattern.toCharArray();
		int plen = patternChars.length;
		if (tlen == 0 || plen == 0 || tlen < plen) return -1;
		
		// next表
		int[] next = next(pattern);
		
		int pi = 0, ti = 0;
		while (pi < plen && ti < tlen) {
			// next表置-1的精妙之处, pi = -1 则 pi = 0, ti++ 相当于模式串后一一位
			if (pi < 0 || textChars[ti] == patternChars[pi]) {
				ti++;
				pi++;
			} else {
				pi = next[pi];
			}
		} 
		return pi == plen ? ti - pi : -1;
	}
	
	/**
	 * next 表构造 - 优化
	 */
	private static int[] next(String pattern) {
		char[] chars = pattern.toCharArray();
		int[] next = new int [chars.length];
		
		next[0] = -1;
		int i = 0;
		int n = -1;
		int iMax = chars.length - 1;
		while (i < iMax) {
			if (n < 0 || chars[i] == chars[n]) {
				++i;
				++n;

				if (chars[i] == chars[n]) {
					next[i] = next[n];
				} else {
					next[i] = n;
				}
			} else {
				n = next[n];
			}
		}
		return next;
	}
	
	/**
	 * next表构造
	 */
	private static int[] next2(String pattern) {
		char[] chars = pattern.toCharArray();
		int[] next = new int [chars.length];
		
		next[0] = -1;
		int i = 0;
		int n = -1;
		int iMax = chars.length - 1;
		while (i < iMax) {
			if (n < 0 || chars[i] == chars[n]) {
				next[++i] = ++n;
			} else {
				n = next[n];
			}
		}
		return next;
	}
	
}
