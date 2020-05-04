package com.mj;

public class Hanoi {
	public static void main(String[] args) {
		new Hanoi().hanoi(3, "A", "B", "C");
	}
	/**
	 * 将第 i 号盘子从 from 移到 to
	 */
	void move(int i, String from, String to) {
		System.out.println(i + "号盘子: " + from + "->" + to);
	}
	/**
	 * 将 n 个盘子从 p1 移动到 p3
	 */
	void hanoi(int n, String p1, String p2, String p3) {
		if (n <= 1) {
			move(n, p1, p3);
			return;
		}
		hanoi(n - 1, p1, p3, p2);
		move(n, p1, p3);
		// hanoi(1, p	1, p2, p3);
		hanoi(n - 1, p2, p1, p3);
	}
	
}
