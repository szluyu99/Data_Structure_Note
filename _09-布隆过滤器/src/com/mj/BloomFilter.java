package com.mj;

public class BloomFilter<T> {
	/**
	 * 二进制向量的长度(一共有多少个二进制位)
	 */
	private int bitSize;
	/**
	 * 二进制向量
	 */
	private long[] bits;
	/**
	 * 哈希函数的个数
	 */
	private int hashSize;
	
	/**
	 * 布隆过滤器的构造
	 * @param n 数据规模
	 * @param p 误判率, 取值范围(0, 1)
	 */
	public BloomFilter(int n, double p){
		if (n <= 0 || p <= 0 || p >= 1) { // 非法输入检测
			throw new IllegalArgumentException("wrong n or p");
		}
		
		// 根据公式求出对应的数据
		double ln2 = Math.log(2);
		// 求出二进制向量的长度
		bitSize = (int) (- (n * Math.log(p)) / (ln2 * ln2));
		hashSize = (int) (bitSize * ln2 / n);
		// bits数组的长度
		bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE]; // 分页公式
		// (64 + 64 - 1) / 64 = 127 / 64 = 1
		// (128 + 64 - 1) / 64 = 2
		// (130 + 64 - 1) / 64 = 3
		
		// 分页问题:
		// 每一页显示100条数据, pageSize = 100
		// 一共有999999条数据, n = 999999
		// 请问有多少页 pageCount = (n + pageSize - 1) / pageSize
	};
	
	/**
	 * 添加元素
	 */
	public boolean put(T value) {
		nullCheck(value);
		
		// 利用value生成2个整数
		int hash1 = value.hashCode();
		int hash2 = hash1 >>> 16;

		boolean result = false;
		for (int i = 1; i <= hashSize; i++) {
			int combinedHash = hash1 + (i * hash2);
			if (combinedHash < 0) {
				combinedHash = ~combinedHash;
			}	
			// 生成一个二进制的索引
			int index = combinedHash % bitSize;
			// 设置第index位置的二进制为1
			if (set(index)) result = true;
			//   101010101010010101
			// | 000000000000000100	   1 << index
			//   101010111010010101
		}
		return result;
	}
	
	/**
	 * 判断一个元素是否存在
	 */
	public boolean contains(T value) {
		nullCheck(value);
		// 利用value生成2个整数
		int hash1 = value.hashCode();
		int hash2 = hash1 >>> 16;
		
		for (int i = 1; i <= hashSize; i++) {
			int combinedHash = hash1 + (i * hash2);
			if (combinedHash < 0) {
				combinedHash = ~combinedHash;
			}	
			// 生成一个二进制的索引
			int index = combinedHash % bitSize;
			// 查询第index位置的二进制是否为0
			if (!get(index)) return false;
			//   101010101010010101
			// | 000000000000000100	   1 << index
			//   101010111010010101
		}
		return true;
	}
	
	/**
	 * 设置index位置的二进制为1
	 */
	private boolean set(int index){
		// 对应的long值
		long value = bits[index / Long.SIZE];
		int bitValue = 1 << (index % Long.SIZE);
		bits[index / Long.SIZE] = value | bitValue;
		return (value & bitValue) == 0;
		/*
		 *    100000
		 *  | 000100   1 << 2
		 *  ---------
		 *    100100
		 */
	}
	
	/**
	 * 查看index位置的二进制的值
	 * @param index
	 * @return true代表1, false代表0
	 */
	private boolean get(int index) {
		// 对应的long值
		long value = bits[index / Long.SIZE];
		return (value & (1 << (index % Long.SIZE))) != 0;
		/*
		 *   10101111
		 * & 00000100
		 * -----------
		 *   00000100 != 0, 说明index位的二进制为1
		 */
	}
	
	private void nullCheck(T value) {
		if (value == null) {
			throw new IllegalArgumentException("Value must not be null.");
		}
	}
	
}
