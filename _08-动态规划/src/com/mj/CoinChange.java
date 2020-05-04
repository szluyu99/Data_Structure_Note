package com.mj;

public class CoinChange {
	public static void main(String[] args) {
//		System.out.println(coins1(41));
//		System.out.println(coins2(41));
//		System.out.println(coins3(41));
//		System.out.println(coins4(41));
		System.out.println(coins5(41, new int[]{1, 5, 20, 25}));
		
		// dp(41) = 凑够41需要的最少硬币数量 = min { dp{40}, dp{36}, dp{21}, dp{16} } + 1
		// dp(41 - 1) = dp(40) = 凑够40需要的最少硬币数量
		// dp(41 - 5) = dp(36) = 凑够36需要的最少硬币数量
		// dp(41 - 20) = dp(21) = 凑够21需要的最少硬币数量
		// dp(41 - 25) = dp(16) = 凑够16需要的最少硬币数量
		// min { dp{40}, dp{36}, dp{21}, dp{16} } + 1
	}
	
	static int coins5(int n, int[] faces) {
		if (n < 1 || faces == null || faces.length == 0 ) return -1;
		int[] dp = new int [n + 1];
		for (int i = 1; i <= n; i++) {
			int min = Integer.MAX_VALUE;
			for (int face : faces) {
				// 假如给我的面值是20, 要凑的是15, 则跳过此轮循环
				if (face > i) continue; // 如果给我的面值比我要凑的面值还大, 跳过此轮循环
				int v = dp[i - face];
				if (v < 0 || v >= min) continue;
				min = v;
			}
			// 说明上面的循环中每次都是continue, 要凑的面值比给定的所有面值小
			if (min == Integer.MAX_VALUE) {
				dp[i] = -1;
			} else {
				dp[i] = min + 1;
			}
		}
		return dp[n];
	}
	
//	static int coins5(int n, int[] faces) {
//		if (n < 1 || faces == null || faces.length == 0 ) return -1;
//		int[] dp = new int [n + 1];
//		for (int i = 1; i <= n; i++) {
//			int min = Integer.MAX_VALUE;
//			for (int face : faces) {
//				// 假如给我的面值是20, 要凑的是15, 则跳过此轮循环
//				if (face > i) continue; // 如果给我的面值比我要凑的面值还大, 跳过此轮循环
//				min = Math.min(dp[i - face], min);
//			}
//			dp[i] = min + 1;
//		}
//		return dp[n];
//	}
	
	static int coins4(int n) {
		if (n < 1) return -1; // 处理非法数据
		int[] dp = new int[n + 1];
		// faces[i]是凑够i分时最后选择的那枚硬币的面值
		int[] faces = new int[dp.length]; // 存放硬币面值(为了输出)
		for (int i = 1; i <= n; i++) {
			int min = Integer.MAX_VALUE;
			if (i >= 1 && dp[i - 1] < min) {
				min = dp[i - 1];
				faces[i] = 1;
			}
			// 上面一步其实必然执行, 可以直接写成下面这样
			// int min = dp[i - 1];
			// faces[i] = 1;
			if (i >= 5 && dp[i - 5] < min) {
				min = dp[i - 5];
				faces[i] = 5;
			}
			if (i >= 20 && dp[i - 20] < min) {
				min = dp[i - 20];
				faces[i] = 20;
			}
			if (i >= 25 && dp[i - 25] < min) {
				min = dp[i - 25];
				faces[i] = 25;
			}
			dp[i] = min + 1;
			print(faces, i); // 打印凑够面值 1 ~ n 的方案
		}
//		print(faces, n); // 打印凑够面值 n 的方案
		return dp[n];
	}
	// 打印凑够面值 n 的方案
	static void print(int[] faces, int n) {
		System.out.print("[" + n + "] = ");
		while (n > 0) {
			System.out.print(faces[n] + " ");
			n -= faces[n];
		}
		System.out.println();
	}
	
	/**
	 * 递推(自底向上)
	 */
	static int coins3(int n) {
		if (n < 1) return -1; // 处理非法数据
		int[] dp = new int [n + 1]; 
		for (int i = 1; i <= n; i++) { // 自底向上的递推
			int min = dp[i - 1];
			if (i >= 5) min = Math.min(min, dp[i - 5]);
			if (i >= 20) min = Math.min(min, dp[i - 20]);
			if (i >= 25) min = Math.min(min, dp[i - 25]);
			dp[i] = min + 1;
		}
		return dp[n];
	}
//	static int coins3(int n) {
//		if (n < 1) return -1; // 处理非法数据
//		int[] dp = new int[n + 1];
//		for (int i = 1; i <= n; i++) { // 自底向上的递推
//			int min = Integer.MAX_VALUE;
//			if (i >= 1) min = Math.min(min, dp[i - 1]);
//			if (i >= 5) min = Math.min(min, dp[i - 5]);
//			if (i >= 20) min = Math.min(min, dp[i - 20]);
//			if (i >= 25) min = Math.min(min, dp[i - 25]);
//			dp[i] = min + 1;
//		}
//		return dp[n];
//	}
	
	/**
	 * 记忆化搜索(自顶向下的调用)
	 */
	static int coins2(int n) {
		if (n < 1) return -1; // 处理非法数据
		int[] dp = new int[n + 1];
		int[] faces = new int[] {1, 5, 20, 25}; // 给定的面值数组, 用来决定初始化哪些面值

		for (int face : faces) {
			// 如果我要凑的钱是20元, 那么我肯定用不到25元面值
			if (face > n) break; // 用不到的面值不用初始化
			dp[face] = 1; // 初始化可能用到的面值
		}	
		return coins2(n, dp);
	}
	static int coins2(int n, int[] dp) {
		// 递归基
		if (n < 1) return Integer.MAX_VALUE;
		if (dp[n] == 0) { // 记忆化搜索, dp[n] == 0 表示以前没有算过, 那便初始化一下
			int min1 = Math.min(coins2(n - 25, dp), coins2(n - 20, dp));
			int min2 = Math.min(coins2(n - 5, dp), coins2(n - 1, dp));
			dp[n] = Math.min(min1, min2) + 1;
		}
		return dp[n];
	}
	/**
	 * 暴力递归(自顶向下的调用, 出现了重叠子问题)
	 */
	static int coins1(int n) {
		// 递归基
		if (n < 1) return Integer.MAX_VALUE;
		if (n == 1 || n == 5 || n == 20 || n == 25) return 1;
		
		// 找出四种取法的最小值
		int min1 = Math.min(coins1(n - 25), coins1(n - 20));
		int min2 = Math.min(coins1(n - 5), coins1(n - 1));
		return Math.min(min1, min2) + 1;
	}
}
