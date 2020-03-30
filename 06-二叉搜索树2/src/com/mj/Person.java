package com.mj;

public class Person implements Comparable<Person> {
	private int age;
	private String name;
	public Person(int age, String name){
		this.age = age;
		this.name = name;
	}
	public Person(int age){
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int compareTo(Person e) { // 实现了比较器
		return age - e.age;
	}
	@Override
	public String toString() {
		return age + "_" + name;
	}

}
