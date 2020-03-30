package com.mj.sort.cmp;

import com.mj.sort.Sort;

/**
 * 冒泡排序-优化1
 * 如果序列已经完全有序，可以提前终止冒泡排序
 */
public class BubbleSort2<T extends Comparable<T>> extends Sort<T>{

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			boolean sorted = true;
			for (int begin = 1; begin <= end; begin++) {
				if (cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
					sorted = false;
				}
			}
			if (sorted) break;
		}
	}
}
