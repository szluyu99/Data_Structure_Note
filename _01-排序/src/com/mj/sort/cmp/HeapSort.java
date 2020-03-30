package com.mj.sort.cmp;

import com.mj.sort.Sort;

/**
 * 堆排序
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {
	private int heapSize; // 堆大小

	@Override
	protected void sort() {
		// 原地建堆（自下而上的下滤）
		heapSize = array.length;
		for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
			siftDown(i);
		}
		
		while (heapSize > 1) {
			// 交换堆顶元素和尾部元素
			swap(0, --heapSize);

			// 对0位置进行siftDown（恢复堆的性质）
			siftDown(0);
		}
	}
	
	private void siftDown(int index) {
		T element = array[index];
		
		int half = heapSize >> 1;
		while (index < half) { // index必须是非叶子节点
			// 默认是左边跟父节点比
			int childIndex = (index << 1) + 1;
			T child = array[childIndex];
			
			int rightIndex = childIndex + 1;
			// 右子节点比左子节点大
			if (rightIndex < heapSize && 
					cmp(array[rightIndex], child) > 0) { 
				child = array[childIndex = rightIndex];
			}
			
			// 大于等于子节点
			if (cmp(element, child) >= 0) break;
			
			array[index] = child;
			index = childIndex;
		}
		array[index] = element;
	}
}
