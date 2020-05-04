package com.mj;
/**
 * 最长连续子序列
 * @author yusael
 */
public class LIS {
	public static void main(String[] args) {
		System.out.println(lengthOfLIS1(new int[]{10, 2, 2, 5, 1, 7, 101, 18}));
		System.out.println(lengthOfLIS2(new int[]{10, 2, 2, 5, 1, 7, 101, 18}));
		System.out.println(lengthOfLIS3(new int[]{10, 2, 2, 5, 1, 7, 101, 18}));
	}
	
	static int lengthOfLIS3(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		// 牌堆的数量
		int len = 0;
		// 牌顶数组
		int[] top = new int[nums.length];
		// 遍历所有的牌
		for (int num : nums) {
			int begin = 0;
			int end = len;
			while (begin < end) {
				int mid = (begin + end) >> 1;
				if (num <= top[mid]) {
					end = mid;
				} else {
					begin = mid + 1;
				}
			}
			// 覆盖牌顶
			top[begin] = num;
			// 检查是否要新建一个牌堆
			if (begin == len) len++;
		}
		return len;
	}
	
	static int lengthOfLIS2(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		// 牌堆的数量
		int len = 0;
		// 牌顶数组
		int[] top = new int[nums.length];
		// 遍历所有的牌
		for (int num : nums) {
			int j = 0;
			while (j < len) {
				// 找到一个>=nums的牌顶
				if (top[j] >= num) {
					top[j] = num;
					break;
				}
				// 牌顶 < nums
				j++;
			}
			if (j == len) { // 新建一个牌堆
				len++;
				top[j] = num;
			}
		}
		return len;
	}
	
    static public int lengthOfLIS1(int[] nums) {
    	if (nums == null || nums.length == 0) return 0;
    	int[] dp = new int[nums.length];
    	int max = dp[0] = 1; // 只有一个元素则长度为1
    	for (int i = 1; i < dp.length; i++) {
    		dp[i] = 1; // 默认只有一个元素时长度为1
    		for (int j = 0; j < i; j++) {
    			// 无法成为一个上升子序列
    			if (nums[j] >= nums[i]) continue;
    			dp[i] = Math.max(dp[j] + 1, dp[i]);
    		}
    		max = Math.max(dp[i], max);
    	}
    	return max;
    }
}
