package com.mj.ks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
/**
 * 0-1 背包问题
 * @author yusael
 */
public class Knapsack {
	public static void main(String[] args) {
		select("价值主导", (Article a1, Article a2) -> {
			// 价值大的优先
			return a2.value - a1.value;
		});
		select("重量主导", (Article a1, Article a2) -> {
			// 重量小的优先
			return a1.weight - a2.weight; 
		});
		select("价值密度主导", (Article a1, Article a2) -> {
			// 价值密度大的优先
			return Double.compare(a2.valueDensity, a1.valueDensity);
		});
	}
	
	/**
	 * 以一个属性为主导实现贪心策略
	 * @param title 显示标题
	 * @param cmp 比较器决定主导属性, [价值、重量、价值密度]
	 */
	static void select(String title, Comparator<Article> cmp) {
		// 模拟题意的物品
		Article[] articles = new Article[] {
			new Article(35, 10), new Article(30, 40),
			new Article(60, 30), new Article(50, 50),
			new Article(40, 35), new Article(10, 40),
			new Article(25, 30)
		};
		
		// 通过比较器, 按某个主导属性进行排序
		Arrays.sort(articles, cmp);
		
		// 以某个属性为主导, 实现贪心策略
		int capacity = 150, weight = 0, value = 0;
		List<Article> selectedArticles = new LinkedList<Article>(); // 选择的物品集合
		for (int i = 0; i < articles.length && weight < capacity; i++) {
			int newWeight = weight + articles[i].weight;
			if (newWeight <= capacity) {
				weight = newWeight;
				value += articles[i].value;
				selectedArticles.add(articles[i]);
			}
		}
		System.out.println("-----------------------------");
		System.out.println("【" + title + "】");
		System.out.println("总价值: " + value);
		for (Article article : selectedArticles) {
			System.out.println(article);
		}
	}
}
