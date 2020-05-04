package com.mj;

public class Climb {
	public static void main(String[] args) {
		// n = 4; 
		// 1 1 1 1
		// 1 1 2
		// 1 2 1
		// 2 1 1
		// 2 2
		System.out.println(new Climb().climbStairs0(10));
		System.out.println(new Climb().climbStairs(10));
	}
	
	int climbStairs0(int n) {
		if (n <= 2) return n;
		return climbStairs0(n - 1) + climbStairs0(n - 2);
	}
	
	int climbStairs(int n) {
		if (n <= 2) return n;
		int first = 1;
		int second = 2;
		for(int i = 3; i <= n; i++) {
			second = first + second;
			first = second - first;
		}
		return second;
	}
	
	
	
}
