package com.mj;
/**
 * 最长公共子序列
 * @author yusael
 */
public class LCS {
	public static void main(String[] args) {
		int len = lcs3(new int[] {1, 3, 5, 9, 10}, new int[] {1, 4, 9, 10});
		System.out.println(len);
	}
	
	/**
	 * 非递归实现(滚动数组优化)
	 */
	static int lcs3(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0) return 0;
		int[][] dp = new int[2][nums2.length + 1];
		
		for (int i = 1; i <= nums1.length; i++) {
			int row = i & 1;
			int prevRow = (i - 1) & 1;
			for (int j = 1; j <= nums2.length; j++) {
				if (nums1[i - 1] == nums2[j - 1]) {
					dp[row][j] = dp[prevRow][j - 1] + 1;
				} else {
					dp[row][j] = Math.max(dp[prevRow][j], dp[row][j - 1]);
				}
			}
		}
		return dp[nums1.length & 1][nums2.length];
	}
	
	/**
	 * 非递归实现
	 */
	static int lcs2(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0;
		if (nums2 == null || nums2.length == 0) return 0;
		int[][] dp = new int[nums1.length + 1][nums2.length + 1];
		
		for (int i = 1; i <= nums1.length; i++) {
			for (int j = 1; j <= nums2.length; j++) {
				if (nums1[i - 1] == nums2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[nums1.length][nums2.length];
	}
	
	/**
	 * 递归实现
	 */
	static int lcs1(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0) return 0; // 检测非法数据
		if (nums2 == null || nums2.length == 0) return 0; // 检测非法数据
		return lcs1(nums1, nums1.length, nums2, nums2.length);
	}
	/**
	 * 求nums1前i个元素和nums2前j个元素的最长g公共子序列长度
	 * @param nums1
	 * @param i
	 * @param nums2
	 * @param j
	 */
	static int lcs1(int[] nums1, int i, int[] nums2, int j) {
		if (i == 0 || j == 0) return 0;
		// 最后一个元素相等, 返回前面的公共子序列长度 + 1
		if (nums1[i - 1] == nums2[j - 1]) {
			return lcs1(nums1, i - 1, nums2, j - 1) + 1;
		}
		return Math.max(
			lcs1(nums1, i - 1, nums2, j), 
			lcs1(nums1, i, nums2, j - 1)
		);
	}
	
}

	
