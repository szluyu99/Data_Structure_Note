package com.mj;

import com.mj.circle.CircleLinkedList;

public class Main {
	
	static void testList(List<Integer> list) {
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);
		list.add(0, 55); // [55, 11, 22, 33, 44]
		list.add(2, 66); // [55, 11, 66, 22, 33, 44]
		list.add(list.size(), 77); // [55, 11, 66, 22, 33, 44, 77]

		list.remove(0); // [11, 66, 22, 33, 44, 77]
		list.remove(2); // [11, 66, 33, 44, 77]
		list.remove(list.size() - 1); // [11, 66, 33, 44]

		Asserts.test(list.indexOf(44) == 3);
		Asserts.test(list.indexOf(22) == List.ELEMENT_NOT_FOUND);
		Asserts.test(list.contains(33));
		Asserts.test(list.get(0) == 11);
		Asserts.test(list.get(1) == 66);
		Asserts.test(list.get(list.size() - 1) == 44);
		
		System.out.println(list);
	}
	
	public static void josephus(){
		CircleLinkedList<Integer> list = new CircleLinkedList<>();
		for(int i = 1; i <= 8; i++){
			list.add(i);
		}
		list.reset(); // current->1
		while(!list.isEmpty()){
			list.next();
			list.next();
			System.out.println(list.remove());
		}
	}
	
	public static void main(String[] args) {
		josephus();
//		testList(new ArrayList<>());  // 动态数组
//		testList(new LinkedList<>()); // 双向链表
//		testList(new SingleCircleLinkedList<>()); // 单向循环链表
		testList(new CircleLinkedList<>());
	}

}
