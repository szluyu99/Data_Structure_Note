package com.mj;

import java.util.HashMap;
/**
 * @author yusael
 * Trie 字典树
 */
public class Trie <V> {
	private int size;
	private Node<V> root;
	
	private static class Node<V>{
		Node<V> parent;
		HashMap<Character, Node<V>> children;
		Character character; // 为删除做准备
		V value;
		boolean word; // 是否为单词的结尾(是否位一个完整的单词)
		public Node(Node<V> parent) {
			this.parent = parent;
		}
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public void clear(){
		size = 0;
		root = null;
	}
	
	public V get(String key){
		Node<V> node = node(key);
		return (node!=null && node.word) ? node.value : null;
	}
	
	public boolean contains(String key){
		Node<V> node = node(key);
		return node!=null && node.word;
	}
	
	public V add(String key, V value){
		keyCheck(key);
		
		// 创建根节点
		if(root == null){
			root = new Node<>(null);
		}
		
		Node<V> node = root;
		int len = key.length();
		for(int i = 0; i < len; i++){
			char c = key.charAt(i);
			boolean emptyChildren = (node.children==null);
			Node<V> childNode = emptyChildren ? null : node.children.get(c);
			if(childNode == null){
				childNode = new Node<>(node);
				childNode.character = c;
				node.children = emptyChildren ? new HashMap<>() : node.children;
				node.children.put(c, childNode);
			}
			node = childNode;
		}
		
		if(node.word){ // 已经存在这个单词
			V oldValue = node.value;
			node.value = value;
			return oldValue;
		}
		
		// 新增一个单词
		node.word = true;
		node.value = value;
		size++;
		return null;
	}
	
	public V remove(String key){
		// 找到最后一个节点
		Node<V> node = node(key);
		// 如果不是单词结尾，不用作任何处理
		if(node==null || !node.word) return null;
		size--;
		V oldValue = node.value;
			
		// 如果还有子节点
		if(node.children!=null && !node.children.isEmpty()){
			node.word = false;
			node.value = null;
			return oldValue; 
		}
		
		// 没有子节点
		Node<V> parent = null;
		while((parent = node.parent) != null){
			parent.children.remove(node.character);
			if(parent.word || !parent.children.isEmpty()) break;
			node = parent;
		}
		return oldValue;
	}
	
	public boolean startsWith(String prefix){
		return node(prefix) != null;
	}
	
	/**
	 * 根据传入字符串，找到最后一个节点
	 * 例如输入 dog
	 * 找到 g
	 */
	private Node<V> node(String key){
		keyCheck(key);
		
		Node<V> node = root;
		int len = key.length();
		for(int i = 0; i < len; i++){
			if(node==null || node.children==null || node.children.isEmpty()) return null;
			char c = key.charAt(i);
			node = node.children.get(c);
		}
		return node;
	}
	
	private void keyCheck(String key){
		if(key==null || key.length()==0){
			throw new IllegalArgumentException("key must not be empty");
		}
	}
	
}
