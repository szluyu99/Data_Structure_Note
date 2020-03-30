package com.mj;

public class BinarySearch {
	/**
	 * 查找v在有序数组array中的位置
	 */
	public static int indexOf(int[] array, int v){
		if(array == null || array.length < 1) return -1;
		int begin = 0;
		int end = array.length;
		while(begin < end){
			int mid = (begin + end) >> 1;
			if(v < array[mid]){
				end = mid;
			}else if(v > array[mid]){
				begin = mid + 1;
			}else{
				return mid;
			}
		}
		return -1;
	}
	/**
	 * 查找v在有序数组array中待插入位置
	 */
	public static int search(int[] array, int v){
		if(array == null || array.length == 0) return -1;
		int begin = 0;
		int end = array.length;
		while(begin < end){
			int mid = (begin + end) >> 1;
			if(v < array[mid]){
				end = mid;
			}else{
				begin = mid + 1;
			}
		}
		return begin; // 此时begin==end
	}
}
