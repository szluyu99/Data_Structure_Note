package com.mj.sort.cmp;

import com.mj.sort.Sort;

/**
 * 归并排序
 */
@SuppressWarnings("unchecked")
	public class MergeSort <T extends Comparable<T>> extends Sort<T> {
	private T[] leftArray;
	
	@Override
	protected void sort() {
		// 准备一段临时的数组空间, 在merge操作中使用
		leftArray = (T[])new Comparable[array.length >> 1];
		sort(0, array.length);
	}
	
	/**
	 * 对 [begin, end) 范围的数据进行归并排序
	 */	
	private void sort(int begin, int end){
		if(end - begin < 2) return; // 至少要2个元素
		
		int mid = (begin + end) >> 1;
		sort(begin, mid); // 归并排序左半子序列
		sort(mid, end);	// 归并排序右半子序列
		merge(begin, mid, end); // 合并整个序列
	}
	
	/**
	 * 将 [begin, mid) 和 [mid, end) 范围的序列合并成一个有序序列
	 */
	private void merge(int begin, int mid, int end){
		int li = 0, le = mid - begin; // 左边数组(基于leftArray)
		int ri = mid, re = end;	// 右边数组(array)
		int ai = begin; // array的索引
		
		// 备份左边数组到leftArray
		for(int i = li; i < le; i++){
			leftArray[i] = array[begin + i];
		}
		
		// 如果左边还没有结束
		while(li < le){ // li == le 左边结束, 则直接结束归并
			if(ri < re && cmp(array[ri], leftArray[li]) < 0){ // cmp改为<=0会失去稳定性
				array[ai++] = array[ri++]; // 右边<左边, 拷贝右边数组到array
			}else{
				array[ai++] = leftArray[li++]; // 左边<=右边, 拷贝左边数组到array
			}
		}
	}

}
