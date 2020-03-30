package com.mj.sort.cmp;

import com.mj.sort.Sort;

/**
 * 插入排序
 */
public class InsertionSort1 <T extends Comparable<T>> extends Sort<T>{

	@Override
	protected void sort() {
		for(int begin = 1; begin < array.length; begin++){
			int cur = begin;
			while(cur > 0 && cmp(cur, cur-1) < 0){
				swap(cur, cur - 1);
				cur--;
			}
		}
	}

}
