package com.mj;

public class Main {
	public static void main(String[] args) {
		ArrayList<Person> list = new ArrayList<>();
		
		list.add(new Person(10, "jack"));
		list.add(new Person(20, "rose"));
		list.add(null);
		list.add(null);
		
		System.out.println("add()添加元素: " + list);
		
		System.out.println("get()获取元素: " + list.get(0));
		
		list.set(0, new Person(99, "ghost"));
		System.out.println("set()设置元素值: " + list);
		
		list.remove(0);
		System.out.println("remove()删除元素: " + list);
		
		list.clear();
		System.out.println("clear()清空数组: " + list);
	}
}
