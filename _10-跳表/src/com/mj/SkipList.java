package com.mj;

import java.util.Comparator;

/**
 * 跳表
 * @author yusael
 */
@SuppressWarnings("unchecked")
public class SkipList <K, V> {
	private static final double P = 0.25; // 仿照Redis的做法, 维护一个概率值
	private static final int MAX_LEVEL = 32; // 最高层数
	private int size;
	private Comparator<K> comparator;
	/**
	 * 有效层数
	 */
	private int level;
	/**
	 * 不存放任何 K-V 的虚拟头结点
	 */
	private Node<K, V> first; 
	
	public SkipList(Comparator<K> comparator) {
		this.comparator = comparator;
		// 头节点的高度必须是跳表的最高层数(非有效层数)
		first = new Node<>(null, null, MAX_LEVEL);
	}
	
	public SkipList() {
		this(null);
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public V put(K key, V value) {
		keyCheck(key);
		
		Node<K, V> node = first;
		// 前驱节点, 插入节点时要用到, 获取了前驱就相当于获取了后继
		Node<K, V>[] prevs = new Node[level];
		for (int i = level - 1; i >= 0 ; i--) {
			int cmp = -1;
			while (node.nexts[i] != null 
					&& (cmp = compare(key, node.nexts[i].key)) > 0) {
				node = node.nexts[i];
			}
			// node.nexts[i].key >= key
			if (cmp == 0) { // 节点原本就存在
				V oldV = node.nexts[i].value;
				node.nexts[i].value = value;
				return oldV;
			}
			prevs[i] = node; // 保存前驱节点
		}
			
		// 新节点的层数(随机)
		int newLevel = randomLevel();
		// 添加新节点
		Node<K, V> newNode = new Node<>(key, value, newLevel);
		// 设置新节点的前驱和后继(获取了前驱就相当于获取了后继)
		for (int i = 0; i < newLevel; i++) {
			if (i >= level) { // 新节点的层数比有效层数高
				// 头结点成为新节点的前驱
				first.nexts[i] = newNode; // 让头节点指向新节点(头节点创建时是最高层)
				// 后继结点默认指向null
			} else { // 新节点的层数比有效层数低
				newNode.nexts[i] = prevs[i].nexts[i]; // 让新节点的后继节点指向(之前的)前驱的后继
				prevs[i].nexts[i] = newNode; // 让前驱节点指向新节点
			}
		}
		level = Math.max(level, newLevel); // 计算跳表的最终层数(更新有效层数)
		size++; // 节点数量增加
		return null; // 之前不存在该节点, 返回null
	}
	
	public V get(K key) {
		keyCheck(key);
		
		// first.next[3] == 21节点
		// first.next[2] == 9节点
		// first.next[1] == 6节点
		// first.next[0] == 3节点

		Node<K, V> node = first;
		for (int i = level - 1; i >= 0 ; i--) {
			int cmp = -1;
			while (node.nexts[i] != null 
					&& (cmp = compare(key, node.nexts[i].key)) > 0) {
				node = node.nexts[i];
			}
			// node.nexts[i].key >= key
			if (cmp == 0) return node.nexts[i].value;
		}
		return null;
	}
	
	public V remove(K key) {
		keyCheck(key);
		Node<K, V> node = first;
		Node<K, V>[] prevs = new Node[level];
		boolean exist = false; // 判断是否有该节点
		for (int i = level - 1; i >= 0; i--) {
			int cmp = -1;
			while (node.nexts[i] != null 
					&& (cmp = compare(key, node.nexts[i].key)) > 0) {
				node = node.nexts[i];
			}
			// key <= node.nexts[i].key 
			if (cmp == 0) exist = true; // 存在
			prevs[i] = node; // 保存前驱节点
		}
		if (!exist) return null; // 跳表中没有该元素, 无需删除
		
		// 需要被删除的节点
		// 此时该元素必然存在, 且node必然为最下面一层, 该元素的前驱节点 
		Node<K, V> removedNode = node.nexts[0];
		
		// 设置后继
		for (int i = 0 ; i < removedNode.nexts.length; i++) {
			prevs[i].nexts[i] = removedNode.nexts[i];
		}
		// 更新跳表的层数
		int newLevel = level;
		while (--newLevel > 0 && first.nexts[newLevel] == null) {
			level = newLevel;
		}
		size--; // 数量减少
		return removedNode.value;
	}
	
	/**
	 * 跳表中加入新结点时的层数随机
	 * @return
	 */
	private int randomLevel() {
		int level = 1; 
		while (Math.random() < P && level < MAX_LEVEL) { // 每次有25%的概率+1层
			level++;
		}
		return level;
	}
	
	private void keyCheck(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key must not be null.");
		}
	}
	
	private int compare(K k1, K k2) {
		return comparator != null
				? comparator.compare(k1, k2)
				: ((Comparable<K>)k1).compareTo(k2);
	}

	private static class Node<K, V> {
		K key;
		V value;
		Node<K, V>[] nexts; // 该节点的不同层指向的下一节点
		public Node(K key, V value, int level) {
			this.key = key;
			this.value = value;
			nexts = new Node[level];
		}
		@Override
		public String toString() {
			return key + ":" + value + "_" + nexts.length;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("一共" + level + "层").append("\n");
		for (int i = level - 1; i >= 0; i--) {
			Node<K, V> node = first;
			while (node.nexts[i] != null) {
				sb.append(node.nexts[i]);
				sb.append("\t");
				node = node.nexts[i];
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
