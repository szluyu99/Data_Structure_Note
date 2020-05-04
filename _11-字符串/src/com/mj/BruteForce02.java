package com.mj;import com.mj.tool.Times;

public class BruteForce02 {
	/**
	 * 蛮力匹配2
	 */
	public static int indexOf(String text, String pattern) {
		if (text == null || pattern == null) return -1;
		char[] textChars = text.toCharArray();
		int tlen = textChars.length;
		char[] patternChars = pattern.toCharArray();
		int plen = patternChars.length;
		if (tlen == 0 || plen == 0 || tlen < plen) return -1;
		
		int tiMax = tlen - plen;
		for (int ti = 0; ti <= tiMax; ti++) {
			int pi = 0;
			for (; pi < plen; pi++) {
				if (textChars[ti + pi] != patternChars[pi]) break;
			}
			if (pi == plen) return ti;
		}
		return -1;
	}
}
