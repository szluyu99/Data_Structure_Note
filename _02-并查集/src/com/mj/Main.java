package com.mj;

import com.mj.tools.Asserts;
import com.mj.tools.Times;
import com.mj.union.GenericUnionFind;
import com.mj.union.UnionFind;
import com.mj.union.UnionFind_QU_R;
import com.mj.union.UnionFind_QU_R_PC;
import com.mj.union.UnionFind_QU_R_PH;
import com.mj.union.UnionFind_QU_R_PS;
import com.mj.union.UnionFind_QU_S;

public class Main {
	static final int count = 10000000;

	public static void main(String[] args) {
//		testTime(new UnionFind_QF(count)); // 太慢了,不测试
//		testTime(new UnionFind_QU(count)); // 太慢了,不测试
		testTime(new UnionFind_QU_S(count));
		testTime(new UnionFind_QU_R(count));
		testTime(new UnionFind_QU_R_PC(count));
		testTime(new UnionFind_QU_R_PS(count));
		testTime(new UnionFind_QU_R_PH(count));
		testTime(new GenericUnionFind<Integer>());
		
//		GenericUnionFind<Student> uf = new GenericUnionFind<>();
//		Student stu1 = new Student(1, "jack");
//		Student stu2 = new Student(2, "rose");
//		Student stu3 = new Student(3, "jack");
//		Student stu4 = new Student(4, "rose");
//		uf.makeSet(stu1);
//		uf.makeSet(stu2);
//		uf.makeSet(stu3);
//		uf.makeSet(stu4);
//		
//		uf.union(stu1, stu2);
//		uf.union(stu3, stu4);
//		
//		uf.union(stu1, stu4);
//		
//		Asserts.test(uf.isSame(stu2, stu3));
//		Asserts.test(uf.isSame(stu3, stu4));
//		Asserts.test(!uf.isSame(stu1, stu3));
	}
	
	static void testTime(GenericUnionFind<Integer> uf) {
		for (int i = 0; i < count; i++) {
			uf.makeSet(i);
		}
		
		uf.union(0, 1);
		uf.union(0, 3);
		uf.union(0, 4);
		uf.union(2, 3);
		uf.union(2, 5);
		
		uf.union(6, 7);

		uf.union(8, 10);
		uf.union(9, 10);
		uf.union(9, 11);
		
		Asserts.test(!uf.isSame(2, 7));

		uf.union(4, 6);
		
		Asserts.test(uf.isSame(2, 7));
		
		Times.test(uf.getClass().getSimpleName(), () -> {
			for (int i = 0; i < count; i++) {
				uf.union((int)(Math.random() * count), 
						(int)(Math.random() * count));
			}
			
			for (int i = 0; i < count; i++) {
				uf.isSame((int)(Math.random() * count), 
						(int)(Math.random() * count));
			}
		});
	}
	
	static void testTime(UnionFind uf) {
		uf.union(0, 1);
		uf.union(0, 3);
		uf.union(0, 4);
		uf.union(2, 3);
		uf.union(2, 5);
		
		uf.union(6, 7);

		uf.union(8, 10);
		uf.union(9, 10);
		uf.union(9, 11);
		
		Asserts.test(!uf.isSame(2, 7));
		uf.union(4, 6);
		Asserts.test(uf.isSame(2, 7));
		
		Times.test(uf.getClass().getSimpleName(), () -> {
			for (int i = 0; i < count; i++) {
				uf.union((int)(Math.random() * count), 
						(int)(Math.random() * count));
			}
			
			for (int i = 0; i < count; i++) {
				uf.isSame((int)(Math.random() * count), 
						(int)(Math.random() * count));
			}
		});
	}
}
