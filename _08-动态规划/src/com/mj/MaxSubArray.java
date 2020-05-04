package com.mj;
/**
 * 最大连续子序列
 * @author yusael
 */
public class MaxSubArray {
	public static void main(String[] args) {
		System.out.println(maxSubArray2(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
	}
	
	static int maxSubArray2(int[] nums) {
		if (nums == null || nums.length == 0) return 0; // 非法数据监测
		int dp = nums[0]; // 初始状态
		int max = dp;
		// 状态转移方程
		for (int i = 1; i < nums.length; i++) {
			if (dp > 0) {
				dp = dp + nums[i];
			} else {
				dp = nums[i];
			}
			max = Math.max(max, dp);
		}
		return max;
	}
	
	static int maxSubArray1(int[] nums) {
		if (nums == null || nums.length == 0) return 0; // 非法数据检测
		int[] dp = new int[nums.length]; 
		int max = dp[0] = nums[0]; // 初始状态
		// 状态转移方程
		for (int i = 1; i < dp.length; i++) {
			if (dp[i - 1] > 0) {
				dp[i] = dp[i - 1] + nums[i];
			} else {
				dp[i] = nums[i];
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}
	
}
