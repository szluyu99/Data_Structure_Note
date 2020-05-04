package com.mj;

import java.util.Stack;

public class Main {
	public static void main(String[] args) {
//		System.out.println(sum(100));
		a(5);
//		log(5);
//		System.out.println(new Main().factorial(4));
	}
	
//	int fib(int n) {
//		if (n <= 2) return 1;
//		return fib(n - 1) + fib(n - 2);
//	}
	
	int fib(int n) {
		return fib(n, 1, 1);
	}
	
	int fib(int n, int first, int second) {
		if (n <= 1) return 1;
		return fib(n - 1, second, first + second);
	}
	
	
	int factorial(int n ) {
		return factorial(n, 1);
	}
	/**
	 * @param result 从大到小累乘的结果
	 */
	int factorial(int n, int result) {
		if(n <= 1) return result;
		return factorial(n - 1, n * result);
	}
	
	static void log(int n) {
		Stack<Frame> frames = new Stack<>();;
		while (n > 0) {
			frames.push(new Frame(n, n + 10));
			n--;
		}
		while (!frames.isEmpty()) {
			Frame frame = frames.pop();
			System.out.println(frame.v);
		}
	}
	
//	static void log(int n) {
//		if(n < 1) return;
//		log(n - 1);
//		int v = n + 10;
//		System.out.println(v);
//	}
	
//	int sum(int n) {
//		if(n <= 1) return n;
//		return sum(n - 1) + n; 
//	}
//	int sum(int n) {
//		int result = 0;
//		for (int i = 0; i <= n; i++) {
//			result += i;
//		}
//		return result;
//	}
	int sum(int n) {
		if (n <= 1) return n;
		return (1 + n) * n >> 1;
	}
	
	/**
	 * 没有递归出口, 最终会 StackOverflow
	 */
	static void a(int v) {
		b(--v);
	}
	static void b(int v) {
		a(--v);;
	}
	
}
