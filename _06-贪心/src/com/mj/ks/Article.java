package com.mj.ks;

public class Article {
	int weight; // 重量
	int value;  // 价值
	double valueDensity; // 价值密度
	
	public Article(int weight, int value) {
		this.weight = weight;
		this.value = value;
		valueDensity = value * 1.0 / weight;
	}
	@Override
	public String toString() {
		return "Article [weight=" + weight + ", value=" + value + ", ValueDensity=" + valueDensity + "]";
	}

}
