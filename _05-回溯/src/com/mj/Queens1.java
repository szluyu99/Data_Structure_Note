package com.mj;

public class Queens1 {
	public static void main(String[] args) {
		new Queens1().placeQueens(4);
	}
	
	// cols[row] = col; 表示第row行第col列摆放了皇后
	int cols[];
	// 一共有多少种合理的摆法
	int ways = 0;
	
	/**
	 * n皇后
	 */
	void placeQueens(int n) {
		if (n < 1) return;
		cols = new int[n];
		place(0); // 从第0行开始摆放皇后
		System.out.println(n + "皇后一共有" + ways + "种摆法");
	}
	/**
	 * 从第 row 行开始摆放皇后
	 */
	void place(int row) {
		// 如果已经放到第n行,说明找到了一种n皇后的摆法
		if (row == cols.length) {
			ways++;
			show();
			return;
		}
		for (int col = 0; col < cols.length; col++) {
			if (isValid(row, col)) {
				// 在第row行第col列摆放皇后
				cols[row] = col;
				place(row + 1);
			}
		}
	}
	
	/**
	 * 判断第row行第col列是否可以摆放皇后
	 */
	boolean isValid(int row, int col) {
		for (int i = 0; i < row; i++) {
			// 第col列已经有皇后
			if (cols[i] == col) {
				System.out.println("["+ row + "][" + col + "]=false");
				return false;
			}
			// 第i行的皇后根第row行第col列格子处在同一斜线上
			// 45度角斜线: y-y0 = (x-x0), 则 (y-y0)/(x-x0) = 1, 表示为45度角的斜线
			if (row - i == Math.abs(col - cols[i])) {
				System.out.println("["+ row + "][" + col + "]=false");
				return false;
			}
		}
		System.out.println("["+ row + "][" + col + "]=true");
		return true;
	}
	
	void show() {
		for (int row = 0; row < cols.length; row++) {
			for (int col = 0; col < cols.length; col++) {
				if (cols[row] == col) { // 摆放了皇后
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}
	
}
