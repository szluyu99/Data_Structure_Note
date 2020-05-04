package com.mj;
/**
 * 八皇后优化 - 位运算
 */
public class Queens3 {
	public static void main(String[] args) {
		new Queens3().place8Queens();
	}
	
	// 该变量不是必须, 仅仅是为了打印
	int[] queens;
	// 标记着某一列是否有皇后了
	// 比如 00100111 代表0、1、2、5列已经有皇后
	byte cols; // byte是8位
	// 标记着某一对角线是否有皇后了(左上角->右下角)
	short leftTop; // short是16位
	// 标记着某一对角线是否有皇后了(右上角->左下角)
	short rightTop; // short是16位
	// 一共有多少种合理的摆法
	int ways = 0;
	
	/**
	 * n皇后
	 */
	void place8Queens() {
		queens = new int[8];
		
		place(0); // 从第0行开始摆放皇后
		System.out.println("八皇后一共有" + ways + "种摆法");
	}
	/**
	 * 从第 row 行开始摆放皇后
	 */
	void place(int row) {
		// 如果已经放到第8行,说明找到了一种8皇后的摆法
		if (row == 8) {
			ways++;
			show();
			return;
		}
		for (int col = 0; col < 8; col++) {
			int colV = 1 << col; // 00000001
			if((cols & colV) != 0) continue; // col列已经有皇后
			int ltV = 1 << (row - col + 7);
			if ((leftTop & ltV) != 0) continue;
			int rtV = 1 << (row + col);
			if ((rightTop & rtV) != 0) continue;
			
			queens[row] = col;
			
			cols |= colV;
			leftTop |= ltV;
			rightTop |= rtV;
			place(row + 1); // 这一列摆了皇后,继续下一列
			cols &= ~colV;
			leftTop &= ~ltV;
			rightTop &= ~rtV;
		}
	}
	
	void show() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (queens[row] == col) { // 摆放了皇后
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
