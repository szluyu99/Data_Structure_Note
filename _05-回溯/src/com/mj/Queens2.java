package com.mj;

public class Queens2 {
	public static void main(String[] args) {
		new Queens2().placeQueens(8);
	}
	
	// 该变量不是必须, 仅仅是为了打印
	int[] queens;
	// 标记着某一列是否有皇后了
	boolean[] cols;
	// 标记着某一对角线是否有皇后了(左上角->右下角)
	boolean[] leftTop;
	// 标记着某一对角线是否有皇后了(右上角->左下角)
	boolean[] rightTop;
	// 一共有多少种合理的摆法
	int ways = 0;
	
	/**
	 * n皇后
	 */
	void placeQueens(int n) {
		if (n < 1) return;
		
		// 初始化
		queens = new int[n];
		cols = new boolean[n]; // 总共有n列
		leftTop = new boolean[(n << 1) - 1]; // n条对角线
		rightTop = new boolean[leftTop.length]; // 上面已经做过一次运算,无需再做
		
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
			// 第col列已经有皇后, 继续下一轮
			if (cols[col]) continue;
			int ltIndex = row - col + cols.length - 1; // 左上角->右下角的对角线索引
			// 左上->右下已经有皇后, 继续下轮
			if (leftTop[ltIndex]) continue;
			int rtIndex = row + col;  // 右上角->左下角的对角线索引
			// 右上->左下已经有皇后, 继续下轮
			if (rightTop[rtIndex]) continue;
			// 给该列摆上皇后
			cols[col] = leftTop[ltIndex] = rightTop[rtIndex] = true;
			place(row + 1); // 该列摆已经摆好了皇后,继续下一行
			// 这一步很关键, 列、对角线都是牵一发而动全身的影响, 需要重置
			cols[col] = leftTop[ltIndex] = rightTop[rtIndex] = false;
		}
	}
	
	void show() {
		for (int row = 0; row < queens.length; row++) {
			for (int col = 0; col < queens.length; col++) {
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
