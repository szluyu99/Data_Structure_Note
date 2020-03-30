package com.mj.sort.cmp;

import com.mj.sort.Sort;

/**
 * 选择排序
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			int max = 0;
			for (int begin = 1; begin <= end; begin++) {
				if (cmp(max, begin) < 0) {
					max = begin;
				}
			}
			swap(max, end);
		}
	}
}
