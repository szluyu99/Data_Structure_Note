package com.mj;

import com.mj.printer.BinaryTrees;
import com.mj.tree.AVLTree;

public class Main {
	// Integer类型的数据
	public static void test1(){
		Integer date[] = new Integer[] { 
			75, 94, 21, 7, 93, 31, 83, 65, 43, 50, 57, 56
			};
		AVLTree<Integer> avl = new AVLTree<>();
		for (int i = 0; i < date.length; i++) {
			avl.add(date[i]);
			System.out.println("【" + date[i] + "】");
			BinaryTrees.println(avl);
			System.out.println("-----------------------------------------");
		}
	}

	// 删除
	public static void test2(){
		Integer data[] = new Integer[] {
				67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
		};
		
		AVLTree<Integer> avl = new AVLTree<>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);
//			System.out.println("【" + data[i] + "】");
//			BinaryTrees.println(avl);
//			System.out.println("---------------------------------------");
		}
		
		for (int i = 0; i < data.length; i++) {
			avl.remove(data[i]);
			System.out.println("【" + data[i] + "】");
			BinaryTrees.println(avl);
			System.out.println("---------------------------------------");
		}
		
		
		BinaryTrees.println(avl);
	}
	public static void main(String[] args) {
		test2();
	}
}
