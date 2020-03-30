package com.mj;

import java.util.Comparator;

import com.mj.BinarySearchTree.Visitor;
import com.mj.file.Files;
import com.mj.printer.BinaryTrees;

public class Main {
	public static class PersonComparator implements Comparator<Person> { // 比较器
		@Override
		public int compare(Person e1, Person e2) {
			return e1.getAge() - e2.getAge();
		}
	}

	public static class PersonComparator2 implements Comparator<Person> {
		@Override
		public int compare(Person e1, Person e2) {
			return e2.getAge() - e1.getAge();
		}
	}
	
	// Integer类型的数据
	public static void test1(){
		Integer date[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < date.length; i++) {
			bst.add(date[i]);
		}
		BinaryTrees.println(bst);
	}

	// Person类型的数据
	public static void test2(){
		// Java，匿名类
		BinarySearchTree<Person> bst = new BinarySearchTree<>(new Comparator<Person>() {
			@Override
			public int compare(Person e1, Person e2) {
				return e1.getAge() - e2.getAge();
			}
		});
		Integer date[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
		for (int i = 0; i < date.length; i++) {
			bst.add(new Person(date[i]));
		}
		BinaryTrees.println(bst);
	}
	
	// 保存打印结果
	public static void test3(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for(int i = 0; i < 40; i++){
			bst.add((int)(Math.random()*100));
		}
		String string = BinaryTrees.printString(bst);
		string += "\n";
		Files.writeToFile("F:/1.txt", string);
	}
	
	// add() 时值相等的处理
	public static void test4(){
		BinarySearchTree<Person> bst = new BinarySearchTree<>(new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.getAge() - o2.getAge();
			}
		});
		bst.add(new Person(15, "jack"));
		bst.add(new Person(16, "rose"));
		bst.add(new Person(10, "jerry"));
		bst.add(new Person(10, "kali")); // add()时值相等最好覆盖，否则则不会替换
		BinaryTrees.println(bst);
	}
	
	// 遍历
	public static void test5(){
//		Integer date[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
//		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//		for (int i = 0; i < date.length; i++) {
//			bst.add(date[i]);
//		}
//		BinaryTrees.println(bst);
//		System.out.print("\n前序遍历：");
//		bst.preOrderTraversal();
//		System.out.print("\n中序遍历：");
//		bst.inorderTraversal();
//		System.out.print("\n后序遍历：");
//		bst.postorderTraversal();
//		System.out.print("\n层序遍历：");
//		bst.levelOrderTraversal();
	}
	
	// 访问器遍历
	public static void test6(){
		Integer date[] = new Integer[] { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < date.length; i++) {
			bst.add(date[i]);
		}
		BinaryTrees.println(bst);
		
		System.out.print("层次遍历：");
		bst.levelOrder(new Visitor<Integer>() {
			@Override
			public void visit(Integer element) {
				System.out.print("_" + element + "_ ");
			}
		});
		System.out.println();
		System.out.print("前序遍历：");
		bst.preOrderTraversal(new Visitor<Integer>() {
			@Override
			public void visit(Integer element) {
				System.out.print("_" + element + "_ ");
			}
		});
		System.out.println();
		System.out.print("中序遍历：");
		bst.inorderTraversal(new Visitor<Integer>() {
			@Override
			public void visit(Integer element) {
				System.out.print("_" + element + "_ ");
			}
		});
		System.out.println();
		System.out.print("后序遍历：");
		bst.postorderTraversal(new Visitor<Integer>() {
			@Override
			public void visit(Integer element) {
				System.out.print("_" + element + "_ ");
			}
		});
	}
	
	// 高度
	public static void test7(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for(int i = 0; i < 20; i++){
			bst.add((int)(Math.random()*100));
		}
		BinaryTrees.print(bst);
		System.out.println();
//		System.out.println(bst.height()1);//递归求高度
		System.out.println(bst.height());
	}
	
	// 是否是完全二叉树
	public static void test8(){
		/* 
		 *    7
		 *  4   8
		 *1   5 
		 * 
		 */
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Integer date[] = new Integer[] { 7, 4, 8, 1, 5};
		for (int i = 0; i < date.length; i++) {
			bst.add(date[i]);
		}
		BinaryTrees.println(bst);
		System.out.println(bst.isComplete());
	}

	// 是否包含某个结点
	public static void test9(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Integer date[] = new Integer[] { 7, 4, 8, 1, 5};
		for (int i = 0; i < date.length; i++) {
			bst.add(date[i]);
		}
		BinaryTrees.println(bst);
		System.out.println(bst.contains(6));
	}
	// 删除节点
	public static void test10(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Integer date[] = new Integer[] { 7, 4, 8, 1, 5};
		for (int i = 0; i < date.length; i++) {
			bst.add(date[i]);
		}
		BinaryTrees.println(bst);
		bst.remove(8);
		BinaryTrees.println(bst);
	}
	public static void main(String[] args) {
		test10();
	}
}
