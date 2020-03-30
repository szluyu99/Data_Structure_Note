package com.mj.tree;

import java.util.LinkedList;
import java.util.Queue;
import com.mj.printer.BinaryTreeInfo;
/**
 * 二叉树
 */
@SuppressWarnings("unchecked")
// 实现BinaryTreeInfo接口是为了使用打印二叉树的工具，非必须
public class BinaryTree<E> implements BinaryTreeInfo {
	protected int size; // 元素数量
	protected Node<E> root; // 根节点
	
	/**
	 * 访问器接口 ——> 访问器抽象类
	 * 增强遍历接口
	 */
	/*public static interface Visitor<E>{
		void visit(E element);
	}*/
	public static abstract class Visitor<E> {
		boolean stop;
		// 如果返回true，就代表停止遍历
		public abstract boolean visit(E element);
	}
	/**
	 * 内部类，节点类
	 */
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
	 * 前序遍历
	 */
	public void preorder(Visitor<E> visitor){
		if (visitor == null) return;
		preorder(root, visitor);
	}
	public void preorder(Node<E> node, Visitor<E> visitor){
		if(node == null || visitor.stop) return;
		// 根
		visitor.stop = visitor.visit(node.element);
		// 左
		preorder(node.left, visitor);
		// 右
		preorder(node.right, visitor);
	}
	/**
	 * 中序遍历
	 * @param visitor
	 */
	public void inorder(Visitor<E> visitor){
		if (visitor == null) return;
		inorder(root, visitor);
	}
	public void inorder(Node<E> node, Visitor<E> visitor){
		if(node == null || visitor.stop) return;
		// 左
		inorder(node.left, visitor);
		// 根
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
		// 右
		inorder(node.right, visitor);
	}
	/**
	 * 后序遍历
	 */
	public void postorder(Visitor<E> visitor){
		if(visitor == null) return;
		postorder(root, visitor);
	}
	public void postorder(Node<E> node, Visitor<E> visitor){
		if(node == null || visitor.stop) return;
		// 左
		postorder(node.left, visitor);
		// 右
		postorder(node.right, visitor);
		// 根
		if(visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
	}
	/**
	 * 层次遍历
	 */
	public void levelOrder(Visitor<E> visitor){
		if(root == null || visitor.stop) return;
		Queue<Node<E>> queue = new LinkedList<>(); // 队列
		queue.offer(root);
		
		while(!queue.isEmpty()){
			Node<E> node = queue.poll();
			if(visitor.visit(node.element)) return;
			
			if(node.left != null) {
				queue.offer(node.left);
			}
			if(node.right != null) {
				queue.offer(node.right);
			}
		}
	}
	/**
	 * 高度（递归）
	 */
	public int height1(){
		return height1(root);
	}
	public int height1(Node<E> node){
		if(node == null) return 0;
		return 1 + Math.max(height1(node.left), height1(node.right));
	}
	/**
	 * 高度（迭代）
	 */
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
	 * 前驱节点：中序遍历时的前一个节点
	 * 求前驱节点
	 */
	protected Node<E> predecessor(Node<E> node) {
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
		// 当父节点不为空，则顺着父节点找，直到找到【某结点为父节点的右子节点】时
		while(node.parent != null && node.parent.left==node){
			node = node.parent;
		}
		// 来到这里有以下两种情况：
		// node.parent == null	无前驱，说明是根结点
		// node.parent.right == node 找到【某结点为父节点的右子节点】
		return node.parent;
	}
	/**
	 * 后继节点：中序遍历时的后一个节点
	 * 求后继节点
	 */
	protected Node<E> successor(Node<E> node) {
		if(node == null) return null;
		// 后继节点与前驱节点正好相反
		
		// 后继节点在右子树中（node.right.left.left...）
		if(node.right != null){
			Node<E> p = node.right;
			while(p.left != null){
				p = p.left;
			}
			return p;
		}
		// 来到这里说明没有右节点
		// 当父节点不为空，则顺着父节点找，直到找到【某结点为父节点的左子节点】时
		while(node.parent!=null && node.parent.right==node){
			node = node.parent;
		}
		
		// 来到这里有以下两种情况：
		// node.parent == null   无前驱，说明是根结点
		// node.parent.left == node  找到【某结点为父节点的左子节点】
		return node.parent;
	}
	/**
	 * BinaryTreeInfo 工具，用来打印二叉树
	 */
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
}
