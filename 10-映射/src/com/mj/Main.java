package com.mj;

import com.mj.file.FileInfo;
import com.mj.file.Files;
import com.mj.map.Map;
import com.mj.map.TreeMap;
import com.mj.set.Set;
import com.mj.set.TreeSet;

public class Main {
	
	public static void test1(){
		Map<String, Integer> treeMap = new TreeMap<>();
		treeMap.put("ddd", 444);
		treeMap.put("bbb", 222);
		treeMap.put("ccc", 333); 
		treeMap.put("fff", 555);
		treeMap.put("aaa", 111);

		treeMap.put("aaa", 999);
		treeMap.put("aaa", 888);
		treeMap.put("bbb", 555);
		
		treeMap.traversal(new Map.Visitor<String, Integer>() {
			public boolean visit(String key, Integer value) {
				System.out.println("key:" + key + "---value:" + value);
				return false;
			}
		});
	}

	public static void test2(){
		FileInfo fileInfo = Files.read("F:\\java\\javaPro-git", 
				new String[]{"java"});
		System.out.println("文件数量：" + fileInfo.getFiles());
		System.out.println("代码行数：" + fileInfo.getLines());
		String[] words = fileInfo.words();
		System.out.println("单词数量：" + words.length);
		
		Map<String, Integer> map = new TreeMap<>();
		for(int i = 0; i < words.length; i++){
			Integer count = map.get(words[i]);
			count = (count == null) ? 1 : (count + 1);
			map.put(words[i], count);
		}
		
		map.traversal(new Map.Visitor<String, Integer>() {
			public boolean visit(String key, Integer value) {
				System.out.println(key + "_" + value);
				return false;
			}
		});
		
	}
	
	public static void test3(){
		Set<Integer> set = new TreeSet<>();
		set.add(15);
		set.add(10);
		set.add(14);
		set.add(10);
		set.add(13);
		set.add(10);
		set.add(13);
	
		set.traversal(new Set.Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.println(element);
				return false;
			}
		});
		
	}
	public static void main(String[] args) {
		test3();
	}
}
