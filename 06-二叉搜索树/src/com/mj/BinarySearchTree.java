package com.mj;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import com.mj.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {
	private int size;
	private Node<E> root; // 根节点
	private Comparator<E> comparator; // 比较器
	public BinarySearchTree (Comparator<E> comparator){ // 可以传一个比较器
		this.comparator = comparator;
	}
	public BinarySearchTree(){ // 默认不传比较器
		this(null);
	}
	
	public static class Node<E> {
		E element; // 元素值
		Node<E> left; // 左节点
		Node<E> right; // 右节点
		Node<E> parent; // 父节点

		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		public boolean isLeaf(){ // 是否叶子节点
			return left==null && right==null;
		}
		public boolean hasTwoChildren(){ // 是否有两个子节点
			return left!=null && right!=null;
		}
	}

	/**
	 * 元素的数量
	 */
	public int size() {
		return size;
	}

	/**
	 * 是否为空
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 清空所有的元素
	 */
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * 添加元素
	 */
	public void add(E element) {
		elementNotNullCheck(element); // 不能传入空节点
		// 传入第一个节点
		if(root == null){
			root = new Node<>(element, null);
			size++;
			return;
		}
		Node<E> node = root;
		Node<E> parent = root;
		int cmp = 0;
		while(node != null){
			parent = node; // 父节点
			cmp = compareTo(node.element, element); // 方向
			if(cmp < 0){
				node = node.right;
			}else if(cmp > 0){
				node = node.left;
			}else{ // 相等，最好是覆盖掉
				node.element = element;
				return;
			}
		}
		Node<E> newNode = new Node<>(element, parent);
		if(cmp < 0){
			parent.right = newNode;
		}else{
			parent.left = newNode;
		}
		size++;
	}
	/*	
    // 前序遍历
	public void preOrderTraversal(Node<E> node){
		if(node == null) return;
		System.out.print(node.element + " ");
		preOrderTraversal(node.left);
		preOrderTraversal(node.right);
	}
	public void preOrderTraversal(){
		preOrderTraversal(root);
	}
	// 中序遍历
	public void inorderTraversal(Node<E> node){	
		if(node == null) return;
		inorderTraversal(node.left);
		System.out.print(node.element + " ");
		inorderTraversal(node.right);
	}
	public void inorderTraversal(){
		inorderTraversal(root);
	}
	// 后序遍历
	public void postorderTraversal(Node<E> node){	
		if(node == null) return;
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		System.out.print(node.element + " ");
	}
	public void postorderTraversal(){
		postorderTraversal(root);
	}
	// 层次遍历
	public void levelOrderTraversal(){	
		if(root == null) return; 
		Queue<Node<E>> queue = new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()){
			Node<E> node = queue.poll();
			System.out.print(node.element + " ");
			if(node.left != null) queue.offer(node.left);
			if(node.right != null) queue.offer(node.right);
		}
	}
 	*/
	/**
	 * 访问器的遍历
	 */
	// 层次遍历
	public void levelOrder(Visitor<E> visitor){
		if(root == null || visitor == null) return;
		Queue<Node<E>> queue = new LinkedList<>(); // 队列
		queue.offer(root);
		
		while(!queue.isEmpty()){
			Node<E> node = queue.poll();
			visitor.visit(node.element);
			if(node.left != null) queue.offer(node.left);
			if(node.right != null) queue.offer(node.right);
		}
	}
	// 前序遍历
	public void preOrderTraversal(Node<E> node, Visitor<E> visitor){
		if(node == null || visitor == null) return;
		visitor.visit(node.element);
		preOrderTraversal(node.left, visitor);
		preOrderTraversal(node.right, visitor);
	}
	public void preOrderTraversal(Visitor<E> visitor){
		preOrderTraversal(root, visitor);
	}
	// 中序遍历
	public void inorderTraversal(Node<E> node, Visitor<E> visitor){
		if(node == null || visitor == null) return;
		inorderTraversal(node.left, visitor);
		visitor.visit(node.element);
		inorderTraversal(node.right, visitor);
	}
	public void inorderTraversal(Visitor<E> visitor){
		inorderTraversal(root, visitor);
	}
	// 后序遍历
	public void postorderTraversal(Node<E> node, Visitor<E> visitor){
		if(node == null || visitor == null) return;
		postorderTraversal(node.left, visitor);
		postorderTraversal(node.right, visitor);
		visitor.visit(node.element);
	}
	public void postorderTraversal(Visitor<E> visitor){
		postorderTraversal(root, visitor);
	}
	/**
	 * 高度
	 */
	// 递归求高度
	public int height1(Node<E> node){
		if(node == null) return 0;
		return 1 + Math.max(height1(node.left), height1(node.right));
	}
	public int height1(){
		return height1(root);
	}
	// 迭代求高度
	public int height(){
		if(root == null) return 0;
		int levelSize = 1; // 存储每一层的元素数量	
 		int height = 0; // 树的高度
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while(!queue.isEmpty()){	
			Node<E> node = queue.poll();
			levelSize--;
			if(node.left != null) {
				queue.offer(node.left);
			}
			if(node.right != null) {
				queue.offer(node.right);
			}
			if(levelSize == 0){ // 即将要访问下一层
				levelSize = queue.size();
				height++;
			}
		}
		return height;
	}
	/**
	 * 是否是完全二叉树
	 */
	/*
	public boolean isComplete(){
		if(root == null) return false;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		boolean leaf = false;
		while(!queue.isEmpty()){
			Node<E> node = queue.poll();
			if(leaf && !node.isLeaf()){
				return false;
			}
			if(node.hasTwoChildren()) { // 正常入队
				queue.offer(node.left);
				queue.offer(node.right);
			}else if(node.left==null && node.right!=null){
				return false;
			}else{
				leaf = true;
			}
		}
		return true;
	}
	*/
	public boolean isComplete(){
		if(root == null){
			return false;
		}
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		boolean leaf = false;
		while(!queue.isEmpty()){
			Node<E> node = queue.poll();
			if(leaf && !node.isLeaf()){ // 要求是叶子结点，但是当前节点不是叶子结点
				return false;
			}
			if(node.left != null){
				queue.offer(node.left);
			}else if(node.right != null){
				// node.left==null && node.right!=null
				return false;
			}
			if(node.right != null){
				queue.offer(node.right);
			}else{
				// node.left==null && node.right==null
				// node.left!=null && node.right==null
				leaf = true; // 要求后面都是叶子节点
			}
		}
		return true;
	}
	
	/**
	 * 根据传入的值删除元素
	 */
	public void remove(E element) {
		remove(node(element));
	}
	// 根据节点删除元素
	private void remove(Node<E> node) {
		if (node == null) return;
		
		size--;
		
		if (node.hasTwoChildren()) { // 度为2的节点
			// 找到后继节点
			Node<E> s = successor(node);
			// 用后继节点的值覆盖度为2的节点的值
			node.element = s.element;
			// 删除后继节点
			node = s;
		}
		
		// 删除node节点（node的度必然是1或者0）
		Node<E> replacement = node.left != null ? node.left : node.right;
		
		if (replacement != null) { // node是度为1的节点
			// 更改parent
			replacement.parent = node.parent;
			// 更改parent的left、right的指向
			if (node.parent == null) { // node是度为1的节点并且是根节点
				root = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else { // node == node.parent.right
				node.parent.right = replacement;
			}
		} else if (node.parent == null) { // node是叶子节点并且是根节点
			root = null;
		} else { // node是叶子节点，但不是根节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else { // node == node.parent.right
				node.parent.right = null;
			}
		}
	}
	
	/**
	 * 是否包含某元素
	 */
	public boolean contains(E element) {
		return node(element) != null;
	}
	// 根据元素值获取节点元素
	private Node<E> node(E element){
		elementNotNullCheck(element);
		
		Node<E> node = root;
		while(node != null){
			int cmp = compareTo(element, node.element);
			if(cmp < 0){
				node = node.left;
			}else if (cmp > 0){
				node = node.right;
			}else{ // cmp == 0
				return node;
			}
		}
		return null;
	}
	
	/**
	 * 访问器接口 ——> 访问器抽象类
	 * 增强遍历接口
	 */
	public static interface Visitor<E>{
		void visit(E element);
	}
	
	@Override
	public Object root() {
		return root;
	}
	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}
	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}
	@Override
	public Object string(Object node) {
		Node<E> myNode = (Node<E>)node;
		String parentStr = "null";
		if(myNode.parent != null){
			parentStr = myNode.parent.element.toString();
		}
		return myNode.element + "_p(" + parentStr + ")";
	}
	/**
	 * 求前驱节点
	 */
	private Node<E> predecessor(Node<E> node) {
		if(node == null) return null;
		
		// 前驱节点在左子树中(left.right.right.right....)
		if(node.left != null ){ // 左子树不为空,则找到它的最右节点
			Node<E> p = node.left;	
			while(node.right != null){
				p = p.right;
			}
			return p;
		}
		// 能来到这里说明左子树为空
		// 当父节点不为空，则顺着父节点找，直到找到【该结点为父节点的右节点】时
		while(node.parent != null && node.parent.left==node){
			node = node.parent;
		}
		
		// node.parent == null	无前驱
		// node.parent.right == node 该节点为根结点的右节点
		return node.parent;
	}
	/**
	 * 求后继节点
	 */
	private Node<E> successor(Node<E> node) {
		if(node == null) return null;
		
		if(node.right != null){ // 存在右节点
			Node<E> p = node.right;
			while(p.left != null){
				p = p.left;
			}
			return p;
		}
		
		while(node.parent!=null && node.parent.right==node){
			node = node.parent;
		}
		
		// node.parent == null
		// node.parent.left == node
		return node.parent;
	}
	
	// 节点元素比较
	private int compareTo(E e1, E e2) {
		if (comparator != null) { // 传入比较器则通过比较器比较
			comparator.compare(e1, e2);
		}
		// 没传比较器，元素内部必须自行实现了 Comparable 接口
		return ((Comparable<E>)e1).compareTo(e2);
	}

	// 检测传入的节点是否为空
	private void elementNotNullCheck(E element) {
		if (element == null) { // 不能传入空节点
			throw new IllegalArgumentException("element must not null");
		}
	}
	
}
